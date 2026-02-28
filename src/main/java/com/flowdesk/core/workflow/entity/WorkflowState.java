package com.flowdesk.core.workflow.entity;

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
@Table(name = "workflow_states")
public class WorkflowState extends BaseEntity {

    @Column(nullable = false)
    private UUID workflowId;

    @Column(nullable = false)
    private String name;   // OPEN, IN_PROGRESS, DONE
}
