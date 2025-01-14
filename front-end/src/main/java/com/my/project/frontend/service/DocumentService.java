package com.my.project.frontend.service;

import com.my.project.frontend.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private AuthService authService;

    private final String DOCUMENT_API_BASE = "/documents";

    public List<Document> getAllDocuments(HttpSession session) {
        ResponseEntity<List<Document>> response = authService.makeAuthenticatedRequest(
                DOCUMENT_API_BASE,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<List<Document>>() {}
        );
        return response.getBody();
    }

    public Document getDocumentById(Long id, HttpSession session) {
        ResponseEntity<Document> response = authService.makeAuthenticatedRequest(
                DOCUMENT_API_BASE + "/" + id,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<Document>() {}
        );
        return response.getBody();
    }

    public Document createDocument(Document document, HttpSession session) {
        ResponseEntity<Document> response = authService.makeAuthenticatedRequest(
                DOCUMENT_API_BASE,
                HttpMethod.POST,
                document,
                session,
                new ParameterizedTypeReference<Document>() {}
        );
        return response.getBody();
    }

    public Document updateDocument(Long id, Document document, HttpSession session) {
        ResponseEntity<Document> response = authService.makeAuthenticatedRequest(
                DOCUMENT_API_BASE + "/" + id,
                HttpMethod.PUT,
                document,
                session,
                new ParameterizedTypeReference<Document>() {}
        );
        return response.getBody();
    }

    public void deleteDocument(Long id, HttpSession session) {
        authService.makeAuthenticatedRequest(
                DOCUMENT_API_BASE + "/" + id,
                HttpMethod.DELETE,
                null,
                session,
                new ParameterizedTypeReference<Void>() {}
        );
    }
}
