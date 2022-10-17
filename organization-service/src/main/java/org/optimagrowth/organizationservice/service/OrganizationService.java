package org.optimagrowth.organizationservice.service;

import org.optimagrowth.organizationservice.events.source.SimpleSourceBean;
import org.optimagrowth.organizationservice.model.Organization;
import org.optimagrowth.organizationservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private SimpleSourceBean simpleSourceBean;

    @Transactional
    public Organization findById(String organizationId) {
        Optional<Organization> opt = organizationRepository.findById(organizationId);
        simpleSourceBean.publishOrganizationChange("GET", organizationId);
        return opt.orElse(null);
    }

    @Transactional
    public Organization create(Organization organization) {
        organization.setId(UUID.randomUUID().toString());
        organization = organizationRepository.save(organization);
        simpleSourceBean.publishOrganizationChange("CREATE", organization.getId());
        return organization;
    }

    @Transactional
    public void update(Organization organization) {
        organizationRepository.save(organization);
        simpleSourceBean.publishOrganizationChange("UPDATE", organization.getId());

    }

    @Transactional
    public void delete(Organization organization) {
        organizationRepository.deleteById(organization.getId());
        simpleSourceBean.publishOrganizationChange("DELETE", organization.getId());
    }
}
