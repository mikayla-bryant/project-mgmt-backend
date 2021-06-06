package com.example.projectmgmt.controllers;

import com.example.projectmgmt.models.Client;
import com.example.projectmgmt.models.Ticket;
import com.example.projectmgmt.models.User;
import com.example.projectmgmt.services.OrganizationService;
import com.example.projectmgmt.services.TicketService;
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
@RequestMapping("/tickets")
public class TicketController
{
    @Autowired
    private TicketService ticketService;

    @Autowired
    private OrganizationService organizationService;

    // RETRIEVES A LIST OF ALL TICKETS IN APPLICATION
    // http://localhost:2021/tickets/tickets
    @GetMapping(value = "/tickets")
    public ResponseEntity<?> listAllTickets()
    {
        List<Ticket> myTickets = ticketService.findAllTickets();
        return new ResponseEntity<>(myTickets, HttpStatus.OK);
    }

    // RETRIEVES A LIST OF ALL TICKETS IN ORGANIZATION BY ORGANIZATION ID
    // http://localhost:2021/tickets/organization
    @GetMapping(value = "/organization")
    public ResponseEntity<?> listAllTicketsByOrganization(@RequestParam UUID organizationId)
    {
        List<Ticket> myTickets = ticketService.findAllTicketsByOrganization(organizationService.findOrganizationByOrganizationID(organizationId));
        return new ResponseEntity<>(myTickets, HttpStatus.OK);
    }

    // RETRIEVES INFO ABOUT A SPECIFIC TICKET (BY ID)
    // http://localhost:2021/tickets/ticket/{TICKETID}
    @GetMapping(value = "/ticket/{ticketid}", produces = {"application/json"})
    public ResponseEntity<?> findTicketById(
            @PathVariable long ticketid)
    {
        Ticket rtnTicket = ticketService.findTicketById(ticketid);
        return new ResponseEntity<>(rtnTicket, HttpStatus.OK);
    }

    // POSTS A NEW TICKET TO DATABASE
    // http://localhost:2021/tickets/ticket
    @PostMapping(value = "/ticket", consumes = {"application/json"})
    public ResponseEntity<?> addNewTicket(@Valid @RequestBody Ticket newTicket)
    {
        newTicket.setTicketid(0);
        newTicket = ticketService.save(newTicket);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ticketid}")
                .buildAndExpand(newTicket.getTicketid())
                .toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(newTicket, responseHeaders, HttpStatus.CREATED);
    }

    // UPDATES AN EXISTING TICKET (BY ID)
    // http://localhost:2021/tickets/ticket/{TICKETID}
    @PutMapping(value = "/ticket/{ticketid}", consumes = {"application/json"})
    public ResponseEntity<?> updateFullTicket(
            @Valid
            @RequestBody
                    Ticket updateTicket,
            @PathVariable
                    long ticketid
    ){
        updateTicket.setTicketid(ticketid);
        ticketService.save(updateTicket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
