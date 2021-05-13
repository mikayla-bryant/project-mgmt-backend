package com.example.projectmgmt.models;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contactid;

    private String firstname;

    private String lastname;

    private String phonenumber;

    private String email;

    private String city;

    private String state;

    private String country;

    @ManyToOne
    @JoinColumn(name = "clientid", nullable = true)
    private Client client;

    public Contact()
    {
    }

    public Contact(String firstname, String lastname, String phonenumber, String email, String city, String state, String country)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public long getContactid()
    {
        return contactid;
    }

    public void setContactid(long contactid)
    {
        this.contactid = contactid;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }
}
