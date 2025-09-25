package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dtos.UserDTO;
import com.ast.storagemdp_api.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO findById(Long id);

    UserDTO findByUsername(String username);

    UserDTO findByEmail(String email);

    List<UserDTO> findAll();

    Page<UserDTO> findAll(Pageable pageable);

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    void deleteById(Long id);
}
