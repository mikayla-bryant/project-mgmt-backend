package com.example.projectmgmt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    private String firstname;

    private String lastname;

    @Column(nullable = false, unique = true)
    private String emailaddress;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne
    @JoinColumn(name = "organizationid", nullable = true) // CHANGE TO FALSE WHEN DATABASE IS SET UP
    private Organization organization;

    @ManyToMany(mappedBy = "recipients")
    @JsonIgnoreProperties("recipients")
    Set<Email> emails = new HashSet<>();

    @ManyToMany(mappedBy = "assignedUsers")
    @JsonIgnoreProperties(value = {"assignedUsers"})
    Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnoreProperties("assignee")
    private List<Ticket> assignedtickets = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<UserRoles> roles = new HashSet<>();

    public User()
    {
    }

    public User(String firstname, String lastname, String emailaddress, String password)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddress = emailaddress;
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       this.password = passwordEncoder.encode(password);

    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public Organization getOrganization()
    {
        return organization;
    }

    public void setOrganization(Organization organization)
    {
        this.organization = organization;
    }

    public Set<Email> getEmails()
    {
        return emails;
    }

    public void setEmails(Set<Email> emails)
    {
        this.emails = emails;
    }

    public Set<Project> getProjects()
    {
        return projects;
    }

    public void setProjects(Set<Project> projects)
    {
        this.projects = projects;
    }

    public List<Ticket> getAssignedtickets()
    {
        return assignedtickets;
    }

    public void setAssignedtickets(List<Ticket> assignedtickets)
    {
        this.assignedtickets = assignedtickets;
    }

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


    public Set<UserRoles> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles)
    {
        this.roles = roles;
    }

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r: this.roles)
        {
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }
}
