package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Long>
{
}
