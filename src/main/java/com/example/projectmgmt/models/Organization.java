package com.example.projectmgmt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "organizations")
public class Organization
{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "organizationid", updatable = false, nullable = false)
    private UUID organizationid;

    private String organizationname;

    private int userdeletioninterval;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("organization")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("organization")
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("organization")
    private List<Client> clients = new ArrayList<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("organization")
    private List<Ticket> tickets = new ArrayList<>();

    public Organization()
    {
    }

    public Organization(String organizationname, int userdeletioninterval)
    {
        this.organizationname = organizationname;
        this.userdeletioninterval = userdeletioninterval;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public List<Ticket> getTickets()
    {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }

    public List<Project> getProjects()
    {
        return projects;
    }

    public void setProjects(List<Project> projects)
    {
        this.projects = projects;
    }

    public List<Client> getClients()
    {
        return clients;
    }

    public void setClients(List<Client> clients)
    {
        this.clients = clients;
    }

    public UUID getOrganizationid()
    {
        return organizationid;
    }

    public void setOrganizationid(UUID organizationid)
    {
        this.organizationid = organizationid;
    }

    public String getOrganizationname()
    {
        return organizationname;
    }

    public void setOrganizationname(String organizationname)
    {
        this.organizationname = organizationname;
    }

    public int getUserdeletioninterval()
    {
        return userdeletioninterval;
    }

    public void setUserdeletioninterval(int userdeletioninterval)
    {
        this.userdeletioninterval = userdeletioninterval;
    }
}
