package com.example.projectmgmt.controllers;

import com.example.projectmgmt.models.Client;
import com.example.projectmgmt.models.User;
import com.example.projectmgmt.services.ClientService;
import com.example.projectmgmt.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController
{
    @Autowired
    private ClientService clientService;

    @Autowired
    private OrganizationService organizationService;

    // RETRIEVES A LIST OF ALL CLIENTS IN APPLICATION
    // http://localhost:2021/clients/clients
    @GetMapping(value = "/clients")
    public ResponseEntity<?> listAllClients()
    {
        List<Client> myClients = clientService.findAllClients();
        return new ResponseEntity<>(myClients, HttpStatus.OK);
    }

    // RETRIEVES A LIST OF ALL CLIENTS IN ORGANIZATION BY ORGANIZATION NAME
    // http://localhost:2021/clients/organization
    @GetMapping(value = "/organization")
    public ResponseEntity<?> listAllClientsByOrganization(@RequestParam UUID organizationId)
    {
        List<Client> myUsers = clientService.findAllClientsByOrganization(organizationService.findOrganizationByOrganizationID(organizationId));
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    // RETRIEVES INFO ABOUT A SPECIFIC CLIENT (BY ID)
    // http://localhost:2021/clients/client/{CLIENTID}
    @GetMapping(value = "/client/{clientid}", produces = {"application/json"})
    public ResponseEntity<?> findClientById(
            @PathVariable long clientid)
    {
        Client rtnClient = clientService.findClientById(clientid);
        return new ResponseEntity<>(rtnClient, HttpStatus.OK);
    }

    // POSTS A NEW CLIENT TO DATABASE
    // http://localhost:2021/clients/client
    @PostMapping(value = "/client", consumes = {"application/json"})
    public ResponseEntity<?> addNewClient(@Valid @RequestBody Client newClient)
    {
        newClient.setClientid(0);
        newClient = clientService.save(newClient);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{clientid}")
                .buildAndExpand(newClient.getClientid())
                .toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(newClient, responseHeaders, HttpStatus.CREATED);
    }

    // UPDATES AN EXISTING CLIENT (BY ID)
    // http://localhost:2021/clients/client/{CLIENT}
    @PatchMapping(value = "/client/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateClient (@RequestBody Client updateClient,
                                         @PathVariable Long id){

            clientService.update(updateClient, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
