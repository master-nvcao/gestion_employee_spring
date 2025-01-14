package com.my.project.employeeservice.service;

import com.my.project.employeeservice.model.Departement;
import com.my.project.employeeservice.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public Departement getDepartementById(Long id) {
        return departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departement not found"));
    }

    public Departement createDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    public Departement updateDepartement(Long id, Departement updatedDepartement) {
        Departement existingDepartement = getDepartementById(id);
        existingDepartement.setNomDepartement(updatedDepartement.getNomDepartement());
        existingDepartement.setDescription(updatedDepartement.getDescription());
        existingDepartement.setLocalisation(updatedDepartement.getLocalisation());
        existingDepartement.setCodeDepartement(updatedDepartement.getCodeDepartement());
        return departementRepository.save(existingDepartement);
    }

    public void deleteDepartement(Long id) {
        departementRepository.deleteById(id);
    }
}

