package com.ast.storagemdp_api.mappers;

import com.ast.storagemdp_api.dtos.UserDTO;
import com.ast.storagemdp_api.models.UserModel;

public class UserMapper {
    public static UserDTO toDTO(UserModel entity) {
        if (entity == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public static UserModel toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        UserModel entity = new UserModel();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
