package com.example.projectmgmt.controllers;

import com.example.projectmgmt.models.Contact;
import com.example.projectmgmt.services.ContactService;
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
@RequestMapping("/contacts")
public class ContactController
{
    @Autowired
    private ContactService contactService;

    // RETRIEVES A LIST OF ALL CLIENTS IN APPLICATION
    // http://localhost:2021/contacts/contacts
    @GetMapping(value = "/contacts")
    public ResponseEntity<?> listAllContacts()
    {
        List<Contact> myContacts = contactService.findAllContacts();
        return new ResponseEntity<>(myContacts, HttpStatus.OK);
    }

    // RETRIEVES INFO ABOUT A SPECIFIC CLIENT (BY ID)
    // http://localhost:2021/contacts/contact/{CONTACTID}
    @GetMapping(value = "/contact/{contactid}", produces = {"application/json"})
    public ResponseEntity<?> findContactById(
            @PathVariable long contactid)
    {
        Contact rtnContact = contactService.findContactById(contactid);
        return new ResponseEntity<>(rtnContact, HttpStatus.OK);
    }

    // POSTS A NEW CLIENT TO DATABASE
    // http://localhost:2021/contacts/contact
    @PostMapping(value = "/contact", consumes = {"application/json"})
    public ResponseEntity<?> addNewContact(@Valid @RequestBody Contact newContact)
    {
        newContact.setContactid(0);
        newContact = contactService.save(newContact);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{contactid}")
                .buildAndExpand(newContact.getContactid())
                .toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(newContact, responseHeaders, HttpStatus.CREATED);
    }

    // UPDATES AN EXISTING CLIENT (BY ID)
    // http://localhost:2021/contacts/contact/{CONTACTID}
    @PutMapping(value = "/contact/{contactid}", consumes = {"application/json"})
    public ResponseEntity<?> updateFullContact(
            @Valid
            @RequestBody
                    Contact updateContact,
            @PathVariable
                    long contactid
    ){
        updateContact.setContactid(contactid);
        contactService.save(updateContact);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
