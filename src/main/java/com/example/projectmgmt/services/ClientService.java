package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Client;
import com.example.projectmgmt.models.Organization;

import java.util.List;

public interface ClientService
{
    Client findClientById(long clientid);
    List<Client> findAllClients();
    Client save(Client client);
    List<Client> findAllClientsByOrganization(Organization organization);
    Client update(Client client, long clientid);
}
