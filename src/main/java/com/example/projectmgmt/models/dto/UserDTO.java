package com.example.projectmgmt.models.dto;

public class UserDTO
{
    private long userid;
    private String firstname;
    private String lastname;
    private String emailaddress;
    private OrganizationInfo organization;

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
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

    public String getEmailaddress()
    {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress)
    {
        this.emailaddress = emailaddress;
    }

    public OrganizationInfo getOrganization()
    {
        return organization;
    }

    public void setOrganization(OrganizationInfo organization)
    {
        this.organization = organization;
    }
}
