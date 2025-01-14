package com.my.project.absenceservice.controller;

import com.my.project.absenceservice.model.Conge;
import com.my.project.absenceservice.service.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conges")
public class CongeController {

    @Autowired
    private CongeService congeService;

    @GetMapping
    public List<Conge> getAllConges() {
        return congeService.getAllConges();
    }

    @GetMapping("/{id}")
    public Conge getCongeById(@PathVariable Long id) {
        return congeService.getCongeById(id);
    }

    @PostMapping
    public Conge createConge(@RequestBody Conge conge) {
        return congeService.createConge(conge);
    }

    @PutMapping("/{id}")
    public Conge updateConge(@PathVariable Long id, @RequestBody Conge conge) {
        return congeService.updateConge(id, conge);
    }

    @DeleteMapping("/{id}")
    public void deleteConge(@PathVariable Long id) {
        congeService.deleteConge(id);
    }

    @PutMapping("/{id}/approve")
    public Conge approveConge(@PathVariable Long id) {
        Conge conge = congeService.getCongeById(id);
        conge.setStatus("Approved");
        return congeService.updateConge(id, conge);
    }

    @PutMapping("/{id}/reject")
    public Conge rejectConge(@PathVariable Long id, @RequestParam String commentaire) {
        Conge conge = congeService.getCongeById(id);
        conge.setStatus("Rejected");
        conge.setCommentaire(commentaire);
        return congeService.updateConge(id, conge);
    }
}

