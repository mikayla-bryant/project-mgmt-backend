package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long>
{
}
