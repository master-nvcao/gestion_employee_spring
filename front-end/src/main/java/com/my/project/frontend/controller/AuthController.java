package com.my.project.frontend.controller;

import com.my.project.frontend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login"; // Renders login.html
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        try {
            // Call AuthService to authenticate
            Map<String, String> response = authService.login(email, password);

            // Store token and role in session
            session.setAttribute("token", response.get("token"));
            session.setAttribute("role", response.get("role"));
            session.setAttribute("id", response.get("id"));

            // Redirect based on role
            if ("ROLE_RH".equals(response.get("role"))) {
                return "redirect:/rh-dashboard";
            } else {
                return "redirect:/employee-dashboard";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Invalid credentials. Please try again.");
            return "auth/login"; // Renders login.html with an error message
        }
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/login"; // Redirect to login page
    }

    @GetMapping("/")
    public String showHomePage(Model model, HttpSession session) {

        // Redirect based on role
        if ("ROLE_RH".equals(session.getAttribute("role"))) {
            return "redirect:/rh-dashboard";
        } else {
            return "redirect:/employee-dashboard";
        }
    }

    @GetMapping("/rh-dashboard")
    public String showRhDashboard(Model model) {
        return "rh-dashboard/dashboard";
    }

    @GetMapping("/employee-dashboard")
    public String showEmployeeDashboard(Model model) {
        return "employee-dashboard/dashboard";
    }



}
