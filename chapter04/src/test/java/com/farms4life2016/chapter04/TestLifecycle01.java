package com.farms4life2016.chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * simple realization of our abstract class
 */
class FoxFurry extends Furry {
    public FoxFurry() {
        super.setAnimal("fox");
    }
}

@ContextConfiguration(locations = "/config-01.xml")
public class TestLifecycle01 extends AbstractTestNGSpringContextTests {
    @Autowired
    ApplicationContext context;

    // stores the names of our beans + whether or not they are singletons
    @DataProvider
    Object[][] getReferences() {
        return new Object[][] {
            {"single", true},
            {"proto", false}
        };
    }

    @Test(dataProvider = "getReferences")
    public void testReferenceTypes(String name, boolean singleton) {
        // in this test, we visualize the differences between singletons and prototypes
        // first we create a Furry and store its animal (i.e. "fox")
        Furry f1 = context.getBean(name, Furry.class);
        String defaultValue = f1.getAnimal();

        // then change the animal and create a second furry
        f1.setAnimal("fennec fox"); 
        Furry f2 = context.getBean(name, Furry.class);
        
        // if the bean is a singleton, we expect the defaultValue to be different from 
        // the second Furry's animal, since we just changed it
        if (singleton) {
            assertSame(f1, f2); // however, f1 and f2 should point to the same object
            assertEquals(f1, f2);
            assertNotEquals(defaultValue, f2.getAnimal());
        } else {
            // for prototypes, we expect the opposite to happen:
            // f1 and f2 point at different objects
            // default should equal f2's animal since we did not change f2's animal
            assertNotSame(f1, f2);
            assertNotEquals(f1, f2);
            assertEquals(defaultValue, f2.getAnimal());
        }
    }
}
