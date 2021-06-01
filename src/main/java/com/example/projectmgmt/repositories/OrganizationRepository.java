package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrganizationRepository extends CrudRepository<Organization, UUID>
{
    Organization findOrganizationByOrganizationname(String name);
}
