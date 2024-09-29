package com.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import com.model.Company;
import com.model.EmployeeBalance;
import com.repository.CompanyRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyServiceImplTest {

	@InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Value("${default.credit.amount}")
    private Double defaultCredit = 10000.0;

    private Company company;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        company = Company.builder()
                .id(1)
                .name("company1")
                .status("Active")
                .defaultCredit(defaultCredit)
                .build();
    }
    
	@Test
	@DisplayName("Test 1: CompanyServiceImpl - GetCompany")
	@Order(1)
	void testGetCompany() {
        when(companyRepository.findById(1)).thenReturn(Optional.of(company));

        Company result = companyService.getCompany(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("company1", result.getName());
       // verify(companyRepository, times(1)).findById(1);
	}

	@Test
	@DisplayName("Test 2: CompanyServiceImpl - CreateCompany")
	@Order(2)
	void testCreateCompany() {
        when(companyRepository.save(any(Company.class))).thenReturn(company);
        when(companyRepository.findIdByName("company1")).thenReturn(company);

        Integer companyId = companyService.createCompany("company1");

        assertNotNull(companyId);
        assertEquals(1, companyId);
        verify(companyRepository, times(1)).save(any(Company.class));
        verify(companyRepository, times(1)).findIdByName("company1");
	}

	@Test
	@DisplayName("Test 3: CompanyServiceImpl - UpdateCompany")
	@Order(3)
	void testUpdateCompany() {
        when(companyRepository.findIdByName("Company1")).thenReturn(company);

        boolean result = companyService.updateCompany("Company1", "Inactive");

        assertTrue(result);
        assertEquals("Inactive", company.getStatus());
        verify(companyRepository, times(1)).save(any(Company.class));
	}

	@Test
	@DisplayName("Test 4: CompanyServiceImpl - GetCompanyId")
	@Order(4)
	void testGetCompanyId() {
        when(companyRepository.findIdByName("Company1")).thenReturn(company);
        Integer result = companyService.getCompanyId("Company1");

        assertNotNull(result);
        assertEquals(1, result);
        verify(companyRepository, times(1)).findIdByName("Company1");
	}

}
