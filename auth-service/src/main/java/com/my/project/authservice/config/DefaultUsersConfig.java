package com.my.project.authservice.config;

import com.my.project.authservice.model.User;
import com.my.project.authservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class DefaultUsersConfig {

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) { // Only insert users if the table is empty
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                User rhUser = new User();
                rhUser.setNom("RH");
                rhUser.setPrenom("Admin");
                rhUser.setEmail("rh@example.com");
                rhUser.setMotDePasse(passwordEncoder.encode("password123")); // Encrypted password
                rhUser.setRole("ROLE_RH");

                User employeeUser = new User();
                employeeUser.setNom("Employee");
                employeeUser.setPrenom("User");
                employeeUser.setEmail("employee@example.com");
                employeeUser.setMotDePasse(passwordEncoder.encode("password456")); // Encrypted password
                employeeUser.setRole("ROLE_EMPLOYEE");

                userRepository.saveAll(List.of(rhUser, employeeUser));
            }
        };
    }
}