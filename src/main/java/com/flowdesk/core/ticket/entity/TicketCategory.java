package com.flowdesk.core.ticket.entity;

import com.flowdesk.core.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ticket_categories",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "name"})
        })
public class TicketCategory extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;
}
