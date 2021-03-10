package ru.sergei.komarov.chatbot.be.integrations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sergei.komarov.chatbot.be.models.Message;

import java.util.Collections;

@Service
public class AIIntegrationService {

    @Value("${ai.service}")
    private String aiServiceUrl;

    private final HttpClient httpClient;

    public AIIntegrationService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Message getAnswer(Message message) {
        return httpClient.post(
                getUrl("/api/chat/getAnswer"),
                message,
                Collections.emptyMap(),
                Message.class
        );
    }

    private String getUrl(String pathToApi) {
        return aiServiceUrl + pathToApi;
    }
}
