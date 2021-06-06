package com.example.projectmgmt.services;

import com.example.projectmgmt.models.*;
import com.example.projectmgmt.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service(value = "organizationService")
public class OrganizationServiceImpl implements OrganizationService
{
    @Autowired
    private OrganizationRepository organizationrepos;

    @Override
    public Organization findOrganizationById(UUID organid)
    {
        return organizationrepos.findById(organid)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Organization> findAllOrganizations()
    {
        List<Organization> list = new ArrayList<>();
        organizationrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Organization save(Organization organization)
    {
        Organization newOrganization = new Organization();

        /*if (organization.getOrganizationid() == null)
        {
            organizationrepos.findById(organization.getOrganizationid())
                    .orElseThrow(() -> new EntityNotFoundException("Organization " + organization.getOrganizationid() + " Not Found"));
            newOrganization.setOrganizationid(organization.getOrganizationid());
        }*/

        newOrganization.setOrganizationname(organization.getOrganizationname());
        newOrganization.setUserdeletioninterval(organization.getUserdeletioninterval());
        newOrganization.getUsers().clear();
        for (User u : organization.getUsers())
        {
            User newUser = new User();
            newUser.setFirstname(u.getFirstname());
            newUser.setLastname(u.getLastname());
            newUser.setOrganization(u.getOrganization());

            newOrganization.getUsers().add(newUser);
        }
        newOrganization.getClients().clear();
        for (Client c: organization.getClients())
        {
            Client newClient = new Client();
            newClient.setName(c.getName());
            newClient.setCity(c.getCity());
            newClient.setOrganization(c.getOrganization());

            newOrganization.getClients().add(newClient);
        }
        newOrganization.getTickets().clear();
        for (Ticket t: organization.getTickets())
        {
            Ticket newTicket = new Ticket();
            newTicket.setSubject(t.getSubject());
            newTicket.setDescription(t.getDescription());
            newTicket.setOrganization(t.getOrganization());

            newOrganization.getTickets().add(newTicket);
        }

        for (Project p: organization.getProjects())
        {
            Project newProject = new Project();
            newProject.setProjectname(p.getProjectname());
            newProject.setDescription(p.getDescription());
            newProject.setOrganization(p.getOrganization());

            newOrganization.getProjects().add(newProject);
        }

        return organizationrepos.save(newOrganization);
    }

    @Override
    public Organization findOrganizationByOrganizationID(UUID organizationName)
    {
        return organizationrepos.findOrganizationByOrganizationid(organizationName);
    }

}
