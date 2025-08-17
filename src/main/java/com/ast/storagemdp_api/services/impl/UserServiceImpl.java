package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dto.UserDTO;
import com.ast.storagemdp_api.mappers.UserMapper;
import com.ast.storagemdp_api.models.UserModel;
import com.ast.storagemdp_api.repositories.UserRepository;
import com.ast.storagemdp_api.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserModel entity = UserMapper.toEntity(userDTO);
        UserModel saved = userRepository.save(entity);
        return UserMapper.toDTO(saved);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return userRepository.findById(userDTO.getId())
                .map(existing -> {
                    existing.setUsername(userDTO.getUsername());
                    existing.setName(userDTO.getName());
                    existing.setEmail(userDTO.getEmail());
                    UserModel updated = userRepository.save(existing);
                    return UserMapper.toDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userDTO.getId()));
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
