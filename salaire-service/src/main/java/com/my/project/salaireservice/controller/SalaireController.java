package com.my.project.salaireservice.controller;

import com.my.project.salaireservice.model.Salaire;
import com.my.project.salaireservice.service.SalaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salaires")
public class SalaireController {

    @Autowired
    private SalaireService salaireService;

    @GetMapping
    public List<Salaire> getAllSalaires() {
        return salaireService.getAllSalaires();
    }

    @GetMapping("/{id}")
    public Salaire getSalaireById(@PathVariable Long id) {
        return salaireService.getSalaireById(id);
    }

    @PostMapping
    public Salaire createSalaire(@RequestBody Salaire salaire) {
        return salaireService.createSalaire(salaire);
    }

    @PutMapping("/{id}")
    public Salaire updateSalaire(@PathVariable Long id, @RequestBody Salaire salaire) {
        return salaireService.updateSalaire(id, salaire);
    }

    @DeleteMapping("/{id}")
    public void deleteSalaire(@PathVariable Long id) {
        salaireService.deleteSalaire(id);
    }

//    @GetMapping("/calculate/{id}")
//    public Double calculateSalaire(@PathVariable Long id) {
//        Salaire salaire = salaireService.getSalaireById(id);
//
//        // Business logic for salary calculation (example):
//        Double totalSalaire = salaire.getMontant()
//                + (salaire.getHeuresSupplementaires() * 10) // Assuming 10 per overtime hour
//                + salaire.getPrimes()
//                - (salaire.getAbsences() * 50); // Assuming 50 deducted per absence
//
//        return totalSalaire;
//    }
}

