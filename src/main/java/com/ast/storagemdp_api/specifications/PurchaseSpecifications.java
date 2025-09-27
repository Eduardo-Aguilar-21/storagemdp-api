package com.ast.storagemdp_api.specifications;

import com.ast.storagemdp_api.models.PurchaseModel;
import com.ast.storagemdp_api.models.SupplierModel;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PurchaseSpecifications {

    public static Specification<PurchaseModel> hasCompany(Long companyId) {
        return (root, query, cb) -> companyId == null
                ? null
                : cb.equal(root.get("company").get("id"), companyId);
    }

    public static Specification<PurchaseModel> hasBranch(Long branchId) {
        return (root, query, cb) -> branchId == null
                ? null
                : cb.equal(root.get("branch").get("id"), branchId);
    }

    public static Specification<PurchaseModel> hasSupplier(Long supplierId) {
        return (root, query, cb) -> supplierId == null
                ? null
                : cb.equal(root.get("supplier").get("id"), supplierId);
    }

    public static Specification<PurchaseModel> isPaid(Boolean paid) {
        return (root, query, cb) -> paid == null
                ? null
                : cb.equal(root.get("paid"), paid);
    }

    public static Specification<PurchaseModel> purchaseDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> {
            if (startDate != null && endDate != null) {
                return cb.between(root.get("purchaseDate"), startDate, endDate);
            } else if (startDate != null) {
                return cb.greaterThanOrEqualTo(root.get("purchaseDate"), startDate);
            } else if (endDate != null) {
                return cb.lessThanOrEqualTo(root.get("purchaseDate"), endDate);
            } else {
                return null;
            }
        };
    }
}
