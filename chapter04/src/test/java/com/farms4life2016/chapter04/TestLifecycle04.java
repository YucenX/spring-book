package com.farms4life2016.chapter04;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * a simple class that demonstrates methods that can be executed
 * right after object creation and right before the application context closes
 */
class WolfFurryInterfaced extends Furry implements InitializingBean, DisposableBean {

    /*
     * same as WolfFurry class, but we use interfaces to implement the 
     * on-construction and on-context-close methods
     */

    static Furry huntTarget = null;

    // this method will be run upon object creation
    @Override
    public void afterPropertiesSet() throws Exception {
        super.animal = "wolf";
        huntTarget = new FoxFurry();
    }

    // this method will be run when the context closes
    @Override
    public void destroy() throws Exception {
        huntTarget = null;
    }

}

public class TestLifecycle04 {

    @Test
    public void testInitDestroyMethods04() {
        // creates a manually-closable application context
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/config-04.xml");

        // creates a wolf furry, which will immediately run init()
        WolfFurryInterfaced roxanne = context.getBean(WolfFurryInterfaced.class);
        assertNotNull(WolfFurryInterfaced.huntTarget); // huntTarget should point to a valid FoxFurry

        assertEquals(roxanne.getAnimal(), "wolf");
        context.close();  // closing the context would cause dispose to run

        // now the wolf will stop hunting that Fox
        assertNull(WolfFurryInterfaced.huntTarget);
    }
}