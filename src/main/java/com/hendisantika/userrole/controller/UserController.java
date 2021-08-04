package com.hendisantika.userrole.controller;

import com.hendisantika.userrole.entity.User;
import com.hendisantika.userrole.exception.UserRegistrationException;
import com.hendisantika.userrole.repository.UserRepository;
import com.hendisantika.userrole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasAnyAuthority('SUPER ADMIN', 'ADMIN', 'EDITOR')")
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
}
