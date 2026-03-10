package com.flowdesk.core.ticket.repository;

import com.flowdesk.core.ticket.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findByEntityTypeAndEntityId(String entityType, UUID entityId);
}