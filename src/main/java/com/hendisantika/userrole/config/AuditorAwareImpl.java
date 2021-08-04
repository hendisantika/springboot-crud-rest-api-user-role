package com.hendisantika.userrole.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-crud-rest-api-user-role
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/08/21
 * Time: 09.27
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Mr. Auditor");
    }
}
