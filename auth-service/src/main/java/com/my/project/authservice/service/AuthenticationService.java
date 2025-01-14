package com.my.project.authservice.service;

import com.my.project.authservice.config.JwtUtil;
import com.my.project.authservice.model.User;
import com.my.project.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("Found user: " + user.getEmail());
        System.out.println("Stored password: " + user.getMotDePasse());
        System.out.println("Provided password: " + password);

        if (!passwordEncoder.matches(password, user.getMotDePasse())) {
            System.out.println("Password mismatch");
            throw new RuntimeException("Invalid password");
        }

        System.out.println("Authentication successful");
        return jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
    }
}

