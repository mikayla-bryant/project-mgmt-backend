package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Email;

import java.util.List;

public interface EmailService
{
    Email findEmailById(long emailid);
    List<Email> findAllEmails();
    Email save(Email email);
}
