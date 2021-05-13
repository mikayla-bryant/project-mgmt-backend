package com.example.projectmgmt.services;

import com.example.projectmgmt.models.EmailReply;
import com.example.projectmgmt.repositories.EmailReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service(value = "replyService")
public class EmailReplyServiceImpl implements EmailReplyService
{
    @Autowired
    private EmailReplyRepository replyrepos;

    @Override
    public EmailReply findReplyById(long emailreplyid)
    {
        return replyrepos.findById(emailreplyid)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    @Override
    public EmailReply save (EmailReply emailReply)
    {
        EmailReply newEmailReply = new EmailReply();
        if (emailReply.getEmailreplyid() != 0)
        {
            replyrepos.findById(emailReply.getEmailreplyid())
                    .orElseThrow(() -> new EntityNotFoundException("Reply " + emailReply.getEmailreplyid() + " Not Found"));
            newEmailReply.setEmailreplyid(emailReply.getEmailreplyid());
        }
        newEmailReply.setSubject(emailReply.getSubject());
        newEmailReply.setReplytext(emailReply.getReplytext());
        newEmailReply.setEmail(emailReply.getEmail());
        return replyrepos.save(emailReply);
    }
}
