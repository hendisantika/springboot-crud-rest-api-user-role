package com.hendisantika.userrole.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-crud-rest-api-user-role
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/08/21
 * Time: 06.14
 */
public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

}
