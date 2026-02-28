package com.flowdesk.core.workflow.entity;

import com.flowdesk.core.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "workflow_transitions")
public class WorkflowTransition extends BaseEntity {

    private UUID workflowId;

    private UUID fromStateId;

    private UUID toStateId;

    private String actionName; // START, RESOLVE, CLOSE
}
