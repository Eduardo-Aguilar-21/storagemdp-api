package com.ast.storagemdp_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String contactPerson;
    private String taxId;
    private String notes;
    private Boolean active;
    private Long companyId;
    private String companyName;
}
