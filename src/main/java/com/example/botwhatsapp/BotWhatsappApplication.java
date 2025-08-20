package com.example.botwhatsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Bot WhatsApp Spring Boot application.
 * 
 * - @SpringBootApplication tells Spring Boot:
 *   * scan this package and sub-packages for components (@Service, @RestController, etc.)
 *   * apply auto-configuration
 *   * enable configuration properties
 */
@SpringBootApplication
public class BotWhatsappApplication {
    public static void main(String[] args) {
        SpringApplication.run(BotWhatsappApplication.class, args);
    }
}
