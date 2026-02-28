package com.flowdesk.core.integration.openfga;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final OpenFgaClient client;

    public AuthorizationService(OpenFgaClient client) {
        this.client = client;
    }

    public void checkPermission(String user, String relation, String object) {
        if (!client.check(user, relation, object)) {
            throw new RuntimeException("Access Denied");
        }
    }
}
