package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Contact;

import java.util.List;

public interface ContactService
{
    Contact findContactById(long contactid);
    List<Contact> findAllContacts();
    Contact save(Contact contact);
}
