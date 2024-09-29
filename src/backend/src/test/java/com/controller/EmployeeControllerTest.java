package com.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.common.Utils;
import com.exception.CompanyNotFoundException;
import com.exception.EmployeeNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.EmployeeBalanceUpdateDTO;
import com.model.EmployeeRegisterDTO;
import com.service.CompanyService;
import com.service.EmployeeService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

	private MockMvc mockMvc;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private CompanyService companyService;

    private EmployeeRegisterDTO employeeRegisterDTO;
    private EmployeeBalanceUpdateDTO employeeBalanceUpdateDTO;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        employeeRegisterDTO = new EmployeeRegisterDTO("Joe@company1.com", "company1","Joe");
        employeeBalanceUpdateDTO = new EmployeeBalanceUpdateDTO("company1", "Joe", "credit", 200.0);
    }

    
	@Test
	@DisplayName("Test 1: EmployeeController - RegisterEmployee success")
	@Order(1)
	void testRegisterEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.objectToJsonString(employeeRegisterDTO)))
                .andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Test 2: EmployeeController - GetCreditBalanceEmployee")
	@Order(2)
	void testGetCreditBalanceEmployee() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.objectToJsonString(employeeRegisterDTO)))
                .andExpect(status().isOk());
        
		mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/credit/balance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.objectToJsonString(employeeRegisterDTO)))
                .andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test 3: EmployeeController - GetAllemployeeBalance")
	@Order(3)
	void testGetAllemployeeBalance() throws Exception {
       
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/all/balance")
                .param("companyName", "company1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test 4: EmployeeController - GetAllemployeeBalance")
	@Order(4)
	void testGetAllemployeeBalanceFailure() throws Exception {
       
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/all/balance")
                .param("companyName", "company3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
	}
	
	@Test
	@DisplayName("Test 5: EmployeeController - UpdateEmployeeBalance")
	@Order(5)
	void testUpdateEmployeeBalance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/balance/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.objectToJsonString(employeeBalanceUpdateDTO)))
                .andExpect(status().isOk());
	}
}
