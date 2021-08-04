package com.hendisantika.userrole.repository;

import com.hendisantika.userrole.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-crud-rest-api-user-role
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/08/21
 * Time: 06.07
 */
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.email = ?1")
//    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = ?1 or u.username = ?1")
    User findByEmailOrUsername(String email);

    Optional<User> findByEmail(String email);
}
