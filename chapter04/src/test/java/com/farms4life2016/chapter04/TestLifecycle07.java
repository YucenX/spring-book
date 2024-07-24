package com.farms4life2016.chapter04;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * imo this test is a little redundant. the book just wants to show you that
 * you can also use interfaces with the annotated components 
 */
@Component
class WolfFurryAnnotatedInterfaced extends Furry implements InitializingBean, DisposableBean {

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


public class TestLifecycle07 {
    @Test
    public void testInitDestroyMethods07() {
        // creates a manually-closable application context
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/annotated-07.xml");

        // creates a wolf furry, which will immediately run initialize()
        WolfFurryAnnotatedInterfaced roxanne = context.getBean(WolfFurryAnnotatedInterfaced.class);
        assertNotNull(WolfFurryAnnotatedInterfaced.huntTarget); // huntTarget should point to a valid FoxFurry

        assertEquals(roxanne.getAnimal(), "wolf");
        context.close();  // closing the context would cause dispose to run

        // now the wolf will stop hunting that Fox
        assertNull(WolfFurryAnnotatedInterfaced.huntTarget);
    }
}
