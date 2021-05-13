package com.example.projectmgmt;

import com.example.projectmgmt.models.Role;
import com.example.projectmgmt.models.User;
import com.example.projectmgmt.models.UserRoles;
import com.example.projectmgmt.services.RoleService;
import com.example.projectmgmt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@ConditionalOnProperty(prefix = "command.line.runner", value = "enabled", havingValue = "true", matchIfMissing = true)
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("OWNER");
        Role r2 = new Role("ADMIN");
        Role r3 = new Role("PROJECT_MANAGER");
        Role r4 = new Role("DEVELOPER");
        Role r5 = new Role("QA_ANALYST");
        Role r6 = new Role("CLIENT");
        Role r7 = new Role("INACTIVE");
        Role r8 = new Role("DATA");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);
        r4 = roleService.save(r4);
        r5 = roleService.save(r5);
        r6 = roleService.save(r6);
        r7 = roleService.save(r7);
        r8 = roleService.save(r8);


        User u1 = new User("Charlie", "Brown", "test@email.com", "password");

        u1.setPassword("password");

        u1.getRoles().add(new UserRoles(u1, r2));

        userService.save(u1);
    }
}
