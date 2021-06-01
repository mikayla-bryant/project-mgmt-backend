package com.example.projectmgmt.models.dto;

public class OrganizationDTO
{
    private String organizationid;

    private String organizationname;

    private int userdeletioninterval;

    private UserInfo users;

    public String getOrganizationid()
    {
        return organizationid;
    }

    public void setOrganizationid(String organizationid)
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
