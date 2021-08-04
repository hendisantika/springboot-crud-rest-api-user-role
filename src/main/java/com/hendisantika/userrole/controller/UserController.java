package com.hendisantika.userrole.controller;

import com.hendisantika.userrole.entity.User;
import com.hendisantika.userrole.exception.UserRegistrationException;
import com.hendisantika.userrole.repository.UserRepository;
import com.hendisantika.userrole.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@Api(value = "A REST API application to manage User & Role",
        description = "This API provides the capability to manage User & Role", produces = "application/json")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Add New User", produces = "application/json")
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


    @ApiOperation(value = "Get All Users", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue = "5"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @ApiOperation(value = "Search User by Id", produces = "application/json")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid User user) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userService.deleteUserById(id);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Search for all users based on created On between startDate & End Date", produces =
            "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "Start Date", required = true, dataType = "String",
                    paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "End Date", required = true, dataType = "String", paramType =
                    "query")})
    @GetMapping("/filter")
    public List<User> filterUserByCreatedOn(@RequestParam("startDate") String startDate,
                                            @RequestParam("endDate") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate1 = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDate1 = LocalDateTime.parse(endDate, formatter);
        return userRepository.findByCreatedOn(startDate1, endDate1);
    }

}
