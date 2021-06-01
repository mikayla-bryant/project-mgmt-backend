package com.example.projectmgmt;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectmgmtApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProjectmgmtApplication.class, args);
    }

}

// Put JsonIgnore on the @OneToMany relationships
// Add Find By ... ID to @ManyToOne relationships to compensate