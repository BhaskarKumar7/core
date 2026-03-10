package com.flowdesk.core.ticket.controller;

import com.flowdesk.core.ticket.dto.*;
import com.flowdesk.core.ticket.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public TicketResponse create(@Valid @RequestBody TicketCreateRequest request,
                                 @AuthenticationPrincipal Jwt jwt) {

        UUID userId = UUID.fromString(jwt.getSubject());
        UUID tenantId = UUID.fromString(jwt.getClaim("tenant_id"));

        return ticketService.createTicket(request, userId, tenantId);
    }

    @PostMapping("/{id}/assign")
    public void assign(@PathVariable UUID id,
                       @Valid @RequestBody AssignTicketRequest request,
                       @AuthenticationPrincipal Jwt jwt) {

        UUID userId = UUID.fromString(jwt.getSubject());
        UUID tenantId = UUID.fromString(jwt.getClaim("tenant_id"));

        ticketService.assignTicket(id, request, userId, tenantId);
    }

    @PostMapping("/{id}/comments")
    public void comment(@PathVariable UUID id,
                        @Valid @RequestBody AddCommentRequest request,
                        @AuthenticationPrincipal Jwt jwt) {

        UUID userId = UUID.fromString(jwt.getSubject());
        UUID tenantId = UUID.fromString(jwt.getClaim("tenant_id"));

        ticketService.addComment(id, request, userId, tenantId);
    }
}