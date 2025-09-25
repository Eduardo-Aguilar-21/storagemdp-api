package com.ast.storagemdp_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class SupplierModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 150)
    private String address;

    @Column(length = 50)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(length = 50)
    private String contactPerson;

    @Column(length = 50)
    private String taxId; // RUC, NIT, VAT, etc.

    @Column(length = 255)
    private String notes;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyModel company;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
