package com.flowdesk.core.incident.entity;

import com.flowdesk.core.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "incidents")
public class Incident extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String severity;   // LOW, MEDIUM, HIGH, CRITICAL

    private String status;     // OPEN, INVESTIGATING, RESOLVED

    private UUID reportedBy;

    private Instant resolvedAt;
}
