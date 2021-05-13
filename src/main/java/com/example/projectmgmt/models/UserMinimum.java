package com.example.projectmgmt.models;

public class UserMinimum
{
    private String emailaddress;
    private String password;
    private String firstname;
    private String lastname;
    private Organization organization;


    public String getEmailaddress()
    {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress)
    {
        this.emailaddress = emailaddress;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    public Organization getOrganization()
    {
        return organization;
    }

    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }
}
