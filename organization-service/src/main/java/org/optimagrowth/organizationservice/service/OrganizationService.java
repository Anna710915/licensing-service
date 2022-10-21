package org.optimagrowth.organizationservice.service;

import brave.ScopedSpan;
import brave.Tracer;
import org.optimagrowth.organizationservice.events.source.SimpleSourceBean;
import org.optimagrowth.organizationservice.model.Organization;
import org.optimagrowth.organizationservice.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private SimpleSourceBean simpleSourceBean;
    @Autowired
    private Tracer tracer;

    @Transactional
    public Organization findById(String organizationId) {
        Optional<Organization> opt = null;
        ScopedSpan newSpan = tracer.startScopedSpan("getOrgDBCall");
        try{
            opt = organizationRepository.findById(organizationId);
            simpleSourceBean.publishOrganizationChange("GET", organizationId);
            if (opt.isEmpty()) {
                String message = String.format("unable to find an organization with theOrganization id %s",
                        organizationId);
                logger.error(message);
                throw new IllegalArgumentException(message);
            }
            logger.debug("Retrieving Organization Info: " + opt.get().toString());
        } finally {
            newSpan.tag("peer.service", "postgres");
            newSpan.annotate("Client received");
            newSpan.finish();
        }
        return opt.get();
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
