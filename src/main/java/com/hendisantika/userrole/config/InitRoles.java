package com.hendisantika.userrole.config;

import com.hendisantika.userrole.entity.Role;
import com.hendisantika.userrole.repository.RoleRepository;

import javax.annotation.PostConstruct;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-crud-rest-api-user-role
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/08/21
 * Time: 12.38
 */
//@Component
public class InitRoles {
    //    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    private void initRoles() {
        roleRepository.deleteAll();
        Role role1 = new Role();
        role1.setName("SUPER ADMIN");

        Role role2 = new Role();
        role2.setName("ADMIN");

        Role role3 = new Role();
        role3.setName("EDITOR");

        Role role4 = new Role();
        role4.setName("USER");

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        roleRepository.save(role4);
    }
}
