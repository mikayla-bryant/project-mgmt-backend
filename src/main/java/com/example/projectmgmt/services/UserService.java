package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Organization;
import com.example.projectmgmt.models.Project;
import com.example.projectmgmt.models.User;
import com.example.projectmgmt.models.dto.UserDTO;

import java.util.List;

public interface UserService
{
    User findUserById(long userid);
    User findByEmailaddress(String emailaddress);
    List<User> findAllUsers();
    List<UserDTO> findAllUsersAndReturnDto();
    User save(User user);
    List<UserDTO> findAllUsersByOrganization(Organization organization);
}
