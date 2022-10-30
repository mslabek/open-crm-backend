package com.application.opencrm.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for disabling most security measures. This can be used for quick manual tests but by
 * definition should not be used in insecure environments.
 */
@Profile("nosecurity")
@Configuration
public class SecurityDisabledConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().disable();

        http.headers().frameOptions().disable();

        http
            .authorizeRequests(authorize -> authorize
                                                .anyRequest()
                                                .permitAll()
            );
        // @formatter:on
        return http.build();
    }

}
