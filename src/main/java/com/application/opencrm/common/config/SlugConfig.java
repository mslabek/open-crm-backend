package com.application.opencrm.common.config;

import com.github.slugify.Slugify;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for customizing beans dealing with generating human-readable url slugs from strings.
 */
@Configuration
public class SlugConfig {

    @Bean
    public Slugify slugify() {
        return Slugify.builder()
                      .build();
    }

}
