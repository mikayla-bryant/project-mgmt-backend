package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Ticket;

import java.util.List;

public interface TicketService
{
    Ticket findTicketById(long ticketid);
    List<Ticket> findAllTickets();
    Ticket save(Ticket ticket);
}
