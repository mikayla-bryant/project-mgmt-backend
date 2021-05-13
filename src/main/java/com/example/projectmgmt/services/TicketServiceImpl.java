package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Ticket;
import com.example.projectmgmt.models.User;
import com.example.projectmgmt.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "ticketService")
public class TicketServiceImpl implements TicketService
{
    @Autowired
    private TicketRepository ticketrepos;

    @Override
    public Ticket findTicketById(long ticketid)
    {
        return ticketrepos.findById(ticketid)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Ticket> findAllTickets()
    {
        List<Ticket> list = new ArrayList<>();
        ticketrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Ticket save(Ticket ticket)
    {
        Ticket newTicket = new Ticket();
        if (ticket.getTicketid() != 0)
        {
            ticketrepos.findById(ticket.getTicketid())
                    .orElseThrow(() -> new EntityNotFoundException("Could not find ticket #" + ticket.getTicketid()));
            newTicket.setTicketid(ticket.getTicketid());
        }
        newTicket.setSubject(ticket.getSubject());
        newTicket.setDescription(ticket.getDescription());
        newTicket.setPriority(ticket.getPriority());
        newTicket.setStatus(ticket.getStatus());
        newTicket.setType(ticket.getType());
        newTicket.setDateClosed(ticket.getDateClosed());
        newTicket.setProject(ticket.getProject());
        newTicket.setAssignee(ticket.getAssignee());
        newTicket.setOrganization(ticket.getOrganization());
        return ticketrepos.save(ticket);
    }
}
