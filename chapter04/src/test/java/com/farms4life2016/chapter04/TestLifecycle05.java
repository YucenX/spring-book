package com.farms4life2016.chapter04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/*
 * this class does nearly the same thing as the Test01 class,
 * but we instead use annotations in Java rather than XML files
 */

 // use @Scope to declare a bean as singleton or prototype
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  // pretty sure this is default behaviour so we don't need to specify this
class FoxFurrySingle extends Furry {
    public FoxFurrySingle() {
        super.animal = "fox";
    }
}

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) 
class FoxFurryProto extends Furry {
    public FoxFurryProto() {
        super.animal = "fox";
    }
}

@ContextConfiguration("/annotated.xml")
public class TestLifecycle05 extends AbstractTestNGSpringContextTests {
    @Autowired
    ApplicationContext context;

    @DataProvider
    Object[][] getReferences() {
        return new Object[][]{
                // bean's class  ,  whether or not bean is a singleton
                {FoxFurrySingle.class, true},
                {FoxFurryProto.class, false}
        };
    }
    @Test(dataProvider = "getReferences")
    public void testReferenceTypes(Class<Furry> furryClass, boolean singleton) {
        // in this test, we visualize the differences between singletons and prototypes
        // first we create a Furry and store its animal (i.e. "fox")
        Furry f1 = context.getBean(furryClass);
        String defaultValue = f1.getAnimal();

        // then change the animal and create a second furry
        f1.setAnimal("fennec fox"); 
        Furry f2 = context.getBean(furryClass);
        
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