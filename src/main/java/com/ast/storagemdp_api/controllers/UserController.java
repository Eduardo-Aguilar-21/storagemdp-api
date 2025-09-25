package com.ast.storagemdp_api.controllers;

import com.ast.storagemdp_api.dtos.UserDTO;
import com.ast.storagemdp_api.mappers.UserMapper;
import com.ast.storagemdp_api.models.UserModel;
import com.ast.storagemdp_api.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return users.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(users);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        Page<UserDTO> users = userService.findAll(pageable);
        return users.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getByUsername(@PathVariable String username) {
        UserDTO user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        UserDTO user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO saved = userService.save(userDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id); // aseguramos consistencia
        UserDTO updated = userService.update(userDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
