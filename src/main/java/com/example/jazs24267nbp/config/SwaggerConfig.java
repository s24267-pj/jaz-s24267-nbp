package com.example.jazs24267nbp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Info info = new Info()
                .title("NBP REST API")
                .version("1.0")
                .description("Api wystawia endpoint do wyliczania sredniego kursu dla podanego przedzialu dat.");
        return new OpenAPI().info(info);

    }
}