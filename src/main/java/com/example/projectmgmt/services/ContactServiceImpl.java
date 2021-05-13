package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Client;
import com.example.projectmgmt.models.Contact;
import com.example.projectmgmt.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "contactService")
public class ContactServiceImpl implements ContactService
{
    @Autowired
    private ContactRepository contactrepos;

    @Override
    public Contact findContactById(long contactid)
    {
        return contactrepos.findById(contactid)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Contact> findAllContacts()
    {
        List<Contact> list = new ArrayList<>();
        contactrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Contact save(Contact contact)
    {
        Contact newContact = new Contact();
        if (contact.getContactid() != 0)
        {
            contactrepos.findById(contact.getContactid())
                    .orElseThrow(()-> new EntityNotFoundException("Contact " + contact.getContactid() + " Not Found"));
            newContact.setContactid(contact.getContactid());
        }
        newContact.setFirstname(contact.getFirstname());
        newContact.setLastname(contact.getLastname());
        newContact.setPhonenumber(contact.getPhonenumber());
        newContact.setEmail(contact.getEmail());
        newContact.setCity(contact.getCity());
        newContact.setState(contact.getState());
        newContact.setCountry(contact.getCountry());
        newContact.setClient(contact.getClient());

        return contactrepos.save(contact);
    }
}
