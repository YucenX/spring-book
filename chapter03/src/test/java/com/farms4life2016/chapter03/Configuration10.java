package com.farms4life2016.chapter03;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.farms4life2016.chapter03.mem03.CappyNormalizer;
import com.farms4life2016.chapter03.mem03.SecondNormalizer;
import com.farms4life2016.chapter03.mem04.WaifumonService4;

/**
 * this config demonstrates constructor injection using Java only, no XML.
 * this config corresponds to the 04 XML config
 */
@Configuration
public class Configuration10 {

    /**
     * creates the bean for the cappy normalizer 
     * @return
     */
    @Bean
    Normalizer cappy() {
        return new CappyNormalizer();
    }

    /**
     * creates the bean for the second normalizer, but
     * we purposefully don't name it "correctly" 
     * @return
     */
    @Bean
    Normalizer secondNormalizer() {
        return new SecondNormalizer();
    }

    /**
     * here is some constructor injection. we also let Spring handle the injection
     * of constructor arguments (which are also objects)
     * @param cappy
     * @param second
     * @return
     */
    @Bean 
    WaifumonService waifumonService( // more cursed-looking code
        Normalizer cappy,     // spring will automatically inject a cappy normalizer cuz cappy() creates a bean named "cappy"
        @Qualifier("secondNormalizer")  // use @Qualifier to reference the name of a bean that will be injected, in case param name != bean name
        Normalizer second
    ) {
        return new WaifumonService4(second, cappy);
    }
}
