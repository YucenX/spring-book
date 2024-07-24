package com.farms4life2016.chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * a simple realization of the Furry class
 * with a constructor to set the animal class
 */
class ConcreteFurry extends Furry {
    ConcreteFurry(String animal) {
        this.animal = animal;
    }
}

@ContextConfiguration(locations = "/config-02.xml")
public class TestLifecycle02 extends AbstractTestNGSpringContextTests {
    @Autowired
    ApplicationContext context;

    @Test
    public void validateConstruction() {
        Furry f1 = context.getBean(Furry.class);
        assertEquals(f1.getAnimal(), "fennec fox");
    }
    @Test
    public void validateSecondObjectScan() {
        // we can look for the instance with anything
        // unique in its hierarchy.
        ConcreteFurry f2 = context.getBean(ConcreteFurry.class);
        assertEquals(f2.getAnimal(), "fennec fox");
    }
    
}
