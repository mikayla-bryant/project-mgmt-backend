package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByEmailaddress(String emailaddress);
}
