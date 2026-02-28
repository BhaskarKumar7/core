package com.flowdesk.core.integration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "api_keys")
public class ApiKey {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID tenantId;

    private String name;

    private String hashedKey;

    private Boolean active = true;

    private Instant createdAt = Instant.now();
}
