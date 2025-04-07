package com.nrapendra.deepseek;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DeepSeekService {

    @Value("${deepseek.api.key}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String DEEPSEEK_URL = "https://api.deepseek.com/v1/chat/completions";
    public String getAIResponse(String userMessage) {
        ChatRequest request = new ChatRequest();
        request.setModel("deepseek-chat");
        request.setMessages(List.of(
                new ChatRequest.Message("user", userMessage)
        ));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<ChatResponse> response = restTemplate.exchange(
                DEEPSEEK_URL, HttpMethod.POST, entity, ChatResponse.class);
        return response.getBody()
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }
}


