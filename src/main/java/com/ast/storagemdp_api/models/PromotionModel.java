package com.ast.storagemdp_api.models;

import com.ast.storagemdp_api.enums.PromotionStatus;
import com.ast.storagemdp_api.enums.PromotionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "promotion",
        indexes = {
                @Index(name = "idx_promotion_status", columnList = "status"),
                @Index(name = "idx_promotion_start_end", columnList = "startDate, endDate")
        })
public class PromotionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PromotionType type; // PRODUCT, CATEGORY, ORDER

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double discount; // porcentaje o monto

    @Column(nullable = false)
    private ZonedDateTime startDate;

    @Column(nullable = false)
    private ZonedDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PromotionStatus status; // ACTIVE, EXPIRED, INACTIVE

    @Column(nullable = false)
    private String createdBy;

    /** Relaciones */

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyModel company;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private BranchModel branch;

    @ManyToMany
    @JoinTable(
            name = "promotion_products",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private List<ProductModel> products;

    @ManyToMany
    @JoinTable(
            name = "promotion_categories",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private List<CategoryModel> categories;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
