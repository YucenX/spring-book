package com.farms4life2016.chapter03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class WaifumonServiceRunner {
    public static void main(String[] args) {

        /*
         * since we no longer have a bunch of XML files, we can't really use that annotations that
         * allowed us to list a bunch of XML file locations.
         * Instead, we have to list out all our @Configuration classes 
         * and shove it into AnnotationConfigApplicationContext
         */
        Class<?>[] configurations = new Class<?>[]{Configuration7.class, TestConfiguration.class};
        ApplicationContext context = new AnnotationConfigApplicationContext(configurations);

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
