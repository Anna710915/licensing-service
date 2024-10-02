package org.optimagrowth.organizationservice.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "organizations")
@Getter @Setter
public class Organization {

    @Id
    @Column(name = "organization_id", nullable = false)
    String id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "contact_name", nullable = false)
    String contractName;

    @Column(name = "contact_email", nullable = false)
    String contractEmail;

    @Column(name = "contact_phone", nullable = false)
    String contactPhone;
}
