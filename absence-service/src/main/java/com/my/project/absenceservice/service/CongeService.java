package com.my.project.absenceservice.service;

import com.my.project.absenceservice.model.Conge;
import com.my.project.absenceservice.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongeService {

    @Autowired
    private CongeRepository congeRepository;

    public List<Conge> getAllConges() {
        return congeRepository.findAll();
    }

    public Conge getCongeById(Long id) {
        return congeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conge not found"));
    }

    public Conge createConge(Conge conge) {
        return congeRepository.save(conge);
    }

    public Conge updateConge(Long id, Conge updatedConge) {
        Conge existingConge = getCongeById(id);
        existingConge.setDateDebut(updatedConge.getDateDebut());
        existingConge.setDateFin(updatedConge.getDateFin());
        existingConge.setNombreDeJours(updatedConge.getNombreDeJours());
        existingConge.setMotif(updatedConge.getMotif());
        existingConge.setStatus(updatedConge.getStatus());
        existingConge.setCommentaire(updatedConge.getCommentaire());
        return congeRepository.save(existingConge);
    }

    public void deleteConge(Long id) {
        congeRepository.deleteById(id);
    }
}
