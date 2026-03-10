package com.flowdesk.core.integration.openfga;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final OpenFgaClient openFgaClient;

    // -----------------------------
    // Permission Check
    // -----------------------------

    public void checkPermission(UUID userId,
                                String permission,
                                String objectType,
                                UUID objectId) {

        boolean allowed = openFgaClient.check(
                "user:" + userId,
                permission,
                objectType + ":" + objectId
        );

        if (!allowed) {
            throw new RuntimeException("Forbidden: insufficient permissions");
        }
    }

    // -----------------------------
    // Relationship Writers
    // -----------------------------

    public void addCreator(UUID ticketId, UUID userId) {
        openFgaClient.writeTuple(
                "ticket:" + ticketId,
                "creator",
                "user:" + userId
        );
    }

    public void addAssignee(UUID ticketId, UUID userId) {
        openFgaClient.writeTuple(
                "ticket:" + ticketId,
                "assignee",
                "user:" + userId
        );
    }

    public void linkTicketToTenant(UUID ticketId, UUID tenantId) {
        openFgaClient.writeTuple(
                "ticket:" + ticketId,
                "parent_tenant",
                "tenant:" + tenantId
        );
    }
}