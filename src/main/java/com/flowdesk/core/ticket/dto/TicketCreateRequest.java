package com.flowdesk.core.ticket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketCreateRequest(

        @NotBlank
        String title,

        String description,

        @NotNull
        String priority
) {}