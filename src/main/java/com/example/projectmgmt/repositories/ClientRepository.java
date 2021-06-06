package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.Client;
import com.example.projectmgmt.models.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long>
{
    List<Client> findClientsByOrganization(Organization organization);
}
