package com.example.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetBankDetailsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void firstEndpointIntegrationTest() throws Exception {

        String swiftCode = "AAISALTRXXX";

        mockMvc.perform(get("/api/banks/{swiftCode}", swiftCode))
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
