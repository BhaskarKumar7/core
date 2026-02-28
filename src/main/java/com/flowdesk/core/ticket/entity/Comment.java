package com.flowdesk.core.ticket.entity;

import com.flowdesk.core.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Column(name = "entity_type", nullable = false)
    private String entityType;  // TICKET, INCIDENT

    @Column(name = "entity_id", nullable = false)
    private UUID entityId;

    @Column(name = "author_id", nullable = false)
    private UUID authorId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;
}
