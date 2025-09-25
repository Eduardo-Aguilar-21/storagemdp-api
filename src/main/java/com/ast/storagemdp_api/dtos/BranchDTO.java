package com.ast.storagemdp_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {
    private Long id;
    private String name;
    private String address;
    private Long companyId;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
