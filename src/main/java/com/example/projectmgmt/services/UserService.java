package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Organization;
import com.example.projectmgmt.models.User;

import java.util.List;

public interface UserService
{
    User findByUserid(long userid);
    User findUserById(long userid);
    User findByEmailaddress(String emailaddress);
    List<User> findAllUsers();
    User save(User user);
    List<User> findAllUsersByOrganization(Organization organization);
    User update(User user, long userid);

}
