package com.farms4life2016.chapter03;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * This class will test every single configuration XML or Java
 * that we have written in this chapter.
 */
@ContextConfiguration(classes = {TestConfiguration.class})
public class TestWaifumonServicesAll extends AbstractTestNGSpringContextTests {
    @Autowired
    WaifumonServicesTests tests;
    @DataProvider
    Object[][] configurations() { // lists out all our config files, 
        return new Object[][] {   // either the XML file location or Java classes
            {"/config-01.xml"},
            {"/config-02.xml"},
            {"/config-03.xml"},
            {"/config-04.xml"},
            {"/config-05.xml"},
            {"/config-06.xml"},
            {Configuration7.class},
            {Configuration8.class},
            {Configuration9.class},
            {Configuration10.class}
        };
    }

    // here comes a very complicated method 
    /**
     * creates an ApplicationContext based on the provided config.
     * then creates a WaifumonService from that context.
     * finally invokes the provided method on the WaifumonService
     * @param config location of an XML config file (as a String) or a Java @Configuration class
     * @param method a method that a WaifumonService object can invoke
     */
    private void runMethod(Object config, Consumer<WaifumonService> method) {
        ApplicationContext context;
        // sort configs based on XML file locations (Strings) or Java classes
        // and initialize the context as appropriate
        if (config instanceof String) {
            context = new ClassPathXmlApplicationContext(config.toString());
        } else if (config instanceof Class<?>) {
            context = new AnnotationConfigApplicationContext((Class<?>) config);
        } else {
            throw new RuntimeException("Invalid configuration argument: " + config);
        }
        WaifumonService service = context.getBean(WaifumonService.class);
        method.accept(service);
    } 

    /**
     * tests the runMethod(...) function, right...?
     * I think this is just testing the exception throwing, cuz this test
     * is meant to throw an exception
     */
    @Test(expectedExceptions = RuntimeException.class)
    public void testRunMethod() {
        runMethod(Boolean.TRUE, tests::testReasonVoting);
    }

    /*
     * these are essentially the four major tests from WaifumonServicesTests
     * but reformatted for our complicated method above
     */

    @Test(dataProvider = "configurations")
    public void testReasonVoting(Object config) {
        runMethod(config, tests::testReasonVoting);
    }

    @Test(dataProvider = "configurations")
    public void testMatchingCandidateNames(Object config) {
        runMethod(config, tests::testMatchingCandidateNames);
    }

    @Test(dataProvider = "configurations")
    public void testMatchingReasonExplainationsForCandidate(Object config) {
        runMethod(config, tests::testMatchingReasonExplainationsForCandidate);
    }

    @Test(dataProvider = "configurations")
    public void testReasonsForCandidate(Object config) {
        runMethod(config, tests::testReasonsForCandidate);
    }

}
