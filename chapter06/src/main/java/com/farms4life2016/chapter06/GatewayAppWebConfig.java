package com.farms4life2016.chapter06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * configuration file...
 * the first two annotations just tell MVC that this is a web config file
 */
@Configuration
@EnableWebMvc  // scans both chapter06 and chapter03.mem03 (which has our candidate + reason classes)
@ComponentScan(basePackages = {"com.farms4life2016.chapter06", "com.farms4life2016.chapter03.mem03"})
public class GatewayAppWebConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(mustacheViewResolver());
    }
    
    @Bean
    public ViewResolver mustacheViewResolver() {
        var viewResolver = new MustacheViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}