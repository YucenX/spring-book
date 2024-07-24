package com.farms4life2016.chapter03;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.farms4life2016.chapter03.mem03.CappyNormalizer;
import com.farms4life2016.chapter03.mem03.SecondNormalizer;

@ContextConfiguration(locations = "/config-04.xml")
public class TestConfigurationImport extends AbstractTestNGSpringContextTests {
    
    @Autowired
    ApplicationContext context;

    @DataProvider
    Object[][] resources()  {
        return new Object[][] { 
            {"waifumonServicesTests"},
            {WaifumonServicesTests.class},
            {"second"},
            {"cappy"},
            {SecondNormalizer.class},
            {CappyNormalizer.class},
            {"waifumonService4"}
        };
    }

    @Test(dataProvider = "resources")
    public void validateResourceExistence(Object resource) {
        if (resource instanceof String) {
            assertNotNull(context.getBean(resource.toString()));
        } else {
            if (resource instanceof Class<?>) {
                assertNotNull(context.getBean((Class<?>) resource));
            } else {
                fail("Invalid resource type");
            }
        }
    }
}
