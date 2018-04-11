package com.modern.codes.lime.tools;

import java.util.Collections;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.pojo.UserPOJO;
import com.modern.codes.lime.service.RoleService;
import com.modern.codes.lime.service.UserService;

@Service
public class StartUpPopulator {
    public StartUpPopulator(final UserService userService, final RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public void createRoles() {
        adminRole.setName("ROLE_ADMIN");
        managerRole.setName("ROLE_MANAGER");
        staffRole.setName("ROLE_STAFF");
        adminRole = roleService.save(adminRole);
        managerRole = roleService.save(managerRole);
        staffRole = roleService.save(staffRole);
    }

    public void addAdmin() {
        final UserPOJO admin = new UserPOJO();
        admin.setPlainPassword("admin1234");
        admin.setUsername("admin");
        admin.setJoinedAt(new Date());
        admin.setName("Maciej");
        admin.setSurname("Glowala");
        admin.setEmailAddress("maciejglowala95@gmail.com");
        admin.setPOJORoles(Collections.singletonList(adminRole));
        userService.save(admin);
    }

    private RolePOJO adminRole = new RolePOJO();
    private RolePOJO managerRole = new RolePOJO();
    private RolePOJO staffRole = new RolePOJO();
    private final UserService userService;
    private final RoleService roleService;
}
