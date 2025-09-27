package com.ast.storagemdp_api.specifications;

import com.ast.storagemdp_api.enums.MovementType;
import com.ast.storagemdp_api.models.InventoryMovementModel;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;

public class InventoryMovementSpecifications {

    public static Specification<InventoryMovementModel> hasCompany(Long companyId) {
        return (companyId == null) ? null :
                (root, query, cb) -> cb.equal(root.get("company").get("id"), companyId);
    }

    public static Specification<InventoryMovementModel> hasBranch(Long branchId) {
        return (branchId == null) ? null :
                (root, query, cb) -> cb.equal(root.get("branch").get("id"), branchId);
    }

    public static Specification<InventoryMovementModel> hasType(MovementType type) {
        return (type == null) ? null :
                (root, query, cb) -> cb.equal(root.get("type"), type);
    }

    public static Specification<InventoryMovementModel> createdBetween(ZonedDateTime start, ZonedDateTime end) {
        if (start != null && end != null) {
            return (root, query, cb) -> cb.between(root.get("createdAt"), start, end);
        } else if (start != null) {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), start);
        } else if (end != null) {
            return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), end);
        } else {
            return null;
        }
    }
}
