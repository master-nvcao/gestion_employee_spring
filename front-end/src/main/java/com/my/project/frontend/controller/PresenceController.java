package com.my.project.frontend.controller;

import com.my.project.frontend.model.Presence;
import com.my.project.frontend.model.User;
import com.my.project.frontend.service.PresenceService;
import com.my.project.frontend.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/presences")
public class PresenceController {

    @Autowired
    private PresenceService presenceService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Display the list of all presences.
     */
    @GetMapping
    public String listPresences(Model model, HttpSession session) {
        try {
            List<Presence> presences = presenceService.getAllPresences(session);
            model.addAttribute("presences", presences);
            return "rh-dashboard/presence/list";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load presences: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Display the form to create a new presence.
     */
    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        List<User> employees = employeeService.getAllUsers(session);
        model.addAttribute("employees", employees);
        model.addAttribute("presence", new Presence());
        return "rh-dashboard/presence/create";
    }

    /**
     * Handle the creation of a new presence.
     */
    @PostMapping("/create")
    public String createPresence(@ModelAttribute("presence") Presence presence, HttpSession session, Model model) {
        try {
            presenceService.createPresence(presence, session);
            return "redirect:/presences";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create presence: " + e.getMessage());
            return "rh-dashboard/presence/create";
        }
    }

    /**
     * Display the form to edit an existing presence.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        try {
            Presence presence = presenceService.getPresenceById(id, session);
            List<User> employees = employeeService.getAllUsers(session);
            model.addAttribute("employees", employees);
            model.addAttribute("presence", presence);
            return "rh-dashboard/presence/edit";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load presence details: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Handle the update of an existing presence.
     */
    @PostMapping("/edit/{id}")
    public String updatePresence(@PathVariable Long id, @ModelAttribute("presence") Presence presence, HttpSession session, Model model) {
        try {
            presenceService.updatePresence(id, presence, session);
            return "redirect:/presences";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update presence: " + e.getMessage());
            return "rh-dashboard/presence/edit";
        }
    }

    /**
     * Handle the deletion of a presence.
     */
    @GetMapping("/delete/{id}")
    public String deletePresence(@PathVariable Long id, HttpSession session, Model model) {
        try {
            presenceService.deletePresence(id, session);
            return "redirect:/presences";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to delete presence: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Display the presence details.
     */
    @GetMapping("/{id}")
    public String showPresence(@PathVariable Long id, Model model, HttpSession session) {
        try {
            Presence presence = presenceService.getPresenceById(id, session);
            model.addAttribute("presence", presence);
            return "rh-dashboard/presence/view";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load presence details: " + e.getMessage());
            return "error";
        }
    }
}
