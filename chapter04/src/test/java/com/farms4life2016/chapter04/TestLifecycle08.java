package com.farms4life2016.chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

class CatFurry extends Furry {
    public CatFurry() {
        super.animal = "cat";
    }
}

@Configuration
class Config08 {
    @Bean
    @Scope("singleton")  // again, this line is unnecessary cuz it is default behaviour
    public CatFurry single() {
        return new CatFurry();
    }

    @Bean 
    @Scope("prototype")
    public CatFurry proto() {
        return new CatFurry();
    }
}

@ContextConfiguration(classes=Config08.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TestLifecycle08 extends AbstractTestNGSpringContextTests {
    
    @Autowired
    ApplicationContext context;

    @DataProvider
    Object[][] getReferences() {
        return new Object[][]{
                // bean's class  ,  whether or not bean is a singleton
                {"single", true},
                {"proto", false}
        };
    }
    @Test(dataProvider = "getReferences")
    public void testReferenceTypes(String reference, boolean singleton) {

        Furry f1 = context.getBean(reference, Furry.class);
        String defaultValue = f1.getAnimal();

        // then change the animal and create a second furry
        f1.setAnimal("lynx"); 
        Furry f2 = context.getBean(reference, Furry.class);
        
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
