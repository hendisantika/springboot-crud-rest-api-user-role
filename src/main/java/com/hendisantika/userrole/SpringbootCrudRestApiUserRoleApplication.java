package com.hendisantika.userrole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootCrudRestApiUserRoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCrudRestApiUserRoleApplication.class, args);
    }

}
