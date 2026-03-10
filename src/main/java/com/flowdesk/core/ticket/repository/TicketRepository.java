package com.flowdesk.core.ticket.repository;

import com.flowdesk.core.ticket.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    Optional<Ticket> findByIdAndTenantId(UUID id, UUID tenantId);

    Page<Ticket> findByTenantId(UUID tenantId, Pageable pageable);
}