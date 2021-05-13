package com.example.projectmgmt.models;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "tickets")
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticketid;

    private String subject;

    private String description;

    private String priority;

    private String status;

    private String type;

    private String dateClosed;


    @ManyToOne
    @JoinColumn(name = "projectid", nullable = true)
    private Project project;


    @ManyToOne
    @JoinColumn(name = "userid", nullable = true)
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "organizationid", nullable = true)
    private Organization organization;

    public Ticket()
    {
    }

    public Ticket(String subject, String description, String priority, String status, String type, String dateClosed, String submitter)
    {
        this.subject = subject;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.type = type;
        this.dateClosed = dateClosed;
    }


    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public User getAssignee()
    {
        return assignee;
    }

    public void setAssignee(User assignee)
    {
        this.assignee = assignee;
    }

    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    public String getDateClosed()
    {
        return dateClosed;
    }

    public void setDateClosed(String dateClosed)
    {
        this.dateClosed = dateClosed;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public long getTicketid()
    {
        return ticketid;
    }

    public void setTicketid(long ticketid)
    {
        this.ticketid = ticketid;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getPriority()
    {
        return priority;
    }

    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public Organization getOrganization()
    {
        return organization;
    }

    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
