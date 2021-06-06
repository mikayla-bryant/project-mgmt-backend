package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Organization;
import com.example.projectmgmt.models.User;

import java.util.List;

public interface UserService
{
    User findUserById(long userid);
    User findByEmailaddress(String emailaddress);
    List<User> findAllUsers();
    User save(User user);
    List<User> findAllUsersByOrganization(Organization organization);
}
