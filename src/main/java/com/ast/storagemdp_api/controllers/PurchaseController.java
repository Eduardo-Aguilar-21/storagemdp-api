package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.PurchaseDTO;
import com.ast.storagemdp_api.models.CompanyModel;
import com.ast.storagemdp_api.models.SupplierModel;
import com.ast.storagemdp_api.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/{id}")
    public PurchaseDTO getPurchaseById(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id);
    }

    @PostMapping
    public PurchaseDTO createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return purchaseService.createPurchase(purchaseDTO);
    }

    @PutMapping("/{id}")
    public PurchaseDTO updatePurchase(@PathVariable Long id, @RequestBody PurchaseDTO purchaseDTO) {
        return purchaseService.updatePurchase(id, purchaseDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<PurchaseDTO> getPurchasesBySupplier(@PathVariable Long supplierId) {
        SupplierModel supplier = new SupplierModel();
        supplier.setId(supplierId);
        return purchaseService.getPurchasesBySupplier(supplier);
    }

    @GetMapping("/supplier/{supplierId}/paged")
    public Page<PurchaseDTO> getPurchasesBySupplier(@PathVariable Long supplierId, Pageable pageable) {
        SupplierModel supplier = new SupplierModel();
        supplier.setId(supplierId);
        return purchaseService.getPurchasesBySupplier(supplier, pageable);
    }

    // Listar compras por proveedor y estado de pago
    @GetMapping("/supplier/{supplierId}/paid/{paid}")
    public List<PurchaseDTO> getPurchasesBySupplierAndPaid(@PathVariable Long supplierId, @PathVariable Boolean paid) {
        SupplierModel supplier = new SupplierModel();
        supplier.setId(supplierId);
        return purchaseService.getPurchasesBySupplierAndPaid(supplier, paid);
    }

    // Listar compras por proveedor y estado de pago con paginación
    @GetMapping("/supplier/{supplierId}/paid/{paid}/paged")
    public Page<PurchaseDTO> getPurchasesBySupplierAndPaid(@PathVariable Long supplierId,
                                                           @PathVariable Boolean paid,
                                                           Pageable pageable) {
        SupplierModel supplier = new SupplierModel();
        supplier.setId(supplierId);
        return purchaseService.getPurchasesBySupplierAndPaid(supplier, paid, pageable);
    }

    @GetMapping("/date/{date}")
    public List<PurchaseDTO> getPurchasesByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return purchaseService.getPurchasesByDate(localDate);
    }

    @GetMapping("/date/{date}/paged")
    public Page<PurchaseDTO> getPurchasesByDate(@PathVariable String date, Pageable pageable) {
        LocalDate localDate = LocalDate.parse(date);
        return purchaseService.getPurchasesByDate(localDate, pageable);
    }

    // Todas las compras de la empresa, paginadas y ordenadas descendente
    @GetMapping("/company/{companyId}/paged/desc")
    public Page<PurchaseDTO> getAllPurchasesByCompanyPagedDesc(@PathVariable Long companyId, Pageable pageable) {
        CompanyModel company = new CompanyModel();
        company.setId(companyId);
        return purchaseService.getAllPurchasesByCompanyPagedDesc(company, pageable);
    }

    // Compras de un proveedor específico de la empresa, paginadas y ordenadas descendente
    @GetMapping("/company/{companyId}/supplier/{supplierId}/paged/desc")
    public Page<PurchaseDTO> getPurchasesByCompanyAndSupplierPagedDesc(
            @PathVariable Long companyId,
            @PathVariable Long supplierId,
            Pageable pageable) {
        CompanyModel company = new CompanyModel();
        company.setId(companyId);
        SupplierModel supplier = new SupplierModel();
        supplier.setId(supplierId);
        return purchaseService.getPurchasesByCompanyAndSupplierPagedDesc(company, supplier, pageable);
    }

    @GetMapping("/kpi/current-month")
    public ResponseEntity<Long> getPurchasesCurrentMonth() {
        LocalDate now = LocalDate.now();
        long count = purchaseService.countPurchasesInMonth(now.getYear(), now.getMonthValue());
        return ResponseEntity.ok(count);
    }

    @GetMapping("/kpi/total-month")
    public ResponseEntity<Double> getTotalSpentCurrentMonth() {
        LocalDate now = LocalDate.now();
        double total = purchaseService.getTotalSpentInMonth(now.getYear(), now.getMonthValue());
        return ResponseEntity.ok(total);
    }

    @GetMapping("/kpi/average-per-purchase")
    public ResponseEntity<Double> getAveragePerPurchase(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {

        LocalDate now = LocalDate.now();
        int y = (year != null) ? year : now.getYear();
        int m = (month != null) ? month : now.getMonthValue();

        double average = purchaseService.getAveragePerPurchaseInMonth(y, m);
        return ResponseEntity.ok(average);
    }
}
