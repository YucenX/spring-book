package com.farms4life2016.chapter07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * I suppose that this is like a Driver class
 * SpringBootApplication - this annotation implies autoconfiguration 
 *   and component scanning in the same package (and its substrees?)
 */
@SpringBootApplication
public class Chapter7Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter7Application.class, args);
    }
}
