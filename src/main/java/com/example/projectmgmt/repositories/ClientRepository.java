package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long>
{
}
