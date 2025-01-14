package com.my.project.frontend.service;

import com.my.project.frontend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private AuthService authService;

    private final String EMPLOYEE_API_BASE = "/users";

    public List<User> getAllUsers(HttpSession session) {
        ResponseEntity<List<User>> response = authService.makeAuthenticatedRequest(
                EMPLOYEE_API_BASE,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<List<User>>() {}
        );
        return response.getBody();
    }

    public User getUserById(Long id, HttpSession session) {
        ResponseEntity<User> response = authService.makeAuthenticatedRequest(
                EMPLOYEE_API_BASE + "/" + id,
                HttpMethod.GET,
                null,
                session,
                new ParameterizedTypeReference<User>() {}
        );
        return response.getBody();
    }

    public User createUser(User user, HttpSession session) {
        ResponseEntity<User> response = authService.makeAuthenticatedRequest(
                EMPLOYEE_API_BASE,
                HttpMethod.POST,
                user,
                session,
                new ParameterizedTypeReference<User>() {}
        );
        return response.getBody();
    }

    public User updateUser(Long id, User user, HttpSession session) {
        ResponseEntity<User> response = authService.makeAuthenticatedRequest(
                EMPLOYEE_API_BASE + "/" + id,
                HttpMethod.PUT,
                user,
                session,
                new ParameterizedTypeReference<User>() {}
        );
        return response.getBody();
    }

    public void deleteUser(Long id, HttpSession session) {
        authService.makeAuthenticatedRequest(
                EMPLOYEE_API_BASE + "/" + id,
                HttpMethod.DELETE,
                null,
                session,
                new ParameterizedTypeReference<Void>() {}
        );
    }
}
