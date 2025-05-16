package com.example.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DeleteBankTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void fourthEndpointIntegrationTest() throws Exception {

        String swiftCode = "AAISALTRXXX";

        mockMvc.perform(delete("/bank/swift-codes/{swiftCode}", swiftCode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Bank deleted"));

    }

}
