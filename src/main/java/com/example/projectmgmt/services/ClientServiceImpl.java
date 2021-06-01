package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Client;

import com.example.projectmgmt.models.Contact;
import com.example.projectmgmt.models.Project;
import com.example.projectmgmt.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "clientService")
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private ClientRepository clientrepos;

    @Override
    public Client findClientById(long clientid)
    {
        return clientrepos.findById(clientid)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Client> findAllClients()
    {
        List<Client> list = new ArrayList<>();
        clientrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Client save(Client client)
    {
        Client newClient = new Client();
        if (client.getClientid() != 0)
        {
            clientrepos.findById(client.getClientid())
                    .orElseThrow(() -> new EntityNotFoundException("Client " + client.getClientid() + " Not Found"));
            newClient.setClientid(client.getClientid());
        }
        newClient.setName(client.getName());
        newClient.setStreet1(client.getStreet1());
        newClient.setStreet2(client.getStreet2());
        newClient.setCity(client.getCity());
        newClient.setState(client.getState());
        newClient.setZip(client.getZip());
        newClient.setCountry(client.getCountry());
        newClient.setPhonenumber(client.getPhonenumber());
        newClient.setEmail(client.getEmail());
        newClient.setDescription(client.getDescription());
        newClient.setWebsite(client.getWebsite());
        newClient.setOrganization(client.getOrganization());
        newClient.getProjects().clear();
        for (Project p: client.getProjects())
        {
            Project newProject = new Project();
            newProject.setProjectname(p.getProjectname());
            newProject.setDescription(p.getDescription());
            newProject.setClient(newClient);

            newClient.getProjects().add(newProject);
        }
        newClient.getContacts().clear();
        for (Contact c: client.getContacts())
        {
            Contact newContact = new Contact();
            newContact.setFirstname(c.getFirstname());
            newContact.setLastname(c.getLastname());
            newContact.setPhonenumber(c.getPhonenumber());
            newContact.setClient(newClient);

            newClient.getContacts().add(newContact);
        }


        return clientrepos.save(newClient);
    }
}

/*
@ManyToOne
Many Clients to One Organization
Find Clients By Organization Name
*/