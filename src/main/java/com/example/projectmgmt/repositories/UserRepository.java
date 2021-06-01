package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.Organization;
import com.example.projectmgmt.models.Project;
import com.example.projectmgmt.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByEmailaddress(String emailaddress);
    List<User> findUsersByOrganization(Organization organization);
}
