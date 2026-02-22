package com.dashboard.automation_dashboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a TODAS las rutas de tu API
                        .allowedOrigins("http://localhost:5173") // El puerto de tu React
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite todos los verbos
                        .allowedHeaders("*") // Permite cualquier cabecera
                        .allowCredentials(true);
            }
        };
    }
}
