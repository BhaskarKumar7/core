package com.flowdesk.core.ticket.service;

import com.flowdesk.core.integration.openfga.AuthorizationService;
import com.flowdesk.core.ticket.dto.*;
import com.flowdesk.core.ticket.entity.Comment;
import com.flowdesk.core.ticket.entity.Ticket;
import com.flowdesk.core.ticket.repository.CommentRepository;
import com.flowdesk.core.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CommentRepository commentRepository;
    private final AuthorizationService authorizationService;

    public TicketResponse createTicket(TicketCreateRequest request,
                                       UUID currentUserId,
                                       UUID tenantId) {

        Ticket ticket = new Ticket();
        ticket.setTitle(request.title());
        ticket.setDescription(request.description());
        ticket.setPriority(request.priority());
        ticket.setStatus("OPEN");
        ticket.setCreatedBy(currentUserId);
        ticket.setTenantId(tenantId);

        Ticket saved = ticketRepository.save(ticket);

        // OpenFGA relationships
        authorizationService.addCreator(saved.getId(), currentUserId);
        authorizationService.linkTicketToTenant(saved.getId(), tenantId);

        return map(saved);
    }

    public void assignTicket(UUID ticketId,
                             AssignTicketRequest request,
                             UUID currentUserId,
                             UUID tenantId) {

        Ticket ticket = getTicketOrThrow(ticketId, tenantId);

        authorizationService.checkPermission(
                currentUserId,
                "can_assign",
                "ticket",
                ticketId
        );

        ticket.setAssignedTo(request.assigneeId());
        ticketRepository.save(ticket);

        authorizationService.addAssignee(ticketId, request.assigneeId());
    }

    public void addComment(UUID ticketId,
                           AddCommentRequest request,
                           UUID currentUserId,
                           UUID tenantId) {

        Ticket ticket = getTicketOrThrow(ticketId, tenantId);

        authorizationService.checkPermission(
                currentUserId,
                "can_comment",
                "ticket",
                ticketId
        );

        Comment comment = new Comment();
        comment.setEntityType("TICKET");
        comment.setEntityId(ticketId);
        comment.setAuthorId(currentUserId);
        comment.setBody(request.body());
        comment.setTenantId(tenantId);

        commentRepository.save(comment);
    }

    private Ticket getTicketOrThrow(UUID ticketId, UUID tenantId) {
        return ticketRepository.findByIdAndTenantId(ticketId, tenantId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    private TicketResponse map(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getCreatedBy(),
                ticket.getAssignedTo(),
                ticket.getSlaDueAt(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }
}