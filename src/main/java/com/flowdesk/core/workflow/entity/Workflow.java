package com.flowdesk.core.workflow.entity;

import com.flowdesk.core.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "workflows")
public class Workflow extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String description;
}
