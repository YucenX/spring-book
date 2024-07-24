package com.farms4life2016.chapter03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.farms4life2016.chapter03.mem02.SimpleNormalizer;
import com.farms4life2016.chapter03.mem02.WaifumonService2;

/**
 * this is the same config as the 02 XML, but in Java instead
 */
@Configuration
public class Configuration8 {
    @Bean
    Normalizer normalizer() {
        return new SimpleNormalizer();
    }

    @Bean
    WaifumonService waifumonService() {
        return new WaifumonService2();
    }

    // seems to be a little more complicated to set up compared to XML files
}
