package com.application.opencrm.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI crmOpenApi() {
        return new OpenAPI().info(new Info().title("Simple CRM API")
                .description("Simple CRM application written in Spring")
                .license(new License().name("Apache 2.0")));
    }

}
