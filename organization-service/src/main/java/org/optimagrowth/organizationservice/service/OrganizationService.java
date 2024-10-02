package org.optimagrowth.organizationservice.service;

import org.optimagrowth.organizationservice.model.Organization;
import org.optimagrowth.organizationservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Transactional
    public Organization findById(String organizationId) {
        Optional<Organization> opt = organizationRepository.findById(organizationId);
        return opt.orElse(null);
    }

    @Transactional
    public Organization create(Organization organization) {
        organization.setId(UUID.randomUUID().toString());
        organization = organizationRepository.save(organization);
        return organization;
    }

    @Transactional
    public void update(Organization organization) {
        organizationRepository.save(organization);
    }

    @Transactional
    public void delete(Organization organization) {
        organizationRepository.deleteById(organization.getId());
    }
}
