package com.farms4life2016.chapter03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    
    @Bean
    WaifumonServicesTests waifumonServicesTests() {
        return new WaifumonServicesTests();
    }
}
