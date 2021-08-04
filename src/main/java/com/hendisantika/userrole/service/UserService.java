package com.hendisantika.userrole.service;

import com.hendisantika.userrole.entity.Role;
import com.hendisantika.userrole.entity.User;
import com.hendisantika.userrole.repository.RoleRepository;
import com.hendisantika.userrole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-crud-rest-api-user-role
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/08/21
 * Time: 06.09
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void registerDefaultUser(User user) {
        Role roleUser = roleRepository.findByName("USER");
        user.addRole(roleUser);
        userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
