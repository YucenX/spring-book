package com.farms4life2016.chapter03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.farms4life2016.chapter03.mem03.CappyNormalizer;
import com.farms4life2016.chapter03.mem03.SecondNormalizer;
import com.farms4life2016.chapter03.mem03.WaifumonService3;

/**
 * this config operates exactly like the 03 XML but in Java form
 */
@Configuration
public class Configuration9 {

    /*
     * by default, the bean produced by a @Bean annotation will have a name
     * equal to the function name, i.e. cappy() will create a bean named "cappy".
     * 
     * however, sometimes you want to change this name because, for example, our
     * WaifumonService3 class is looking for a Normalizer named "second" and not
     * "secondNormalizer". Fortunately you can just specify a custom name in the 
     * bean annotation. 
     */

    /**
     * creates the bean for the cappy normalizer 
     * @return
     */
    @Bean
    Normalizer cappy() {
        return new CappyNormalizer();
    }

    /**
     * creates the bean for the second normalizer 
     * @return
     */
    @Bean(name="second")
    Normalizer secondNormalizer() {
        return new SecondNormalizer();
    }

    @Bean
    WaifumonService waifumonService() {
        return new WaifumonService3();
    }
}
