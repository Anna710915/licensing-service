package org.optimagrowth.organizationservice.controller;

import org.optimagrowth.organizationservice.model.Organization;
import org.optimagrowth.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "v1/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService service;

    @GetMapping(value = "/{organizationId}")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") String organizationId) {
        return ResponseEntity.ok(service.findById(organizationId));
    }

    @PutMapping(value = "/{organizationId}")
    @RolesAllowed({"ADMIN", "USER"})
    public void updateOrganization(@PathVariable("organizationId") String id, @RequestBody Organization organization) {
        service.update(organization);
    }

    @PostMapping
    @RolesAllowed({"ADMIN","USER"})
    public ResponseEntity<Organization> saveOrganization(@RequestBody Organization organization) {
        return ResponseEntity.ok(service.create(organization));
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping(value = "/{organizationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("organizationId") String id, @RequestBody Organization organization) {
        service.delete(organization);
    }
}
