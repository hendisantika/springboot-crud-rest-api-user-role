package com.hendisantika.userrole.controller;

import com.hendisantika.userrole.entity.User;
import com.hendisantika.userrole.exception.UserRegistrationException;
import com.hendisantika.userrole.repository.UserRepository;
import com.hendisantika.userrole.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-crud-rest-api-user-role
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/08/21
 * Time: 08.05
 */
@Log4j2
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "A REST API application to manage User & Role")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Operation(summary = "Add New User", description = "Creates a new user with default USER role")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new UserRegistrationException("User with email " + user.getEmail() + " already exists");
        }
        userService.registerDefaultUser(user);
        User userEntity = userRepository.findByEmail(user.getEmail()).get();
        return userEntity;
    }


    @Operation(summary = "Get All Users", description = "Retrieves all users with pagination support")
    @GetMapping
    public Page<User> getAllUsers(
            @Parameter(description = "Pagination and sorting parameters") Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Operation(summary = "Search User by Id", description = "Retrieves a user by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@Parameter(description = "User ID") @PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update User", description = "Updates an existing user")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "User ID") @PathVariable Long id,
            @RequestBody @Valid User user) {
        return userRepository.findById(id)
                .map(x -> {
                    x.setFullName(user.getFullName());
                    x.setUsername(user.getUsername());
                    x.setEmail(user.getEmail());
                    x.setRoles(user.getRoles());
                    return ResponseEntity.ok(userService.updateUser(x));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete User", description = "Deletes a user by their ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@Parameter(description = "User ID") @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userService.deleteUserById(id);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Filter Users by Creation Date",
            description = "Search for all users created between startDate and endDate (format: yyyy-MM-dd HH:mm)")
    @GetMapping("/filter")
    public List<User> filterUserByCreatedOn(
            @Parameter(description = "Start Date (format: yyyy-MM-dd HH:mm)", required = true)
            @RequestParam("startDate") String startDate,
            @Parameter(description = "End Date (format: yyyy-MM-dd HH:mm)", required = true)
            @RequestParam("endDate") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate1 = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDate1 = LocalDateTime.parse(endDate, formatter);
        return userRepository.findByCreatedOn(startDate1, endDate1);
    }

}
