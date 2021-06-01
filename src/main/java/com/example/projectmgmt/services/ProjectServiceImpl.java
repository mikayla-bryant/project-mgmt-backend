package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Project;
import com.example.projectmgmt.models.Ticket;
import com.example.projectmgmt.models.User;
import com.example.projectmgmt.repositories.ProjectRepository;
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
}
/*
@ManyToOne
Many Projects to One Client
Find Projects By Client ID

@ManyToMany
Many Projects to Many Users
Find Projects By User ID
*/