package com.my.project.frontend.service;

import com.my.project.frontend.model.Conge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Service
public class CongeService {

    @Autowired
    private AuthService authService;

    private final String CONGE_API_BASE = "/conges";

    public List<Conge> getAllConges(HttpSession session) {
        ResponseEntity<List<Conge>> response = authService.makeAuthenticatedRequest(
                CONGE_API_BASE,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<List<Conge>>() {}
        );
        return response.getBody();
    }

    public Conge getCongeById(Long id, HttpSession session) {
        ResponseEntity<Conge> response = authService.makeAuthenticatedRequest(
                CONGE_API_BASE + "/" + id,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<Conge>() {}
        );
        return response.getBody();
    }

    public Conge createConge(Conge conge, HttpSession session) {
        ResponseEntity<Conge> response = authService.makeAuthenticatedRequest(
                CONGE_API_BASE,
                HttpMethod.POST,
                conge,
                session,
                new ParameterizedTypeReference<Conge>() {}
        );
        return response.getBody();
    }

    public Conge updateConge(Long id, Conge conge, HttpSession session) {
        ResponseEntity<Conge> response = authService.makeAuthenticatedRequest(
                CONGE_API_BASE + "/" + id,
                HttpMethod.PUT,
                conge,
                session,
                new ParameterizedTypeReference<Conge>() {}
        );
        return response.getBody();
    }

    public void deleteConge(Long id, HttpSession session) {
        authService.makeAuthenticatedRequest(
                CONGE_API_BASE + "/" + id,
                HttpMethod.DELETE,
                null,
                session,
                new ParameterizedTypeReference<Void>() {}
        );
    }

    public Conge approveConge(Long id, HttpSession session) {
        ResponseEntity<Conge> response = authService.makeAuthenticatedRequest(
                CONGE_API_BASE + "/" + id + "/approve",
                HttpMethod.PUT,
                null,
                session,
                new ParameterizedTypeReference<Conge>() {}
        );
        return response.getBody();
    }

    public Conge rejectConge(Long id, String commentaire, HttpSession session) {
        ResponseEntity<Conge> response = authService.makeAuthenticatedRequest(
                CONGE_API_BASE + "/" + id + "/reject?commentaire=" + commentaire,
                HttpMethod.PUT,
                null,
                session,
                new ParameterizedTypeReference<Conge>() {}
        );
        return response.getBody();
    }
}
