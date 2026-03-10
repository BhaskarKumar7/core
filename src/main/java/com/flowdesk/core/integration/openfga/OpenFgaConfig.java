package com.flowdesk.core.integration.openfga;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenFgaConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}