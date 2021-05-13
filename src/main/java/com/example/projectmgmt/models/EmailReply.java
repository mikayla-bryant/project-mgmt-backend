package com.example.projectmgmt.models;

import javax.persistence.*;

@Entity
@Table(name = "emailreplies")
public class EmailReply
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long emailreplyid;

    private String subject;

    private String replytext;

    @ManyToOne
    @JoinColumn(name = "emailid", nullable = true)
    private Email email;

    public EmailReply()
    {
    }

    public EmailReply(String subject, String replytext)
    {
        this.subject = subject;
        this.replytext = replytext;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getReplytext()
    {
        return replytext;
    }

    public void setReplytext(String replytext)
    {
        this.replytext = replytext;
    }

    public Email getEmail()
    {
        return email;
    }

    public void setEmail(Email email)
    {
        this.email = email;
    }

    public long getEmailreplyid()
    {
        return emailreplyid;
    }

    public void setEmailreplyid(long emailreplyid)
    {
        this.emailreplyid = emailreplyid;
    }

}
