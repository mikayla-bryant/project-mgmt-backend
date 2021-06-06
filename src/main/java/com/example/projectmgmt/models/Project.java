package com.example.projectmgmt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectid;

    private String projectname;

    private String description;


    @ManyToOne
    @JoinColumn(name = "clientid", nullable = true)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "organizationid", nullable = true)
    private Organization organization;

    @ManyToMany
    @JoinTable(name = "projectusers", joinColumns = @JoinColumn(name="projectid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    private Set<User> assignedUsers = new HashSet<>();


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("project")
    private List<Ticket> tickets = new ArrayList<>();


    public Project()
    {
    }

    public Project(String projectname, String description, Client client, Set<User> assignedUsers, List<Ticket> tickets)
    {
        this.projectname = projectname;
        this.description = description;
        this.client = client;
        this.assignedUsers = assignedUsers;
        this.tickets = tickets;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public Set<User> getAssignedUsers()
    {
        return assignedUsers;
    }

    public void setAssignedUsers(Set<User> assignedUsers)
    {
        this.assignedUsers = assignedUsers;
    }

    public List<Ticket> getTickets()
    {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }

    public long getProjectid()
    {
        return projectid;
    }

    public void setProjectid(long projectid)
    {
        this.projectid = projectid;
    }

    public String getProjectname()
    {
        return projectname;
    }

    public void setProjectname(String projectname)
    {
        this.projectname = projectname;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Organization getOrganization()
    {
        return organization;
    }

    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }
}
