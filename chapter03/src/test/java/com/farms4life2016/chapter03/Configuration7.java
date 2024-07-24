package com.farms4life2016.chapter03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.farms4life2016.chapter03.mem01.WaifumonService1;

/**
 * whereas config 5 and 6 were almost entirely XML based, the next few
 * configurations will be almost entirely Java-based
 */
@Configuration
public class Configuration7 {

    /**
     * this will create a bean for a WaifumonService instance
     * @return
     */
    @Bean
    WaifumonService waifumonService() {
        return new WaifumonService1();
    }
    
}
