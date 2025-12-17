package com.example.BataPeru.config;

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
                registry.addMapping("/**") // Todas las rutas son públicas
                        .allowedOriginPatterns("*") // Permite todos los orígenes
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // Todos los métodos HTTP
                        .allowedHeaders("*") // Permite todos los headers
                        .allowCredentials(true) // Permite credenciales (cookies, authorization headers)
                        .exposedHeaders("*") // Expone todos los headers en la respuesta
                        .maxAge(3600); // Cache de preflight requests por 1 hora (3600 segundos)
            }
        };
    }
}

