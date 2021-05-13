package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>
{
    Role findByName(String name);
}
