package com.flowdesk.core.ticket.dto;

import jakarta.validation.constraints.NotBlank;

public record AddCommentRequest(

        @NotBlank
        String body
) {}