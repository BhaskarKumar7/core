package com.flowdesk.core.ticket.dto;

import java.time.Instant;
import java.util.UUID;

public record TicketResponse(

        UUID id,
        String title,
        String description,
        String status,
        String priority,
        UUID createdBy,
        UUID assignedTo,
        Instant slaDueAt,
        Instant createdAt,
        Instant updatedAt
) {}