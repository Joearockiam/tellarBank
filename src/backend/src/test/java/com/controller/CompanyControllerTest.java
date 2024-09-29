package com.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;  // Mocking the service

    @InjectMocks
    private CompanyController companyController;  // Ensures the mock is injected into the controller

    @Test
    public void testGetAllCompanyNames() throws Exception {
        mockMvc.perform(get("/api/company/all/name"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.1", is("ABC systems Pte Ltd")))
               .andExpect(jsonPath("$.2", is("Test Systems")));
    }
}
