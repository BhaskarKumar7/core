package com.flowdesk.core.notification.entity;

import com.flowdesk.core.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "notifications",
        indexes = {
                @Index(name = "idx_notification_user", columnList = "tenant_id,user_id")
        })
public class Notification extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String channel; // IN_APP, EMAIL, PUSH

    @Column(columnDefinition = "TEXT")
    private String payload;

    private Boolean isRead = false;
}
