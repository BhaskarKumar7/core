package com.flowdesk.core.realtime.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "presence")
@IdClass(PresenceId.class)
public class Presence {

    @Id
    private UUID tenantId;

    @Id
    private UUID userId;

    private Instant lastSeenAt;

    private String status; // ONLINE, OFFLINE
}
