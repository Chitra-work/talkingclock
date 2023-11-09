package com.example.talkingclock.controllertest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class TalkingClockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testValidNumericTime() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock/15:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("Three o'clock"));
    }

    @Test
    public void testInvalidNumericTime() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock/invalid-time")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testMissingNumericTime() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/talking-clock/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

