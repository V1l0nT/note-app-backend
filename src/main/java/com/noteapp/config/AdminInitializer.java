package com.noteapp.config;

import com.noteapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) {
        try {
            userService.createAdminUser("admin", "admin123");
            System.out.println("Администратор успешно создан");
        } catch (Exception e) {
            System.out.println("Администратор уже существует");
        }
    }
} 