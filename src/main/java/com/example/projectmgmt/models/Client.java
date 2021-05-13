package com.example.projectmgmt.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clientid;

    private String name;

    private String street1;

    private String street2;

    private String city;

    private String state;

    private String zip;

    private String country;

    private String phonenumber;

    private String email;

    private String description;

    private String website;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties("client")
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("client")
    private List<Contact> contacts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "organizationid", nullable = true) // CHANGE TO NULLABLE = FALSE WHEN CONDUCTING TESTS / APPLICATION CLEANUP
    private Organization organization;

    public Client()
    {
    }

    public Client(String name, String street1, String street2, String city, String state, String zip, String country, String phonenumber, String email, String description, String website, List<Project> projects, List<Contact> contacts, Organization organization)
    {
        this.name = name;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.phonenumber = phonenumber;
        this.email = email;
        this.description = description;
        this.website = website;
        this.projects = projects;
        this.contacts = contacts;
        this.organization = organization;
    }

    public List<Project> getProjects()
    {
        return projects;
    }

    public void setProjects(List<Project> projects)
    {
        this.projects = projects;
    }

    public List<Contact> getContacts()
    {
        return contacts;
    }

    public void setContacts(List<Contact> contacts)
    {
        this.contacts = contacts;
    }

    public Organization getOrganization()
    {
        return organization;
    }

    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public long getClientid()
    {
        return clientid;
    }

    public void setClientid(long clientid)
    {
        this.clientid = clientid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStreet1()
    {
        return street1;
    }

    public void setStreet1(String street1)
    {
        this.street1 = street1;
    }

    public String getStreet2()
    {
        return street2;
    }

    public void setStreet2(String street2)
    {
        this.street2 = street2;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }
}
