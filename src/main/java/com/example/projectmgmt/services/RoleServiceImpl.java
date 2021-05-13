package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Role;
import com.example.projectmgmt.models.User;
import com.example.projectmgmt.models.UserRoles;
import com.example.projectmgmt.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleRepository rolerepos;

    @Override
    public Role findByName(String name)
    {
        Role rr = rolerepos.findByName(name);
        if (rr != null)
        {
            return rr;
        } else
        {
            throw new EntityNotFoundException(name);
        }
    }

    @Transactional
    @Override
    public Role save(Role role)
    {
        Role newRole = new Role();
        newRole.setName(role.getName());
        for (UserRoles ur: role.getUsers())
        {
            UserRoles newUserRoles = new UserRoles();
            newUserRoles.setUser(ur.getUser());
            newUserRoles.setRole(newRole);

            newRole.getUsers().add(newUserRoles);

        }
        return rolerepos.save(role);
    }

}
