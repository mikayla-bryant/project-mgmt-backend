package com.example.projectmgmt.repositories;

import com.example.projectmgmt.models.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long>
{
}
