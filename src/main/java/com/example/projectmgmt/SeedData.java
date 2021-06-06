package com.example.projectmgmt;

import com.example.projectmgmt.models.*;
import com.example.projectmgmt.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@ConditionalOnProperty(prefix = "command.line.runner", value = "enabled", havingValue = "true", matchIfMissing = true)
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    ClientService clientService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ContactService contactService;

    @Autowired
    TicketService ticketService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
       Organization o1 = new Organization();
       o1.setOrganizationname("Pebble Technologies");
       o1.setUserdeletioninterval(0);

       o1 = organizationService.save(o1);

        Role r1 = new Role("OWNER");
        Role r2 = new Role("ADMIN");
        Role r3 = new Role("PROJECT_MANAGER");
        Role r4 = new Role("DEVELOPER");
        Role r5 = new Role("QA_ANALYST");
        Role r6 = new Role("CLIENT");
        Role r7 = new Role("INACTIVE");
        Role r8 = new Role("DATA");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);
        r4 = roleService.save(r4);
        r5 = roleService.save(r5);
        r6 = roleService.save(r6);
        r7 = roleService.save(r7);
        r8 = roleService.save(r8);


        User u1 = new User("Charlie", "Brown", "test@email.com", "password");
        User u2 = new User("DemoA", "Tester", "datester@email.com", "ADMIN_2021_DEMO");
        User u3 = new User("DemoPM", "Tester", "dpmtester@email.com", "PM_2021_DEMO");
        User u4 = new User("DemoDev", "Tester", "devtester@email.com", "DEV_2021_DEMO");
        User u5 = new User("DemoInactive", "Tester", "inactive@email.com", "INACTIVE_2021_DEMO");

        u1.setPassword("password");
        u2.setPassword("ADMIN_2021_DEMO");
        u3.setPassword("PM_2021_DEMO");
        u4.setPassword("DEV_2021_DEMO");
        u5.setPassword("INACTIVE_2021_DEMO");

        u1.getRoles().add(new UserRoles(u1, r2));
        u2.getRoles().add(new UserRoles(u2, r2));
        u3.getRoles().add(new UserRoles(u3, r3));
        u4.getRoles().add(new UserRoles(u4, r4));
        u5.getRoles().add(new UserRoles(u5, r7));

        u1.setOrganization(o1);
        u2.setOrganization(o1);
        u3.setOrganization(o1);
        u4.setOrganization(o1);
        u5.setOrganization(o1);

        u1 = userService.save(u1);
        u2 = userService.save(u2);
        u3 = userService.save(u3);
        u4 = userService.save(u4);
        u5 = userService.save(u5);


        Client c1 = new Client();
        c1.setName("JPMorgan Chase");
        c1.setStreet1("270 Park Ave");
        c1.setStreet2("");
        c1.setCity("New York");
        c1.setState("NY");
        c1.setZip("10017");
        c1.setCountry("United States of America");
        c1.setPhonenumber("(212) 270-1648");
        c1.setEmail("");
        c1.setDescription("J.P. Morgan is a global leader in financial services, offering solutions ot the world's most important corporations, governments and institutions in more than 100 countries.");
        c1.setWebsite("https://www.jpmorgan.com");

        Client c2 = new Client();
        c2.setName("Dollar General");
        c2.setStreet1("100 Mission Ridge");
        c2.setStreet2("");
        c2.setCity("Goodlettsville");
        c2.setState("TN");
        c2.setZip("37072");
        c2.setCountry("United States of America");
        c2.setPhonenumber("(615) 855-4000");
        c2.setEmail("");
        c2.setDescription("Dollar General Corporation is a discount retailer that offers a selection of merchandise, including consumables, seasonal goods, home products and apparel.");
        c2.setWebsite("https://www.dollargeneral.com/");

        Client c3 = new Client();
        c3.setName("Figma");
        c3.setStreet1("116 New Montgomery St");
        c3.setStreet2("Suite 400");
        c3.setCity("San Francisco");
        c3.setState("CA");
        c3.setZip("94105");
        c3.setCountry("United States of America");
        c3.setPhonenumber("(415) 890-5404");
        c3.setEmail("");
        c3.setDescription("Figma is a technology company that provides a web-based suite of design, prototyping, and collaboration features that help teams create, test, and share design systems across the organization");
        c3.setWebsite("https://www.figma.com");

          c1.setOrganization(o1);
          c2.setOrganization(o1);
          c3.setOrganization(o1);

         c1 = clientService.save(c1);
         c2 = clientService.save(c2);
         c3 = clientService.save(c3);

        Project p1 = new Project();
        Project p2 = new Project();
        Project p3 = new Project();


        p1.setProjectname("Inventory Tracker");
        p1.setDescription("An inventory tracker to help keep track of company merchandise");

        p2.setProjectname("Financial Statement Compiler");
        p2.setDescription("Generates financial documents for management to easily assess and review");

        p3.setProjectname("Employee Management System");
        p3.setDescription("An application that tracks number of hours worked, PTO, benefits, etc of employees");

        p1.setClient(c1);
        p2.setClient(c2);
        p3.setClient(c3);

        p1.setOrganization(o1);
        p2.setOrganization(o1);
        p3.setOrganization(o1);

        p1.getAssignedUsers().add(u2);

        p1 = projectService.save(p1);
        p2 = projectService.save(p2);
        p3 = projectService.save(p3);


        Contact co1 = new Contact();
        co1.setFirstname("Mary");
        co1.setLastname("Sue");
        co1.setCountry("United States of America");
        co1.setCity("New York");
        co1.setEmail("msue@email.com");
        co1.setPhonenumber("123-234-3456");
        co1.setClient(c1);

        co1 = contactService.save(co1);

        Ticket t1 = new Ticket();
        t1.setOrganization(o1);
        t1.setType("Bug");
        t1.setStatus("Open");
        t1.setPriority("High");
        t1.setSubject("ALL OF OUR SERVERS ARE FAILING!!");
        t1.setDescription("Pls help everything is failing right now.. AHHH");
        t1.setOwner("SpongeBob SquarePants");
        t1.setProject(p3);
        t1.setAssignee(u4);
        t1 = ticketService.save(t1);

    }
}
