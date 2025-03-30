package com.example.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SecondEndpointIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void secondEndpointIntegrationTest() throws Exception {

        String countryISO2 = "PL";

        mockMvc.perform(get("/v1/swift-codes/country/{countryISO2}", countryISO2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryISO2").isString())
                .andExpect(jsonPath("$.countryName").isString())
                .andExpect(jsonPath("$.banksFromCountry").isArray());

    }

}
