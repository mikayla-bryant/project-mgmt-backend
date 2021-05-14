package com.example.projectmgmt.controllers;

import com.example.projectmgmt.models.Organization;
import com.example.projectmgmt.services.OrganizationService;
import org.aspectj.weaver.ast.Or;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/organizations")
public class OrganizationController
{
    @Autowired
    private OrganizationService organizationService;

    // RETRIEVES A LIST OF ALL ORGANIZATIONS (DELETE AFTER PROJECT COMPLETION, NO ONE SHOULD HAVE ACCESS TO THIS)
    // http://localhost:2021/organizations/organizations
    @GetMapping(value = "/organizations")
    public ResponseEntity<?> listAllOrganizations()
    {
        List<Organization> myOrganizations = organizationService.findAllOrganizations();
        return new ResponseEntity<>(myOrganizations, HttpStatus.OK);
    }

    // RETRIEVES INFO ABOUT A SPECIFIC ORGANIZATION (BY ID)
    // http://localhost:2021/organizations/organization/{ORGANIZATIONID}
    @GetMapping(value = "/organization/{organizationid}", produces = {"application/json"})
    public ResponseEntity<?> findOrganizationById(
            @PathVariable UUID organizationid)
    {
            Organization rtnOrganization = organizationService.findOrganizationById(organizationid);
            return new ResponseEntity<>(rtnOrganization, HttpStatus.OK);
    }

    // POSTS A NEW ORGANIZATION TO DATABASE
    // http://localhost:2021/organizations/organization
    @PostMapping(value = "/organization", consumes = {"application/json"})
    public ResponseEntity<?> addNewOrganization(@Valid @RequestBody Organization newOrganization)
    {
        newOrganization.setOrganizationid(UUID.randomUUID());
        newOrganization = organizationService.save(newOrganization);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrganizationURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{organizationid}")
                .buildAndExpand(newOrganization.getOrganizationid())
                .toUri();
        responseHeaders.setLocation(newOrganizationURI);
        return new ResponseEntity<>(newOrganization, responseHeaders, HttpStatus.CREATED);
    }

    // UPDATES AN EXISTING ORGANIZATION (BY ID)
    // http://localhost:2021/organizations/organization/{ORGANIZATIONID}
    @PutMapping(value = "/organization/{organizationid}", consumes = {"application/json"})
    public ResponseEntity<?> updateFullOrganization(
            @Valid
            @RequestBody
            Organization updateOrganization,
            @PathVariable
                    UUID organizationid
    ){
        updateOrganization.setOrganizationid(organizationid);
        organizationService.save(updateOrganization);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
