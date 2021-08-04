package com.hendisantika.userrole.controller;

import com.hendisantika.userrole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
