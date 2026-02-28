package com.flowdesk.core.audit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID tenantId;

    private UUID userId;

    private String action;

    private String entity;

    private UUID entityId;

    @Column(columnDefinition = "TEXT")
    private String data;

    private Instant createdAt = Instant.now();
}
