package com.ast.storagemdp_api.services;

import com.ast.storagemdp_api.dto.UserDTO;
import com.ast.storagemdp_api.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserModel> findById(Long id);

    Optional<UserModel> findByUsername(String username);

    Optional<UserModel> findByEmail(String email);

    List<UserModel> findAll();

    Page<UserModel> findAll(Pageable pageable);

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    void deleteById(Long id);
}
