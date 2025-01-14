package com.my.project.frontend.service;

import com.my.project.frontend.model.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private AuthService authService;

    private final String DEPARTEMENT_API_BASE = "/departements";

    public List<Departement> getAllDepartements(HttpSession session) {
        ResponseEntity<List<Departement>> response = authService.makeAuthenticatedRequest(
                DEPARTEMENT_API_BASE,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<List<Departement>>() {}
        );
        return response.getBody();
    }

    public Departement getDepartementById(Long id, HttpSession session) {
        ResponseEntity<Departement> response = authService.makeAuthenticatedRequest(
                DEPARTEMENT_API_BASE + "/" + id,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<Departement>() {}
        );
        return response.getBody();
    }

    public Departement createDepartement(Departement departement, HttpSession session) {
        ResponseEntity<Departement> response = authService.makeAuthenticatedRequest(
                DEPARTEMENT_API_BASE,
                HttpMethod.POST,
                departement,
                session,
                new ParameterizedTypeReference<Departement>() {}
        );
        return response.getBody();
    }

    public Departement updateDepartement(Long id, Departement departement, HttpSession session) {
        ResponseEntity<Departement> response = authService.makeAuthenticatedRequest(
                DEPARTEMENT_API_BASE + "/" + id,
                HttpMethod.PUT,
                departement,
                session,
                new ParameterizedTypeReference<Departement>() {}
        );
        return response.getBody();
    }

    public void deleteDepartement(Long id, HttpSession session) {
        authService.makeAuthenticatedRequest(
                DEPARTEMENT_API_BASE + "/" + id,
                HttpMethod.DELETE,
                null,
                session,
                new ParameterizedTypeReference<Void>() {}
        );
    }
}
