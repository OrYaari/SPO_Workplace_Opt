package com.spo.workplace.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkplaceOptimizationControllerIT {


    private static final String URI = "/api/v1/spo/workplace/optimize";
    private static final String APPLICATION_JSON = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void optimizeRoomAssignment() throws Exception {
        mockMvc.perform(post(URI).
                contentType(APPLICATION_JSON).
                content("{ \"rooms\": [35, 21, 17], \"senior\": 10, \"junior\": 6 }")).
                andExpect(status().is2xxSuccessful()).
                andExpect(content().json("[{\"senior\":3,\"junior\":1},{\"senior\":1,\"junior\":2},{\"senior\":2,\"junior\":0}]"));
    }

    @Test
    public void optimizeRoomAssignment_badRequestNoSeniorCapacity() throws Exception {
        mockMvc.perform(post(URI).
                contentType(APPLICATION_JSON).
                content("{ \"rooms\": [35, 21, 17], \"senior\": 0, \"junior\": 6 }")).
                andExpect(status().isBadRequest());
    }

    @Test
    public void optimizeRoomAssignment_badRequestOver100Rooms() throws Exception {
        mockMvc.perform(post(URI).
                contentType(APPLICATION_JSON).
                content("{ \"rooms\": [35, 21, 17,35, 21, 17, 35, 21, 17, 35, 21, 17, 35, 21, 17,35, 21, 17, 35, 21, 17, 35, 21, 17, 35, 21, 17,35, 21, 17, 35, 21, 17, 35, 21, 17," +
                        "35, 21, 17,35, 21, 17, 35, 21, 17, 35, 21, 17, 35, 21, 17,35, 21, 17, 35, 21, 17, 35, 21, 17, 35, 21, 17,35, 21, 17, 35, 21, 17, 35, 21, 17," +
                        "35, 21, 17,35, 21, 17, 35, 21, 17, 35, 21, 17, 35, 21, 17,35, 21, 17, 35, 21, 17, 35, 21, 17, 35, 21, 17,35, 21, 17, 35, 21, 17, 35, 21, 17], \"senior\": 10, \"junior\": 6 }")).
                andExpect(status().isBadRequest());
    }

    @Test
    public void optimizeRoomAssignment_badRequestSeniorLowerCapacity() throws Exception {
        mockMvc.perform(post(URI).
                contentType(APPLICATION_JSON).
                content("{ \"rooms\": [35, 21, 17], \"senior\": 2, \"junior\": 6 }")).
                andExpect(status().isBadRequest());
    }

    @Test
    public void optimizeRoomAssignment_badRequestStructureTooBig() throws Exception {
        mockMvc.perform(post(URI).
                contentType(APPLICATION_JSON).
                content("{ \"rooms\": [35, 21, 117], \"senior\": 12, \"junior\": 6 }")).
                andExpect(status().isBadRequest());
    }
}