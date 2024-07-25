package com.farms4life2016.chapter06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Test
@WebAppConfiguration
@ContextConfiguration(classes = GatewayAppWebConfig.class)
public class TestGetReasonsController extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Test
    public void getSongControllerTest() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        MvcResult result = this.mockMvc.perform(get("/reasons")
                        .param("candidate", "Braixen")
                        .param("reason", "furry"))
                .andReturn();
        this.mockMvc.perform(get("/reasons")
                .param("candidate", "Braixen")
                .param("reason", "furry")
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void getSongsTestWithoutParameters() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc.perform(get("/reasons")
                .accept(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getSongsByArtistTest() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.mockMvc.perform(get("/reasons").param("candidate", "Braixen")
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }
    
}
