package com.flowdesk.core.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tags",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "name"})
        })
public class Tag extends BaseEntity {

    @Column(nullable = false)
    private String name;
}