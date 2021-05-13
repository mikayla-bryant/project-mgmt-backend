package com.example.projectmgmt.services;

import com.example.projectmgmt.models.Project;

import java.util.List;

public interface ProjectService
{
    Project findProjectById(long projectid);
    List<Project> findAllProjects();
    Project save(Project project);
}
