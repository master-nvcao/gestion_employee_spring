package com.my.project.salaireservice.service;

import com.my.project.salaireservice.model.Salaire;
import com.my.project.salaireservice.repository.SalaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaireService {

    @Autowired
    private SalaireRepository salaireRepository;

    public List<Salaire> getAllSalaires() {
        return salaireRepository.findAll();
    }

    public Salaire getSalaireById(Long id) {
        return salaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salaire not found"));
    }

    public Salaire createSalaire(Salaire salaire) {
        return salaireRepository.save(salaire);
    }

    public Salaire updateSalaire(Long id, Salaire updatedSalaire) {
        Salaire existingSalaire = getSalaireById(id);
        existingSalaire.setHeuresSupplementaires(updatedSalaire.getHeuresSupplementaires());
        existingSalaire.setMontant(updatedSalaire.getMontant());
        existingSalaire.setDate(updatedSalaire.getDate());
        existingSalaire.setPrimes(updatedSalaire.getPrimes());
        existingSalaire.setAbsences(updatedSalaire.getAbsences());
        return salaireRepository.save(existingSalaire);
    }

    public void deleteSalaire(Long id) {
        salaireRepository.deleteById(id);
    }
}

