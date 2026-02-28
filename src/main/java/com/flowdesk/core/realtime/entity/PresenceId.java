package com.flowdesk.core.realtime.entity;

import java.io.Serializable;
import java.util.UUID;

public class PresenceId implements Serializable {

    private UUID tenantId;
    private UUID userId;
}
