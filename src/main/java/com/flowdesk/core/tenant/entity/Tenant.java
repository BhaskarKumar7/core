package com.flowdesk.core.tenant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String plan;    // FREE, PRO, ENTERPRISE

    @Column(nullable = false)
    private String status;  // ACTIVE, SUSPENDED
}
