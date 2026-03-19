package com.clean.architeture.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Clean Architecture API")
                        .description("REST API implementing Clean Architecture with JPA, Hibernate, dynamic filters using Specification pattern and global exception handling")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Daniel Sismer")
                                .url("https://github.com/danielsismer/spring-clean-architecture")));
    }
}