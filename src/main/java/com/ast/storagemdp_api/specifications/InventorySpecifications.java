package com.ast.storagemdp_api.specifications;

import com.ast.storagemdp_api.models.InventoryModel;
import org.springframework.data.jpa.domain.Specification;

public class InventorySpecifications {

    public static Specification<InventoryModel> hasCompany(Long companyId) {
        return (root, query, cb) -> companyId == null
                ? null
                : cb.equal(root.get("company").get("id"), companyId);
    }

    public static Specification<InventoryModel> hasBranch(Long branchId) {
        return (root, query, cb) -> branchId == null
                ? null
                : cb.equal(root.get("branch").get("id"), branchId);
    }

    public static Specification<InventoryModel> hasProduct(Long productId) {
        return (root, query, cb) -> productId == null
                ? null
                : cb.equal(root.get("product").get("id"), productId);
    }

    public static Specification<InventoryModel> hasCategory(Long categoryId) {
        return (root, query, cb) -> categoryId == null
                ? null
                : cb.equal(root.get("product").get("category").get("id"), categoryId);
    }

    /**
     * Productos en bajo stock (quantity < minStock).
     * Si el parámetro es `true`, trae solo bajo stock.
     * Si es `false`, trae solo los que NO están en bajo stock.
     * Si es `null`, no aplica filtro.
     */
    public static Specification<InventoryModel> isLowStock(Boolean lowStock) {
        return (root, query, cb) -> {
            if (lowStock == null) {
                return null;
            }
            return lowStock
                    ? cb.lessThan(root.get("quantity"), root.get("minStock"))
                    : cb.greaterThanOrEqualTo(root.get("quantity"), root.get("minStock"));
        };
    }

    public static Specification<InventoryModel> hasCompanyAndBranch(Long companyId, Long branchId) {
        return (root, query, cb) -> {
            if (companyId != null && branchId != null) {
                return cb.and(
                        cb.equal(root.get("company").get("id"), companyId),
                        cb.equal(root.get("branch").get("id"), branchId)
                );
            } else if (companyId != null) {
                return cb.equal(root.get("company").get("id"), companyId);
            } else if (branchId != null) {
                return cb.equal(root.get("branch").get("id"), branchId);
            }
            return null;
        };
    }

}
