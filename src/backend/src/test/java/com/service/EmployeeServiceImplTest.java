package com.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.exception.EmployeeNotFoundException;
import com.model.Company;
import com.model.Employee;
import com.model.EmployeeBalance;
import com.model.EmployeeBalanceDTO;
import com.model.EmployeeRegisterDTO;
import com.repository.EmployeeRepository;
import com.repository.EmployeeBalanceRepository;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CompanyService companyService;

    @Mock
    private EmployeeBalanceRepository employeeBalanceRepository;

    @Mock
    private AccountService accountService;

    private Employee employee;
    private EmployeeBalance employeeBalance;
    private EmployeeRegisterDTO registerDTO;
    private Company company;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        company = Company.builder().id(1).name("company1").build();
        employee = Employee.builder().id(1).name("Joe").email("joe@company1.com").status("Active").companyId(1).build();
        employeeBalance = EmployeeBalance.builder().employeeId(1).balance(1000.0).build();
        registerDTO = new EmployeeRegisterDTO("joe@company1.com", "company1","Joe");
    }
    
	@Test
	@DisplayName("Test 1: EmployeeServiceImpl - GetEmployeeIdExisting")
	@Order(1)
	void testGetEmployeeIdExisting() {
		when(employeeRepository.findIdByNameAndCompanyId("Joe", 1)).thenReturn(employee);

	    Integer employeeId = employeeService.getEmployeeId("Joe", 1);
        assertNotNull(employeeId);
        assertEquals(1, employeeId);
        verify(employeeRepository, times(1)).findIdByNameAndCompanyId("Joe", 1);
	}

	@Test
	@DisplayName("Test 2: EmployeeServiceImpl - GetEmployeeIdNonExisting")
	@Order(2)
	void testGetEmployeeIdNonExisting() {
        when(employeeRepository.findIdByNameAndCompanyId("Unknown", 1)).thenReturn(null);
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeId("Unknown", 1);
        });
        verify(employeeRepository, times(1)).findIdByNameAndCompanyId("Unknown", 1);
	}
	
	@Test
	@DisplayName("Test 3: EmployeeServiceImpl - GetEmployeeBalance")
	@Order(3)
	void testGetEmployeeBalance() {
        when(employeeBalanceRepository.findByEmployeeId(1)).thenReturn(employeeBalance);
        Double balance = employeeService.getEmployeeBalance(1);
        assertNotNull(balance);
        assertEquals(1000.0, balance);
	}

	@Test
	@DisplayName("Test 4: EmployeeServiceImpl - RegisterEmployee")
	@Order(4)
	void testRegisterEmployee() {
		when(companyService.getCompanyId("company1")).thenReturn(1);
        when(employeeRepository.findIdByNameAndCompanyId("Joe", 1)).thenReturn(employee);
        when(accountService.createAccount(1, 1)).thenReturn(1000.0);

        double balance = employeeService.registerEmployee(registerDTO);

        assertEquals(1000.0, balance);
       
	}

	@Test
	@DisplayName("Test 5: EmployeeServiceImpl - GetAllEmployeeBalance")
	@Order(5)
	void testGetAllEmployeeBalance() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"Joe", 1000.0});
        when(employeeRepository.findAllEmpoyeeByCompanyId(1)).thenReturn(data);

        List<EmployeeBalanceDTO> result = employeeService.getAllEmployeeBalance(1);

        assertEquals(1, result.size());
        assertEquals("Joe", result.get(0).getName());
        assertEquals(1000.0, result.get(0).getBalance());
        verify(employeeRepository, times(1)).findAllEmpoyeeByCompanyId(1);
	}

	@Test
	@DisplayName("Test 6: EmployeeServiceImpl - UpdateEmployeeBalance")
	@Order(6)
	void testUpdateEmployeeBalance() {
        when(companyService.getCompanyId("Company2")).thenReturn(1);
        when(employeeRepository.findIdByNameAndCompanyId("Jose", 1)).thenReturn(employee);
        when(accountService.updateAccount(1, 1, "credit", 200.0)).thenReturn(1200.0);

        Double balance = employeeService.updateEmployeeBalance("Jose", "Company2", "credit", 200.0);

        assertEquals(1200.0, balance);
        verify(companyService, times(1)).getCompanyId("Company2");
        verify(employeeRepository, times(1)).findIdByNameAndCompanyId("Jose", 1);
        verify(accountService, times(1)).updateAccount(1, 1, "credit", 200.0);
	}

}
