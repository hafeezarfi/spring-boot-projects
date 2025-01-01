package com.hafeezarfi.springcoredemo.config;

import com.hafeezarfi.springcoredemo.common.Coach;
import com.hafeezarfi.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
