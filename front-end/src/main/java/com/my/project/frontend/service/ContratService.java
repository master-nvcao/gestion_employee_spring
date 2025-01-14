package com.my.project.frontend.service;

import com.my.project.frontend.model.Contrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Service
public class ContratService {

    @Autowired
    private AuthService authService;

    private final String CONTRAT_API_BASE = "/contrats";

    public List<Contrat> getAllContrats(HttpSession session) {
        ResponseEntity<List<Contrat>> response = authService.makeAuthenticatedRequest(
                CONTRAT_API_BASE,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<List<Contrat>>() {}
        );
        return response.getBody();
    }

    public Contrat getContratById(Long id, HttpSession session) {
        ResponseEntity<Contrat> response = authService.makeAuthenticatedRequest(
                CONTRAT_API_BASE + "/" + id,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<Contrat>() {}
        );
        return response.getBody();
    }

    public Contrat createContrat(Contrat contrat, HttpSession session) {
        ResponseEntity<Contrat> response = authService.makeAuthenticatedRequest(
                CONTRAT_API_BASE,
                HttpMethod.POST,
                contrat,
                session,
                new ParameterizedTypeReference<Contrat>() {}
        );
        return response.getBody();
    }

    public Contrat updateContrat(Long id, Contrat contrat, HttpSession session) {
        ResponseEntity<Contrat> response = authService.makeAuthenticatedRequest(
                CONTRAT_API_BASE + "/" + id,
                HttpMethod.PUT,
                contrat,
                session,
                new ParameterizedTypeReference<Contrat>() {}
        );
        return response.getBody();
    }

    public void deleteContrat(Long id, HttpSession session) {
        authService.makeAuthenticatedRequest(
                CONTRAT_API_BASE + "/" + id,
                HttpMethod.DELETE,
                null,
                session,
                new ParameterizedTypeReference<Void>() {}
        );
    }
}
