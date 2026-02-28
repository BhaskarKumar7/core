package com.flowdesk.core.ticket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ticket_tags",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"ticket_id", "tag_id"})
        })
public class TicketTag {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID ticketId;

    private UUID tagId;
}
