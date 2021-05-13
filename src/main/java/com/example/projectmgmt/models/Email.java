package com.example.projectmgmt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "emails")
public class Email
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long emailid;

    private String subject;

    private String emailtext;

    private boolean starred;

    @ManyToMany
    @JoinTable(name = "emailrecipients", joinColumns = @JoinColumn(name="emailid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    @JsonIgnoreProperties("emails")
    private Set<User> recipients = new HashSet<>();

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("email")
    private List<EmailReply> replies = new ArrayList<>();

    public Email()
    {
    }

    public Email(String subject, String emailtext, boolean starred)
    {
        this.subject = subject;
        this.emailtext = emailtext;
        this.starred = starred;
    }

    public Set<User> getRecipients()
    {
        return recipients;
    }

    public void setRecipients(Set<User> recipients)
    {
        this.recipients = recipients;
    }

    public long getEmailid()
    {
        return emailid;
    }

    public void setEmailid(long emailid)
    {
        this.emailid = emailid;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getEmailtext()
    {
        return emailtext;
    }

    public void setEmailtext(String emailtext)
    {
        this.emailtext = emailtext;
    }

    public boolean isStarred()
    {
        return starred;
    }

    public void setStarred(boolean starred)
    {
        this.starred = starred;
    }

    public List<EmailReply> getReplies()
    {
        return replies;
    }

    public void setReplies(List<EmailReply> replies)
    {
        this.replies = replies;
    }

}
