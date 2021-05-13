package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Client;

import java.util.List;

public interface ClientService
{
    Client findClientById(long clientid);
    List<Client> findAllClients();
    Client save(Client client);
}
