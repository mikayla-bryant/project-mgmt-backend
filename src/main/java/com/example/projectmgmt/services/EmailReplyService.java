package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Contact;
import com.example.projectmgmt.models.EmailReply;

public interface EmailReplyService
{
    EmailReply findReplyById(long emailreplyid);
    EmailReply save(EmailReply emailReply);
}
