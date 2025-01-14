package com.my.project.employeeservice.service;

import com.my.project.employeeservice.model.Contrat;
import com.my.project.employeeservice.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    public Contrat getContratById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat not found"));
    }

    public Contrat createContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public Contrat updateContrat(Long id, Contrat updatedContrat) {
        Contrat existingContrat = getContratById(id);
        existingContrat.setDateDebut(updatedContrat.getDateDebut());
        existingContrat.setDateFin(updatedContrat.getDateFin());
        existingContrat.setType(updatedContrat.getType());
        return contratRepository.save(existingContrat);
    }

    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }
}

