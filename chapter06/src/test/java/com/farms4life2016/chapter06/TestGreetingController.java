package com.farms4life2016.chapter06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * a simple test for the GreetingController class
 */
@Test
@WebAppConfiguration
@ContextConfiguration(classes = GatewayAppWebConfig.class)
public class TestGreetingController extends AbstractTestNGSpringContextTests {

  @Autowired
  private WebApplicationContext wac;
  // mockmvc fakes some of the http stuff so that we don't have to start to start a web server
  private MockMvc mockMvc; // makes the test run faster
  
  @Test
  public void greetingTest() throws Exception {
    this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.wac)
            .build();
    this.mockMvc.perform(get("/greeting")
        .accept(MediaType.ALL))
        .andExpect(status().isOk());
  }
}