package com.example.projectmgmt.controllers;

import com.example.projectmgmt.models.Client;
import com.example.projectmgmt.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController
{
    @Autowired
    private ClientService clientService;

    // RETRIEVES A LIST OF ALL CLIENTS IN APPLICATION
    // http://localhost:2021/clients/clients
    @GetMapping(value = "/clients")
    public ResponseEntity<?> listAllClients()
    {
        List<Client> myClients = clientService.findAllClients();
        return new ResponseEntity<>(myClients, HttpStatus.OK);
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
    // http://localhost:2021/clients/client/{CLIENTID}
    @PutMapping(value = "/client/{clientid}", consumes = {"application/json"})
    public ResponseEntity<?> updateFullClient(
            @Valid
            @RequestBody
                    Client updateClient,
            @PathVariable
                    long clientid
    ){
        updateClient.setClientid(clientid);
        clientService.save(updateClient);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
