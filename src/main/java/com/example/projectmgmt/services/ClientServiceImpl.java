package com.example.projectmgmt.services;

import com.example.projectmgmt.models.*;

import com.example.projectmgmt.repositories.ClientRepository;
import com.example.projectmgmt.repositories.ProjectRepository;
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

    @Autowired
    private ProjectRepository projectrepos;

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

    @Override
    public List<Client> findAllClientsByOrganization(Organization organization)
    {
        List<Client> list = new ArrayList<>();
        //List<ClientDTO> listClientDTO = new ArrayList<>();
        clientrepos.findClientsByOrganization(organization)
                .iterator()
                .forEachRemaining(list::add);
/*
        for (Client client : list)
        {

            OrganizationInfo organizationInfo = new OrganizationInfo();
            organizationInfo.setOrganizationid(client.getOrganization().getOrganizationid().toString());
            organizationInfo.setOrganizationname(client.getOrganization().getOrganizationname());
            organizationInfo.setUserdeletioninterval(client.getOrganization().getUserdeletioninterval());

            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setClientid(client.getClientid());
            clientDTO.setOrganization(organizationInfo);
            clientDTO.setCity(client.getCity());
            clientDTO.setDescription(client.getDescription());
            clientDTO.setCountry(client.getCountry());
            clientDTO.setEmail(client.getEmail());
            clientDTO.setPhonenumber(client.getPhonenumber());
            clientDTO.setName(client.getName());
            clientDTO.setState(client.getState());
            clientDTO.setStreet1(client.getStreet1());
            clientDTO.setStreet2(client.getStreet2());
            clientDTO.setWebsite(client.getWebsite());
            clientDTO.setZip(client.getZip());

            listClientDTO.add(clientDTO);
        }*/

        return list;
       // return listClientDTO;
    }

    @Override
    public Client update(Client client, long clientid)
    {
        Client currentClient = clientrepos.findById(clientid).orElseThrow(()-> new EntityNotFoundException("Client #" + clientid + " not found"));
        if(client.getName() != null)
        {
            currentClient.setName(client.getName());
        }
        if(client.getStreet1() != null)
        {
            currentClient.setStreet1(client.getStreet1());
        }
        if(client.getStreet2() != null)
        {
            currentClient.setStreet2(client.getStreet2());
        }
        if(client.getCity() != null)
        {
            currentClient.setCity(client.getCity());
        }
        if(client.getState() != null)
        {
            currentClient.setState(client.getState());
        }
        if(client.getZip() != null)
        {
            currentClient.setZip(client.getZip());
        }
        if(client.getCountry() != null)
        {
            currentClient.setCountry(client.getCountry());
        }
        if (client.getPhonenumber() != null)
        {
            currentClient.setPhonenumber(client.getPhonenumber());
        }
        if (client.getEmail() != null)
        {
            currentClient.setEmail(client.getEmail());
        }
        if(client.getDescription() != null)
        {
            currentClient.setDescription(client.getDescription());
        }
        if (client.getWebsite() != null)
        {
            currentClient.setWebsite(client.getWebsite());
        }
        if (client.getOrganization() != null)
        {
            currentClient.setOrganization(client.getOrganization());
        }
        if (client.getProjects().size() > 0)
        {
            currentClient.getProjects().clear();
            for (Project p: client.getProjects())
            {
                Project newProject = new Project();
                newProject.setProjectname(p.getProjectname());
                // Add the rest of project keys
                currentClient.getProjects().add(newProject);
            }
        }
        return clientrepos.save(currentClient);

    }
}

