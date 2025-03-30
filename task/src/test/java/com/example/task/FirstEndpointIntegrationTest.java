package com.example.task;

import com.example.task.entity.FirstEndpointDTO;
import com.example.task.entity.ThirdEndpointDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FirstEndpointIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void firstEndpointIntegrationTest() throws Exception {

        String swiftCode = "AAISALTRXXX";

        mockMvc.perform(get("/v1/swift-codes/{swiftCode}", swiftCode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").isString())
                .andExpect(jsonPath("$.bankName").isString())
                .andExpect(jsonPath("$.countryISO2").isString())
                .andExpect(jsonPath("$.countryName").isString())
                .andExpect(jsonPath("$.isHeadquarter").value(true))
                .andExpect(jsonPath("$.swiftCode").value(swiftCode))
                .andExpect(jsonPath("$.branches").isArray());
    }
}
