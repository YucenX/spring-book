package com.farms4life2016.chapter04;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * a simple class that demonstrates methods that can be executed
 * right after object creation and right before the application context closes
 */
class WolfFurry extends Furry {

    // represents the wolf's hunting target
    static Furry huntTarget = null;

    /**
     * upon creation, the wolf will hunt a fox 
     * (assuming that a new fox is created for the wolf to hunt)
     */
    public void init() {
        super.animal = "wolf";
        huntTarget = new FoxFurry();
    }

    /**
     * on death (of context), the wolf will no longer be hunting that fox. 
     * I guess if we had more methods you could configure the fox to stop
     * running away from a dead wolf.
     */
    public void dispose() {
        huntTarget = null;
    }
}

@ContextConfiguration(locations = "/config-03.xml")
public class TestLifecycle03 {

    @Test
    public void testInitDestroyMethods03() {
        // creates a manually-closable application context
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/config-03.xml");

        // creates a wolf furry, which will immediately run init()
        WolfFurry roxanne = context.getBean(WolfFurry.class);
        assertNotNull(WolfFurry.huntTarget); // huntTarget should point to a valid FoxFurry

        assertEquals(roxanne.getAnimal(), "wolf");
        context.close();  // closing the context would cause dispose to run

        // now the wolf will stop hunting that Fox
        assertNull(WolfFurry.huntTarget);
    }
}