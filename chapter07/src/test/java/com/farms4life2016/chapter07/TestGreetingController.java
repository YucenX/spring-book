package com.farms4life2016.chapter07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGreetingController extends AbstractTestNGSpringContextTests {

    @Autowired
    private GreetingController greetingController;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @DataProvider
    Object[][] greetingData() {
        return new Object[][]{
                new Object[]{null, "Hello, world!"},
                new Object[]{"World", "Hello, World!"},
                new Object[]{"Andrew", "Hello, Andrew!"},
                new Object[]{"farms4life2016", "Welcome back, farms4life2016!"}
        };
    }

    /**
     * a naive test that only checks that the controller is doing what it is supposed to.
     * it does not account for any of the HTTP stuff though
     * @param name
     * @param greeting
     */
    @Test(dataProvider = "greetingData")
    public void testDirectGreeting(String name, String greeting) {
        assertEquals(greetingController.greeting(name).getMessage(), greeting);
    }

    /**
     * this would be a more complete test that checks for HTTP stuff,
     * such as checking that the status code is OK
     * @param name
     * @param greeting
     */
    @Test(dataProvider = "greetingData")
    public void testRestGreeting(String name, String greeting) {
        String url = "http://localhost:" + port + "/greeting/" + (name != null ? name : "");

        // the rest template allows us to get responseEntities which contain
        // status codes, the webpage's body (which has our message), and other stuff too
        ResponseEntity<Greeting> result = restTemplate.getForEntity(url, Greeting.class);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody().getMessage(), greeting);
    }

}