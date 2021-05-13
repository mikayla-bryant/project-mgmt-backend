package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Role;

public interface RoleService
{
    Role findByName(String name);
    Role save(Role role);
}
