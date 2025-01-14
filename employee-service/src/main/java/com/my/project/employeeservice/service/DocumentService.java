package com.my.project.employeeservice.service;

import com.my.project.employeeservice.model.Document;
import com.my.project.employeeservice.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document updateDocument(Long id, Document updatedDocument) {
        Document existingDocument = getDocumentById(id);
        existingDocument.setNomDocument(updatedDocument.getNomDocument());
        existingDocument.setType(updatedDocument.getType());
        existingDocument.setStatut(updatedDocument.getStatut());
        existingDocument.setCommentaire(updatedDocument.getCommentaire());
        return documentRepository.save(existingDocument);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}

