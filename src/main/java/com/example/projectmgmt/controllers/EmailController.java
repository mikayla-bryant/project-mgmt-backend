package com.example.projectmgmt.controllers;

import com.example.projectmgmt.models.Email;
import com.example.projectmgmt.services.EmailService;
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
@RequestMapping("/emails")
public class EmailController
{
    @Autowired
    private EmailService emailService;

    // RETRIEVES A LIST OF ALL EMAILS IN APPLICATION
    // http://localhost:2021/emails/emails
    @GetMapping(value = "/emails")
    public ResponseEntity<?> listAllEmails()
    {
        List<Email> myEmails = emailService.findAllEmails();
        return new ResponseEntity<>(myEmails, HttpStatus.OK);
    }

    // RETRIEVES INFO ABOUT A SPECIFIC EMAIL (BY ID)
    // http://localhost:2021/emails/email/{EMAILID}
    @GetMapping(value = "/email/{emailid}", produces = {"application/json"})
    public ResponseEntity<?> findEmailById(
            @PathVariable long emailid)
    {
        Email rtnEmail = emailService.findEmailById(emailid);
        return new ResponseEntity<>(rtnEmail, HttpStatus.OK);
    }

    // POSTS A NEW EMAIL TO DATABASE
    // http://localhost:2021/emails/email
    @PostMapping(value = "/email", consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody Email newEmail)
    {
        newEmail.setEmailid(0);
        newEmail = emailService.save(newEmail);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newEmailURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{emailid}")
                .buildAndExpand(newEmail.getEmailid())
                .toUri();
        responseHeaders.setLocation(newEmailURI);
        return new ResponseEntity<>(newEmail, responseHeaders, HttpStatus.CREATED);
    }

}
