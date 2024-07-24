package com.farms4life2016.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.farms4life2016.chapter03.model.Reason;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ContextConfiguration(locations = "/config-01.xml")   // links to our config file
public class TestWaifumonService1 extends AbstractTestNGSpringContextTests {

    @Autowired                  // autowired - if not instantiated by the programmer, Spring will automatically look for something that "fits"
    ApplicationContext context; //             and plug it into the field. if there's only one possible thing that fits, then that thing will be
    @Autowired                  //             inserted into the field. if there's multiple, then the one matching the field name will be inserted
    WaifumonService service;    //             otherwise, nothing will be autowired.

    /**
     * the first test checks that our configuration is correct 
     */
    @Test
    public void testConfiguration() {
        assertNotNull(context);
        Set<String> definitions = new HashSet<>(Arrays.asList(context.getBeanDefinitionNames()));
        /*
        // uncomment if you'd like to see the entire set of defined beans
        for (String d : definitions) {
            System.out.println(d);
        }
        */

        // if @Component does not specify a name, then Spring will default to the same as class name but first letter is uncapped.
        assertTrue(definitions.contains("waifumonService1"));
    }

    /**
     * the second test checks that our API works
     */
    @Test
    public void testWaifumonService() {
        // make up a reason for why some pokemon candidate might be considered a waifumon
        Reason reason = service.getReason("Braixen", "furry");
        assertEquals(reason.getVotes(), 0); // we haven't voted so this should be 0
    }

}
