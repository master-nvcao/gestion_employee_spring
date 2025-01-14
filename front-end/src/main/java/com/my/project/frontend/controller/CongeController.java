package com.my.project.frontend.controller;

import com.my.project.frontend.model.Conge;
import com.my.project.frontend.model.User;
import com.my.project.frontend.service.CongeService;
import com.my.project.frontend.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/conges")
public class CongeController {

    @Autowired
    private CongeService congeService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Display the list of all conges.
     */
    @GetMapping
    public String listConges(Model model, HttpSession session) {
        try {
            List<Conge> conges = congeService.getAllConges(session);
            model.addAttribute("conges", conges);
            return "rh-dashboard/conge/list";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load conges: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Display the form to create a new conge.
     */
    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        List<User> employees = employeeService.getAllUsers(session);
        model.addAttribute("employees", employees);
        model.addAttribute("conge", new Conge());
        return "rh-dashboard/conge/create";
    }

    /**
     * Handle the creation of a new conge.
     */
    @PostMapping("/create")
    public String createConge(@ModelAttribute("conge") Conge conge, HttpSession session, Model model) {
        try {
            congeService.createConge(conge, session);
            return "redirect:/conges";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create conge: " + e.getMessage());
            return "rh-dashboard/conge/create";
        }
    }

    /**
     * Display the form to edit an existing conge.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        try {
            Conge conge = congeService.getCongeById(id, session);
            List<User> employees = employeeService.getAllUsers(session);
            model.addAttribute("employees", employees);
            model.addAttribute("conge", conge);
            return "rh-dashboard/conge/edit";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load conge details: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Handle the update of an existing conge.
     */
    @PostMapping("/edit/{id}")
    public String updateConge(@PathVariable Long id, @ModelAttribute("conge") Conge conge, HttpSession session, Model model) {
        try {
            congeService.updateConge(id, conge, session);
            return "redirect:/conges";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update conge: " + e.getMessage());
            return "rh-dashboard/conge/edit";
        }
    }

    /**
     * Handle the deletion of a conge.
     */
    @GetMapping("/delete/{id}")
    public String deleteConge(@PathVariable Long id, HttpSession session, Model model) {
        try {
            congeService.deleteConge(id, session);
            return "redirect:/conges";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to delete conge: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Display the conge details.
     */
    @GetMapping("/{id}")
    public String showConge(@PathVariable Long id, Model model, HttpSession session) {
        try {
            Conge conge = congeService.getCongeById(id, session);
            model.addAttribute("conge", conge);
            return "rh-dashboard/conge/view";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load conge details: " + e.getMessage());
            return "error";
        }
    }
}
