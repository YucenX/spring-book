package com.farms4life2016.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

// this time we are loading an array of config files. all files are put in the same application context
@ContextConfiguration(locations = {"/config-05.xml", "/waifumonservicetest.xml"})
public class TestWaifumonServices5 extends AbstractTestNGSpringContextTests  {
    
    @Autowired
    WaifumonService service;
    @Autowired  
    WaifumonServicesTests tests;

    @Test
    public void testReasonVoting() {
        tests.testReasonVoting(service);
    }

    @Test 
    public void testMatchingCandidateNames() {
        tests.testMatchingCandidateNames(service);
    }

    @Test
    public void testReasonsForCandidate() {
        tests.testReasonsForCandidate(service);
    }

    @Test
    public void testMatchingReasonExplainationsForCandidate() {
        tests.testMatchingReasonExplainationsForCandidate(service);
    }

}
