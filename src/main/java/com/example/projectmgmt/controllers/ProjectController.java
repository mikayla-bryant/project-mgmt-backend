package com.example.projectmgmt.controllers;

import com.example.projectmgmt.models.Client;
import com.example.projectmgmt.models.Project;
import com.example.projectmgmt.models.User;
import com.example.projectmgmt.services.OrganizationService;
import com.example.projectmgmt.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController
{
    @Autowired
    private ProjectService projectService;

    @Autowired
    private OrganizationService organizationService;

    // RETRIEVES A LIST OF ALL PROJECTS IN APPLICATION
    // http://localhost:2021/projects/projects
    @GetMapping(value = "/projects")
    public ResponseEntity<?> listAllProjects()
    {
        List<Project> myProjects = projectService.findAllProjects();
        return new ResponseEntity<>(myProjects, HttpStatus.OK);
    }

    // RETRIEVES A LIST OF ALL PROJECTS IN ORGANIZATION BY ORGANIZATION ID
    // http://localhost:2021/users/organization
    @GetMapping(value = "/organization")
    public ResponseEntity<?> listAllUsersByOrganization(@RequestParam UUID organizationId)
    {
        List<Project> myProjects = projectService.findAllProjectsByOrganization(organizationService.findOrganizationByOrganizationID(organizationId));
        return new ResponseEntity<>(myProjects, HttpStatus.OK);
    }

    // RETRIEVES INFO ABOUT A SPECIFIC PROJECT (BY ID)
    // http://localhost:2021/projects/project/{PROJECTID}
    @GetMapping(value = "/project/{projectid}", produces = {"application/json"})
    public ResponseEntity<?> findProjectById(
            @PathVariable long projectid)
    {
        Project rtnProject = projectService.findProjectById(projectid);
        return new ResponseEntity<>(rtnProject, HttpStatus.OK);
    }

    // POSTS A NEW PROJECT TO DATABASE
    // http://localhost:2021/projects/project
    @PostMapping(value = "/project", consumes = {"application/json"})
    public ResponseEntity<?> addNewProject(@Valid @RequestBody Project newProject)
    {
        newProject.setProjectid(0);
        newProject = projectService.save(newProject);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{projectid}")
                .buildAndExpand(newProject.getProjectid())
                .toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(newProject, responseHeaders, HttpStatus.CREATED);
    }

    // UPDATES AN EXISTING CLIENT (BY ID)
    // http://localhost:2021/project/project/{PROJECT}
    @PatchMapping(value = "/project/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateProject (@RequestBody Project updateProject,
                                           @PathVariable Long id){

        projectService.update(updateProject, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
