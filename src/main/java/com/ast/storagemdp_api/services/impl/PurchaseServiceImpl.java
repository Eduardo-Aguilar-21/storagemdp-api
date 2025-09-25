package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.PurchaseDTO;
import com.ast.storagemdp_api.mappers.PurchaseItemMapper;
import com.ast.storagemdp_api.mappers.PurchaseMapper;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.models.PurchaseItemModel;
import com.ast.storagemdp_api.models.PurchaseModel;
import com.ast.storagemdp_api.models.SupplierModel;
import com.ast.storagemdp_api.repositories.PurchaseRepository;
import com.ast.storagemdp_api.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public PurchaseDTO getPurchaseById(Long id) {
        PurchaseModel purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found with id: " + id));
        return PurchaseMapper.toDTO(purchase);
    }

    @Override
    public PurchaseDTO createPurchase(PurchaseDTO purchaseDTO) {
        PurchaseModel entity = PurchaseMapper.toEntity(purchaseDTO);

        // Calcular totalAmount sumando los items
        double total = 0;
        if (entity.getItems() != null) {
            total = entity.getItems().stream()
                    .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                    .sum();
        }
        entity.setTotalAmount(total);

        PurchaseModel saved = purchaseRepository.save(entity);
        return PurchaseMapper.toDTO(saved);
    }

    @Override
    public PurchaseDTO updatePurchase(Long id, PurchaseDTO purchaseDTO) {
        PurchaseModel existing = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found with id: " + id));

        existing.setPurchaseDate(purchaseDTO.getPurchaseDate());
        existing.setInvoiceNumber(purchaseDTO.getInvoiceNumber());
        existing.setNotes(purchaseDTO.getNotes());
        existing.setPaid(purchaseDTO.getPaid());

        if (purchaseDTO.getSupplierId() != null) {
            SupplierModel supplier = new SupplierModel();
            supplier.setId(purchaseDTO.getSupplierId());
            existing.setSupplier(supplier);
        }

        // Manejar items: reemplazar los existentes
        if (purchaseDTO.getItems() != null) {
            existing.getItems().clear();
            List<PurchaseItemModel> items = purchaseDTO.getItems().stream()
                    .map(PurchaseItemMapper::toEntity)
                    .collect(Collectors.toList());
            items.forEach(item -> item.setPurchase(existing));
            existing.getItems().addAll(items);

            double total = items.stream()
                    .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                    .sum();
            existing.setTotalAmount(total);
        }

        PurchaseModel updated = purchaseRepository.save(existing);
        return PurchaseMapper.toDTO(updated);
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }


    @Override
    public List<PurchaseDTO> getPurchasesBySupplier(SupplierModel supplier) {
        return purchaseRepository.findBySupplier(supplier)
                .stream()
                .map(PurchaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PurchaseDTO> getPurchasesBySupplier(SupplierModel supplier, Pageable pageable) {
        Page<PurchaseModel> page = purchaseRepository.findBySupplier(supplier, pageable);
        List<PurchaseDTO> dtos = page.stream().map(PurchaseMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public List<PurchaseDTO> getPurchasesBySupplierAndPaid(SupplierModel supplier, Boolean paid) {
        return purchaseRepository.findBySupplierAndPaid(supplier, paid)
                .stream()
                .map(PurchaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PurchaseDTO> getPurchasesBySupplierAndPaid(SupplierModel supplier, Boolean paid, Pageable pageable) {
        Page<PurchaseModel> page = purchaseRepository.findBySupplierAndPaid(supplier, paid, pageable);
        List<PurchaseDTO> dtos = page.stream().map(PurchaseMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public List<PurchaseDTO> getPurchasesByDate(LocalDate date) {
        return purchaseRepository.findByPurchaseDate(date)
                .stream()
                .map(PurchaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PurchaseDTO> getPurchasesByDate(LocalDate date, Pageable pageable) {
        Page<PurchaseModel> page = purchaseRepository.findByPurchaseDate(date, pageable);
        List<PurchaseDTO> dtos = page.stream().map(PurchaseMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public Page<PurchaseDTO> getAllPurchasesByCompanyPagedDesc(CompanyModel company, Pageable pageable) {
        Page<PurchaseModel> page = purchaseRepository.findByCompanyOrderByPurchaseDateDesc(company, pageable);
        List<PurchaseDTO> dtos = page.stream().map(PurchaseMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public Page<PurchaseDTO> getPurchasesByCompanyAndSupplierPagedDesc(CompanyModel company, SupplierModel supplier, Pageable pageable) {
        Page<PurchaseModel> page = purchaseRepository.findByCompanyAndSupplierOrderByPurchaseDateDesc(company, supplier, pageable);
        List<PurchaseDTO> dtos = page.stream().map(PurchaseMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public long countPurchasesInMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return purchaseRepository.countByPurchaseDateBetween(start, end);
    }

    @Override
    public double getTotalSpentInMonth(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<PurchaseModel> purchases = purchaseRepository.findByPurchaseDateBetween(start, end);

        double total = 0;
        for (PurchaseModel purchase : purchases) {
            total += purchase.getItems().stream()
                    .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                    .sum();
        }
        return total;
    }

    @Override
    public double getAveragePerPurchaseInMonth(int year, int month) {
        double total = getTotalSpentInMonth(year, month);
        long count = countPurchasesInMonth(year, month);
        if (count == 0) return 0; // evitar división por cero
        return total / count;
    }
}
