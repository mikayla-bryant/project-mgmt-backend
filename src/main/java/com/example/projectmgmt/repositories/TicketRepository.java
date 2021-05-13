package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long>
{
}
