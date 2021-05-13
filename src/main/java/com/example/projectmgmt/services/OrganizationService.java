package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Organization;

import java.util.List;
import java.util.UUID;

public interface OrganizationService
{

    Organization findOrganizationById(UUID organizationid);
    List<Organization> findAllOrganizations();
    Organization save(Organization organization);
}
