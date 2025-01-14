package com.my.project.frontend.controller;

import com.my.project.frontend.model.Salaire;
import com.my.project.frontend.model.User;
import com.my.project.frontend.service.SalaireService;
import com.my.project.frontend.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/salaires")
public class SalaireController {

    @Autowired
    private SalaireService salaireService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Display the list of all salaires.
     */
    @GetMapping
    public String listSalaires(Model model, HttpSession session) {
        try {
            List<Salaire> salaires = salaireService.getAllSalaires(session);
            model.addAttribute("salaires", salaires);
            return "rh-dashboard/salaire/list";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load salaires: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Display the form to create a new salaire.
     */
    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        List<User> employees = employeeService.getAllUsers(session);
        model.addAttribute("employees", employees);
        model.addAttribute("salaire", new Salaire());
        return "rh-dashboard/salaire/create";
    }

    /**
     * Handle the creation of a new salaire.
     */
    @PostMapping("/create")
    public String createSalaire(@ModelAttribute("salaire") Salaire salaire, HttpSession session, Model model) {
        try {
            salaireService.createSalaire(salaire, session);
            return "redirect:/salaires";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create salaire: " + e.getMessage());
            return "rh-dashboard/salaire/create";
        }
    }

    /**
     * Display the form to edit an existing salaire.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        try {
            Salaire salaire = salaireService.getSalaireById(id, session);
            List<User> employees = employeeService.getAllUsers(session);
            model.addAttribute("employees", employees);
            model.addAttribute("salaire", salaire);
            return "rh-dashboard/salaire/edit";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load salaire details: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Handle the update of an existing salaire.
     */
    @PostMapping("/edit/{id}")
    public String updateSalaire(@PathVariable Long id, @ModelAttribute("salaire") Salaire salaire, HttpSession session, Model model) {
        try {
            salaireService.updateSalaire(id, salaire, session);
            return "redirect:/salaires";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update salaire: " + e.getMessage());
            return "rh-dashboard/salaire/edit";
        }
    }

    /**
     * Handle the deletion of a salaire.
     */
    @GetMapping("/delete/{id}")
    public String deleteSalaire(@PathVariable Long id, HttpSession session, Model model) {
        try {
            salaireService.deleteSalaire(id, session);
            return "redirect:/salaires";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to delete salaire: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Display the salaire details.
     */
    @GetMapping("/{id}")
    public String showSalaire(@PathVariable Long id, Model model, HttpSession session) {
        try {
            Salaire salaire = salaireService.getSalaireById(id, session);
            model.addAttribute("salaire", salaire);
            return "rh-dashboard/salaire/view";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load salaire details: " + e.getMessage());
            return "error";
        }
    }
}
