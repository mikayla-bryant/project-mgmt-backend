package com.example.projectmgmt.controllers;

import com.example.projectmgmt.models.Email;
import com.example.projectmgmt.models.EmailReply;
import com.example.projectmgmt.services.EmailReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/replies")
public class EmailReplyController
{

    @Autowired
    private EmailReplyService replyService;


    // RETRIEVES INFO ABOUT A SPECIFIC REPLY (BY ID)
    // http://localhost:2021/replies/reply/{EMAILREPLYID}
    @GetMapping(value = "/replies/{emailreplyid}", produces = {"application/json"})
    public ResponseEntity<?> findReplyById(
            @PathVariable long emailreplyid)
    {
        EmailReply rtnReply = replyService.findReplyById(emailreplyid);
        return new ResponseEntity<>(rtnReply, HttpStatus.OK);
    }

    // POSTS A NEW REPLY TO DATABASE
    // http://localhost:2021/replies/reply
    @PostMapping(value = "/reply", consumes = {"application/json"})
    public ResponseEntity<?> addNewReply(@Valid @RequestBody EmailReply newEmailReply)
    {
        newEmailReply.setEmailreplyid(0);
        newEmailReply = replyService.save(newEmailReply);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newEmailReplyURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{emailreplyid}")
                .buildAndExpand(newEmailReply.getEmailreplyid())
                .toUri();
        responseHeaders.setLocation(newEmailReplyURI);
        return new ResponseEntity<>(newEmailReply, responseHeaders, HttpStatus.CREATED);
    }
}
