package com.hendisantika.userrole.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-crud-rest-api-user-role
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/08/21
 * Time: 18.43
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot CRUD REST API User Role")
                        .description("A REST API application to manage User & Role")
                        .version("v1")
                        .contact(new Contact()
                                .name("Hendi Santika")
                                .email("hendisantika@gmail.com"))
                        .license(new License()
                                .name("License of API")
                                .url("https://swagger.io/docs/")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Boot CRUD REST API User Role Documentation")
                        .url("https://swagger.io/docs/"));
    }
}
