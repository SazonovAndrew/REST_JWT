package com.sazonov.springboot.task.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE", "GET", "OPTION", "POST")
                .allowedHeaders("Content-Type", "accept", "Authorization")
                .exposedHeaders("Authorization")
                .allowCredentials(false).maxAge(36000000);
    }
}