package com.my.project.frontend.controller;

import com.my.project.frontend.model.Contrat;
import com.my.project.frontend.model.User;
import com.my.project.frontend.service.ContratService;
import com.my.project.frontend.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contrats")
public class ContratController {

    @Autowired
    private ContratService contratService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Display the list of all contracts.
     */
    @GetMapping
    public String listContrats(Model model, HttpSession session) {
        try {
            List<Contrat> contrats = contratService.getAllContrats(session);
            model.addAttribute("contrats", contrats);
            return "rh-dashboard/contrat/list";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load contracts: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String showContrat(@PathVariable Long id, Model model, HttpSession session) {
        try {
            Contrat contrat = contratService.getContratById(id, session);
            model.addAttribute("contrat", contrat);
            return "rh-dashboard/contrat/view";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load employee details: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Display the form to create a new contract.
     */
    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        List<User> employees = employeeService.getAllUsers(session);
        model.addAttribute("employees", employees);
        model.addAttribute("contrat", new Contrat());
        return "rh-dashboard/contrat/create";
    }

    /**
     * Handle the creation of a new contract.
     */
    @PostMapping("/create")
    public String createContrat(@ModelAttribute("contrat") Contrat contrat, HttpSession session, Model model) {
        try {
            contratService.createContrat(contrat, session);
            return "redirect:/contrats";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create contract: " + e.getMessage());
            return "rh-dashboard/contrat/create";
        }
    }

    /**
     * Display the form to edit an existing contract.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        try {
            Contrat contrat = contratService.getContratById(id, session);
            List<User> employees = employeeService.getAllUsers(session);
            model.addAttribute("employees", employees);
            model.addAttribute("contrat", contrat); // Ensure contrat is available for the form
            return "rh-dashboard/contrat/edit";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load contract details: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Handle the update of an existing contract.
     */
    @PostMapping("/edit/{id}")
    public String updateContrat(@PathVariable Long id, @ModelAttribute("contrat") Contrat contrat, HttpSession session, Model model) {
        try {
            contratService.updateContrat(id, contrat, session);
            return "redirect:/contrats";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update contract: " + e.getMessage());
            return "rh-dashboard/contrat/edit";
        }
    }

    /**
     * Handle the deletion of a contract.
     */
    @GetMapping("/delete/{id}")
    public String deleteContrat(@PathVariable Long id, HttpSession session, Model model) {
        try {
            contratService.deleteContrat(id, session);
            return "redirect:/contrats";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to delete contract: " + e.getMessage());
            return "error";
        }
    }
}
