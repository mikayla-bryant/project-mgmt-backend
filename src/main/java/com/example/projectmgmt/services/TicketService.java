package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Organization;
import com.example.projectmgmt.models.Ticket;
import com.example.projectmgmt.models.User;

import java.util.List;

public interface TicketService
{
    Ticket findTicketById(long ticketid);
    List<Ticket> findAllTickets();
    Ticket save(Ticket ticket);
    List<Ticket> findAllTicketsByOrganization(Organization organization);
}
