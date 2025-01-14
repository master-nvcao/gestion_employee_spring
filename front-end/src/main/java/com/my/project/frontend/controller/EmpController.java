package com.my.project.frontend.controller;

import com.my.project.frontend.model.Conge;
import com.my.project.frontend.model.User;
import com.my.project.frontend.service.AuthService;
import com.my.project.frontend.service.CongeService;
import com.my.project.frontend.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CongeService congeService;

    @GetMapping("/details-employee")
    public String detailsemployee(Model model, HttpSession session) {
        User user = employeeService.getUserById(Long.valueOf((Integer) session.getAttribute("id")), session);

        model.addAttribute("employee", user);

        return "employee-dashboard/details-employee";
    }

    @GetMapping("/demande-conge")
    public String demandeconge(Model model, HttpSession session) {
        User user = employeeService.getUserById(Long.valueOf((Integer) session.getAttribute("id")), session);
        model.addAttribute("employee", user);
        model.addAttribute("conge", new Conge());
        return "employee-dashboard/demande-conge";
    }

    @PostMapping("/demande-conge")
    public String createConge(@ModelAttribute("conge") Conge conge, HttpSession session, Model model) {
        try {
            User user = employeeService.getUserById(Long.valueOf((Integer) session.getAttribute("id")), session);
            conge.setUser(user);
            conge.setStatus("En cours");
            congeService.createConge(conge, session);
            return "redirect:/demande-conge";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create conge: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/list-conge")
    public String listconges(Model model, HttpSession session) {
        User user = employeeService.getUserById(Long.valueOf((Integer) session.getAttribute("id")), session);
        List<Conge>  conges = congeService.getAllConges(session);
        List<Conge> uc = new ArrayList<Conge>();

        for(Conge conge : conges){
            if(conge.getUser().getId().equals(user.getId())){
                uc.add(conge);
            }
        }
        model.addAttribute("employee", user);
        model.addAttribute("conges", uc);
        return "employee-dashboard/list-conge";
    }


}
