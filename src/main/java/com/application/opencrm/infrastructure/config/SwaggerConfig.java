package com.application.opencrm.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI crmOpenApi() {
        return new OpenAPI().info(new Info().title("Simple CRM API")
                .description("Simple CRM application written in Spring")
                .license(new License().name("Apache 2.0")))
                .components(getSwaggerComponents());
    }

    private Components getSwaggerComponents() {
        Components components = new Components();
        SecurityScheme scheme = new SecurityScheme();
        scheme.setType(SecurityScheme.Type.APIKEY);
        scheme.setIn(SecurityScheme.In.COOKIE);
        scheme.setName("JSESSIONID");
        components.addSecuritySchemes("cookieAuth", scheme);
        return components;
    }

}
