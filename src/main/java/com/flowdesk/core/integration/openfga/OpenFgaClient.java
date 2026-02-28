package com.flowdesk.core.integration.openfga;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class OpenFgaClient {

    private final WebClient webClient;
    private final String storeId;

    public OpenFgaClient(
            @Value("${openfga.api-url}") String apiUrl,
            @Value("${openfga.store-id}") String storeId
    ) {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .build();
        this.storeId = storeId;
    }

    public boolean check(String user, String relation, String object) {

        Map<String, Object> body = Map.of(
                "tuple_key", Map.of(
                        "user", user,
                        "relation", relation,
                        "object", object
                )
        );

        Map response =
                webClient.post()
                        .uri("/stores/" + storeId + "/check")
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();

        return Boolean.TRUE.equals(response.get("allowed"));
    }

    public void write(String user, String relation, String object) {

        Map<String, Object> body = Map.of(
                "writes", Map.of(
                        "tuple_keys", new Object[]{
                                Map.of(
                                        "user", user,
                                        "relation", relation,
                                        "object", object
                                )
                        }
                )
        );

        webClient.post()
                .uri("/stores/" + storeId + "/write")
                .bodyValue(body)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
