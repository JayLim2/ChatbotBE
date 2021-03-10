package ru.sergei.komarov.chatbot.be.integrations;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HttpClient {

    public <T> T get(String url, Map<String, ?> urlVariables, Class<T> entityClass) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(
                url, entityClass, urlVariables
        );
        return responseEntity.getBody();
    }

    public <T> T post(String url, Object requestBody, Map<String, ?> urlVariables, Class<T> entityClass) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(
                url, requestBody, entityClass, urlVariables
        );
        return responseEntity.getBody();
    }

    public void put(String url, Object requestBody, Map<String, ?> urlVariables) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(
                url, requestBody, urlVariables
        );
    }

    public void delete(String url, Map<String, ?> urlVariables) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(
                url, urlVariables
        );
    }
}
