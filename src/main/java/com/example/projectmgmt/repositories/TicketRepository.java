package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.Organization;
import com.example.projectmgmt.models.Ticket;
import com.example.projectmgmt.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long>
{
    List<Ticket> findTicketsByOrganization(Organization organization);
    Ticket findByTicketid(Long ticketid);
}
