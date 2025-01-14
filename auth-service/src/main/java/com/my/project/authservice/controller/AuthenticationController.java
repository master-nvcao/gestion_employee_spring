package com.my.project.authservice.controller;

import com.my.project.authservice.config.JwtUtil;
import com.my.project.authservice.model.User;
import com.my.project.authservice.model.dto.LoginRequest;
import com.my.project.authservice.repository.UserRepository;
import com.my.project.authservice.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository  userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        try {
            System.out.println("Login endpoint called with email: " + email);
            String token = authenticationService.authenticate(email, password);
            System.out.println("Token generated: " + token);

            User user = userRepository.findByEmail(email).orElse(null);
            String role = "ROLE_EMPLOYEE";
            Long id = 0L;
            if(user != null) {
                role = user.getRole();
                id = user.getId();
            }

            // Return token in response
            String finalRole = role;
            Long finalId = id;
            return ResponseEntity.ok(new HashMap<>() {{
                put("token", token);
                put("role", finalRole);
                put("id", finalId);
            }});
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String token) {
        try {
            Claims claims = new JwtUtil().validateToken(token);
            return ResponseEntity.ok(claims);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam String token) {
        try {
            // Validate the existing token
            Claims claims = jwtUtil.validateToken(token);

            // Generate a new token
            String newToken = jwtUtil.generateToken(
                    Long.parseLong(claims.get("id").toString()),
                    claims.getSubject(),
                    claims.get("role").toString()
            );

            // Return the new token
            return ResponseEntity.ok(new HashMap<>() {{
                put("token", newToken);
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

}


