package com.my.project.frontend.service;

import com.my.project.frontend.model.Salaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Service
public class SalaireService {

    @Autowired
    private AuthService authService;

    private final String SALAIRE_API_BASE = "/salaires";

    public List<Salaire> getAllSalaires(HttpSession session) {
        System.err.println("Here is the GET ALL SALAIRES ");
        ResponseEntity<List<Salaire>> response = authService.makeAuthenticatedRequest(
                SALAIRE_API_BASE,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<List<Salaire>>() {}
        );
        System.err.println("Response Body: " + response);
        System.err.println("Response Body: " + response.getBody());
        return response.getBody();
    }

    public Salaire getSalaireById(Long id, HttpSession session) {
        ResponseEntity<Salaire> response = authService.makeAuthenticatedRequest(
                SALAIRE_API_BASE + "/" + id,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<Salaire>() {}
        );
        return response.getBody();
    }

    public Salaire createSalaire(Salaire salaire, HttpSession session) {
        ResponseEntity<Salaire> response = authService.makeAuthenticatedRequest(
                SALAIRE_API_BASE,
                HttpMethod.POST,
                salaire,
                session,
                new ParameterizedTypeReference<Salaire>() {}
        );
        return response.getBody();
    }

    public Salaire updateSalaire(Long id, Salaire salaire, HttpSession session) {
        ResponseEntity<Salaire> response = authService.makeAuthenticatedRequest(
                SALAIRE_API_BASE + "/" + id,
                HttpMethod.PUT,
                salaire,
                session,
                new ParameterizedTypeReference<Salaire>() {}
        );
        return response.getBody();
    }

    public void deleteSalaire(Long id, HttpSession session) {
        authService.makeAuthenticatedRequest(
                SALAIRE_API_BASE + "/" + id,
                HttpMethod.DELETE,
                null,
                session,
                new ParameterizedTypeReference<Void>() {}
        );
    }

    // Uncomment and implement if needed for salary calculation logic
    /*
    public Double calculateSalaire(Long id, HttpSession session) {
        ResponseEntity<Double> response = authService.makeAuthenticatedRequest(
                SALAIRE_API_BASE + "/calculate/" + id,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<Double>() {}
        );
        return response.getBody();
    }
    */
}
