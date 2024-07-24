package com.farms4life2016.chapter04;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import static org.testng.Assert.*;

/**
 * demonstrating after-construction and before-context-dies methods
 * but this time we use annotations intead of XML or interfaces.
 */
@Component
class WolfFurryAnnotated extends Furry {

    static Furry huntTarget = null;

    /**
     * use postconstruct for a method to be executed right after creation
     */
    @PostConstruct
    public void initialize() throws Exception {
        super.animal = "wolf";
        huntTarget = new FoxFurry();
    }

    /**
     * use predestroy for a method to be run right before context dies
     */
    @PreDestroy
    public void dispose() throws Exception {
        huntTarget = null;
    }
}

public class TestLifecycle06 {

    @Test
    public void testInitDestroyMethods06() {
        // creates a manually-closable application context
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/annotated-06.xml");

        // creates a wolf furry, which will immediately run initialize()
        WolfFurryAnnotated roxanne = context.getBean(WolfFurryAnnotated.class);
        assertNotNull(WolfFurryAnnotated.huntTarget); // huntTarget should point to a valid FoxFurry

        assertEquals(roxanne.getAnimal(), "wolf");
        context.close();  // closing the context would cause dispose to run

        // now the wolf will stop hunting that Fox
        assertNull(WolfFurryAnnotated.huntTarget);
    }
}
