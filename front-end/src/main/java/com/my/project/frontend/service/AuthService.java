package com.my.project.frontend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${api.gateway.url}")
    private String apiGatewayUrl;

    public Map<String, String> login(String email, String password) {
        String url = apiGatewayUrl + "/auth/login";

        // Create the login request payload
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", email);
        loginRequest.put("password", password);

        try {
            // Send POST request to the API Gateway
            ResponseEntity<Map> response = restTemplate.postForEntity(url, loginRequest, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {

                return response.getBody(); // Contains "token" and "role"
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during login: " + e.getMessage());
        }
    }

    public <T> ResponseEntity<T> makeAuthenticatedRequest(
            String endpoint,
            HttpMethod method,
            Object body,
            HttpSession session,
            ParameterizedTypeReference<T> responseType) {

        String url = apiGatewayUrl + endpoint;

        // Retrieve the token from the session
        String token = (String) session.getAttribute("token");
        if (token == null) {
            throw new RuntimeException("User is not authenticated");
        }

        // Set the Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);

        System.err.println("Making the request "+requestEntity);

        // Make the request
        return restTemplate.exchange(url, method, requestEntity, responseType);
    }






}