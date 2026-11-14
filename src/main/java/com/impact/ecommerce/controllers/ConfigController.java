package com.impact.ecommerce.controllers;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ConfigController {
    private final Environment environment;
    private final String displayName;
    // constructor pentru a arata in ce mediu suntem
    public ConfigController (
        Environment environment,
        @Value("${app.display-name}") String displayName){
        this.environment = environment;
        this.displayName = displayName;
    }

    // cand accesam ruta -> ne arata informatii despre mediu
    @GetMapping("api/config/info")
    public ConfigInfo info(){
        return new ConfigInfo(
                displayName,
                List.of(environment.getActiveProfiles())
        );
    }

    public record ConfigInfo(String displayName, List<String> profiles){}

}
