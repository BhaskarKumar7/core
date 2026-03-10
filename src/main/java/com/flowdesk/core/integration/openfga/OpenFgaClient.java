package com.flowdesk.core.integration.openfga;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OpenFgaClient {

    private final RestTemplate restTemplate;

    @Value("${openfga.api-url}")
    private String apiUrl;

    @Value("${openfga.store-id}")
    private String storeId;

    // -----------------------------------
    // Check Permission
    // -----------------------------------

    public boolean check(String user, String relation, String object) {

        String url = apiUrl + "/stores/" + storeId + "/check";

        Map<String, Object> body = Map.of(
                "tuple_key", Map.of(
                        "user", user,
                        "relation", relation,
                        "object", object
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        return Boolean.TRUE.equals(response.getBody().get("allowed"));
    }

    // -----------------------------------
    // Write Relationship Tuple
    // -----------------------------------

    public void writeTuple(String object, String relation, String user) {

        String url = apiUrl + "/stores/" + storeId + "/write";

        Map<String, Object> tuple = Map.of(
                "object", object,
                "relation", relation,
                "user", user
        );

        Map<String, Object> body = Map.of(
                "writes", Map.of(
                        "tuple_keys", new Object[]{tuple}
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
    }
}