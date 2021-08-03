package com.hendisantika.userrole.controller;

import com.hendisantika.userrole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("process_register")
    public String processRegister(User user) {
        service.registerDefaultUser(user);

        return "register_success";
    }
}
