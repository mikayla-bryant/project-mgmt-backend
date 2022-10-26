package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Organization;
import com.example.projectmgmt.models.Project;
import com.example.projectmgmt.models.Ticket;
import com.example.projectmgmt.models.User;
import com.example.projectmgmt.repositories.ProjectRepository;
import com.example.projectmgmt.repositories.TicketRepository;
import com.example.projectmgmt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    private ProjectRepository projectrepos;

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private TicketRepository ticketrepos;

    @Override
    public Project findProjectById(long projectid)
    {
        return projectrepos.findById(projectid)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Project> findAllProjects()
    {
        List<Project> list = new ArrayList<>();
        projectrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Project save(Project project)
    {
        Project newProject = new Project();
        if (project.getProjectid() != 0)
        {
            projectrepos.findById(project.getProjectid())
                    .orElseThrow(() -> new EntityNotFoundException("Project " + project.getProjectid() + " Not Found"));
            newProject.setProjectid(project.getProjectid());
        }
        newProject.setProjectname(project.getProjectname());
        newProject.setDescription(project.getDescription());
        newProject.setClient(project.getClient());
        newProject.setOrganization(project.getOrganization());
        newProject.getTickets().clear();
        for (Ticket t: project.getTickets())
        {
            Ticket newTicket = new Ticket();
            newTicket.setSubject(t.getSubject());
            newTicket.setDescription(t.getDescription());
            newTicket.setProject(newProject);

            newProject.getTickets().add(newTicket);
        }
        newProject.getAssignedUsers().clear();
        for (User u: project.getAssignedUsers())
        {
            User newUser = userrepos.findById(u.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("User " + u.getUserid() + " Not Found"));

            newProject.getAssignedUsers().add(newUser);
        }
        return projectrepos.save(newProject);
    }

    @Override
    public List<Project> findAllProjectsByOrganization(Organization organization)
    {
        List<Project> list = new ArrayList<>();
        projectrepos.findProjectsByOrganization(organization)
                .iterator()
                .forEachRemaining(list::add);

        return list;
    }

    @Override
    public Project update(Project project, long projectid)
    {
        Project currentProject = projectrepos.findById(projectid).orElseThrow(EntityNotFoundException::new);
        if (project.getProjectname() != null)
        {
            currentProject.setProjectname(project.getProjectname());
        }
        if (project.getDescription() != null)
        {
            currentProject.setDescription(project.getDescription());
        }
        if (project.getClient() != null)
        {
            currentProject.setClient(project.getClient());
        }
        if (project.getOrganization() != null)
        {
            currentProject.setOrganization(project.getOrganization());
        }
        if (project.getAssignedUsers().size() > 0)
        {
            currentProject.getAssignedUsers().clear();
            for (User u: project.getAssignedUsers())
            {
              User newUser = userrepos.findByUserid(u.getUserid());
              currentProject.getAssignedUsers().add(newUser);
            }
        }
        if (project.getTickets().size() > 0)
        {
            currentProject.getTickets().clear();
            for (Ticket t: project.getTickets())
            {
               Ticket newTicket = new Ticket();
               newTicket.setSubject(t.getSubject());
               newTicket.setDescription(t.getDescription());
               newTicket.setPriority(t.getPriority());
               newTicket.setStatus(t.getStatus());
               newTicket.setType(t.getType());
               newTicket.setDateClosed(t.getDateClosed());
               newTicket.setProject(currentProject);
               currentProject.getTickets().add(newTicket);
            }

        }
        return projectrepos.save(currentProject);
    }
}
