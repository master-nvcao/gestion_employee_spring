package com.my.project.frontend.controller;

import com.my.project.frontend.model.User;
import com.my.project.frontend.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Display the list of all employees.
     */
    @GetMapping
    public String listEmployees(Model model, HttpSession session) {
        try {
            List<User> employees = employeeService.getAllUsers(session);
            model.addAttribute("employees", employees);
            return "rh-dashboard/employee/list";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load employees: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String showEmployee(@PathVariable Long id, Model model, HttpSession session) {
        try {
            User employee = employeeService.getUserById(id, session);
            model.addAttribute("employee", employee);
            return "rh-dashboard/employee/view";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load employee details: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Display the form to create a new employee.
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new User());
        return "rh-dashboard/employee/create";
    }

    /**
     * Handle the creation of a new employee.
     */
    @PostMapping("/create")
    public String createEmployee(@ModelAttribute("employee") User employee, HttpSession session, Model model) {
        try {
            employeeService.createUser(employee, session);
            return "redirect:/employees";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create employee: " + e.getMessage());
            return "rh-dashboard/employee/create";
        }
    }

    /**
     * Display the form to edit an employee.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        try {
            User employee = employeeService.getUserById(id, session);
            model.addAttribute("employee", employee);
            return "rh-dashboard/employee/edit";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load employee details: " + e.getMessage());
            return "error";
        }
    }

    /**
     * Handle the update of an employee's details.
     */
    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") User employee, HttpSession session, Model model) {
        try {
            employeeService.updateUser(id, employee, session);
            return "redirect:/employees";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update employee: " + e.getMessage());
            return "rh-dashboard/employee/edit";
        }
    }

    /**
     * Handle the deletion of an employee.
     */
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, HttpSession session, Model model) {
        try {
            employeeService.deleteUser(id, session);
            return "redirect:/employees";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to delete employee: " + e.getMessage());
            return "error";
        }
    }
}
