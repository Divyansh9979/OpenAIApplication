package com.example.ChatGPTAPI.controller;

import com.example.ChatGPTAPI.dto.ChatGPTRequest;
import com.example.ChatGPTAPI.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OpenAIController {

    @Value("${openai.api.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String getResponse(@RequestParam("prompt") String prompt) {

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(model, prompt);

        ChatGPTResponse chatGPTResponse = template.postForObject(url, chatGPTRequest, ChatGPTResponse.class);

        return chatGPTResponse.getChoices().get(0).getText();

    }
}
