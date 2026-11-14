package com.impact.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AnnouncementController {
    private final String message;

    public AnnouncementController(
            @Value("${app.maintenance-message}") String message){
        this.message = message;
    }

    @GetMapping("/api/announcement")
        public Map<String, String>  announcement() {
            return Map.of("Message", message);
        }
    }
