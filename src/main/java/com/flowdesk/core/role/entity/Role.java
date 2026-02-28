package com.flowdesk.core.role.entity;

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
@Table(name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"tenant_id", "name"})
        })
public class Role extends BaseEntity {

    @Column(nullable = false)
    private String name;   // ADMIN, AGENT, USER
}
