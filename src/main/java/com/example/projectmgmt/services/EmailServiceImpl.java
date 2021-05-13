package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Client;
import com.example.projectmgmt.models.Email;
import com.example.projectmgmt.models.EmailReply;
import com.example.projectmgmt.models.User;
import com.example.projectmgmt.repositories.EmailRepository;
import com.example.projectmgmt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "emailService")
public class EmailServiceImpl implements EmailService
{
    @Autowired
    private EmailRepository emailrepos;

    @Autowired
    private UserRepository userrepos;

    @Override
    public Email findEmailById(long emailid)
    {
        return emailrepos.findById(emailid)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Email> findAllEmails()
    {
        List<Email> list = new ArrayList<>();
        emailrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

   @Transactional
   @Override
    public Email save(Email email)
   {
       Email newEmail = new Email();
       if (email.getEmailid() != 0)
       {
           emailrepos.findById(email.getEmailid())
                   .orElseThrow(() -> new EntityNotFoundException("Email " + email.getEmailid() + " Not Found"));
           newEmail.setEmailid(email.getEmailid());
       }
       newEmail.setSubject(email.getSubject());
       newEmail.setEmailtext(email.getEmailtext());
       newEmail.setStarred(email.isStarred());
       newEmail.getRecipients().clear();
       for (User u: email.getRecipients())
       {
           User newUser = userrepos.findById(u.getUserid())
                   .orElseThrow(()-> new EntityNotFoundException("User " + u.getUserid() + " Not Found"));

           newEmail.getRecipients().add(newUser);
       }
       newEmail.getReplies().clear();
       for (EmailReply er: email.getReplies())
       {
           EmailReply newEmailReply = new EmailReply();
           newEmailReply.setSubject(er.getSubject());
           newEmailReply.setReplytext(er.getReplytext());
           newEmailReply.setEmail(newEmail);

           newEmail.getReplies().add(newEmailReply);
       }

       return emailrepos.save(newEmail);
   }

}
