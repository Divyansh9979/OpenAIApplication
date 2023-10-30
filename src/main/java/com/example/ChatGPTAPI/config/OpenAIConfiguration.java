package com.example.ChatGPTAPI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfiguration {

    @Value("${openai.key}")
    private String apiKey;

    @Bean
    public RestTemplate template() {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add( (request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + this.apiKey);
            return execution.execute(request, body);
        });

        return restTemplate;
    }
}
