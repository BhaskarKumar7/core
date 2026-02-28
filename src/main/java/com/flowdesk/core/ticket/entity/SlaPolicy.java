package com.flowdesk.core.ticket.entity;

import com.flowdesk.core.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sla_policies")
public class SlaPolicy extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private Integer responseMinutes;

    private Integer resolutionMinutes;

    private String priority; // HIGH, MEDIUM, LOW
}
