package com.ast.storagemdp_api.specifications;

import com.ast.storagemdp_api.enums.PromotionStatus;
import com.ast.storagemdp_api.enums.PromotionType;
import com.ast.storagemdp_api.models.PromotionModel;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;

public class PromotionSpecifications {

    public static Specification<PromotionModel> hasCompany(Long companyId) {
        return (companyId == null) ? null :
                (root, query, cb) -> cb.equal(root.get("company").get("id"), companyId);
    }

    public static Specification<PromotionModel> hasBranch(Long branchId) {
        return (branchId == null) ? null :
                (root, query, cb) -> cb.equal(root.get("branch").get("id"), branchId);
    }

    public static Specification<PromotionModel> hasStatus(PromotionStatus status) {
        return (status == null) ? null :
                (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<PromotionModel> hasType(PromotionType type) {
        return (type == null) ? null :
                (root, query, cb) -> cb.equal(root.get("type"), type);
    }

    public static Specification<PromotionModel> startsAfter(ZonedDateTime start) {
        return (start == null) ? null :
                (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("startDate"), start);
    }

    public static Specification<PromotionModel> endsBefore(ZonedDateTime end) {
        return (end == null) ? null :
                (root, query, cb) -> cb.lessThanOrEqualTo(root.get("endDate"), end);
    }

    public static Specification<PromotionModel> activeBetween(ZonedDateTime date) {
        return (date == null) ? null :
                (root, query, cb) -> cb.and(
                        cb.lessThanOrEqualTo(root.get("startDate"), date),
                        cb.greaterThanOrEqualTo(root.get("endDate"), date)
                );
    }
}