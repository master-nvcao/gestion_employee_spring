package com.my.project.frontend.service;

import com.my.project.frontend.model.Presence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Service
public class PresenceService {

    @Autowired
    private AuthService authService;

    private final String PRESENCE_API_BASE = "/presences";

    public List<Presence> getAllPresences(HttpSession session) {
        ResponseEntity<List<Presence>> response = authService.makeAuthenticatedRequest(
                PRESENCE_API_BASE,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<List<Presence>>() {}
        );
        return response.getBody();
    }

    public Presence getPresenceById(Long id, HttpSession session) {
        ResponseEntity<Presence> response = authService.makeAuthenticatedRequest(
                PRESENCE_API_BASE + "/" + id,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<Presence>() {}
        );
        return response.getBody();
    }

    public Presence createPresence(Presence presence, HttpSession session) {
        ResponseEntity<Presence> response = authService.makeAuthenticatedRequest(
                PRESENCE_API_BASE,
                HttpMethod.POST,
                presence,
                session,
                new ParameterizedTypeReference<Presence>() {}
        );
        return response.getBody();
    }

    public Presence updatePresence(Long id, Presence presence, HttpSession session) {
        ResponseEntity<Presence> response = authService.makeAuthenticatedRequest(
                PRESENCE_API_BASE + "/" + id,
                HttpMethod.PUT,
                presence,
                session,
                new ParameterizedTypeReference<Presence>() {}
        );
        return response.getBody();
    }

    public void deletePresence(Long id, HttpSession session) {
        authService.makeAuthenticatedRequest(
                PRESENCE_API_BASE + "/" + id,
                HttpMethod.DELETE,
                null,
                session,
                new ParameterizedTypeReference<Void>() {}
        );
    }
}
