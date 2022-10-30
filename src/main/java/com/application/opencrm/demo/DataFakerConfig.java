package com.application.opencrm.demo;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataFakerConfig {

    @Bean
    public Faker faker() {
        return new Faker(Locale.ENGLISH);
    }

}
