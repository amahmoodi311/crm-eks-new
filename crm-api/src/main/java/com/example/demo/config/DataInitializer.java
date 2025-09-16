package com.example.demo.config;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        // Check if admin user already exists
        if (userRepo.findByUsername("admin") == null) {
            User adminUser = User.builder()
                    .name("Admin")
                    .email("admin@gmail.com")
                    .username("admin")
                    .password("admin@123")
                    .mobile("9087654321")
                    .build();
            
            userRepo.save(adminUser);
            System.out.println("Admin user created successfully!");
        }
    }
}