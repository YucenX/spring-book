package com.farms4life2016.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/config-03.xml")
public class TestWaifumonService3 extends AbstractTestNGSpringContextTests {
    @Autowired
    WaifumonService service;
    WaifumonServicesTests tests = new WaifumonServicesTests();

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
