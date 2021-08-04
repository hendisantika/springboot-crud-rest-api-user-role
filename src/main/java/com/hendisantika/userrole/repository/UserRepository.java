package com.hendisantika.userrole.repository;

import com.hendisantika.userrole.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.email = ?1")
//    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = ?1 or u.username = ?1")
    User findByEmailOrUsername(String email);

    Optional<User> findByEmail(String email);

    @Query(value = "FROM User u WHERE u.createdOn BETWEEN :startDate AND :endDate")
    List<User> findByCreatedOn(LocalDateTime startDate, LocalDateTime endDate);

    Page<User> findAll(Pageable pageable);

}
