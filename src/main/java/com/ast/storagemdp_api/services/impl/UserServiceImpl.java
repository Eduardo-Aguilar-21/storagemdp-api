package com.ast.storagemdp_api.services.impl;

import com.ast.storagemdp_api.dtos.UserDTO;
import com.ast.storagemdp_api.mappers.UserMapper;
import com.ast.storagemdp_api.models.UserModel;
import com.ast.storagemdp_api.repositories.UserRepository;
import com.ast.storagemdp_api.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findById(Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO findByUsername(String username) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<UserModel> page = userRepository.findAll(pageable);
        List<UserDTO> dtos = page.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
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
