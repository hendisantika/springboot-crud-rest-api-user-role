package com.hendisantika.userrole.controller;

import com.hendisantika.userrole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-crud-rest-api-user-role
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/08/21
 * Time: 06.20
 */
@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    private UserService service;

    @GetMapping
    public String viewHomePage() {
        return "index";
    }

}
