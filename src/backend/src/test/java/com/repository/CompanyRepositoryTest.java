package com.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.model.Company;

@DataJpaTest 
class CompanyRepositoryTest {

	@Autowired
    private CompanyRepository companyRepository;
	
	@Test
	@DisplayName("Test 1: Company Repository - FindIdByName")
	void testFindIdByName() {
		Company company = new Company();
		//company.setId(1010);
        company.setName("Company1");
        companyRepository.save(company); 
        
        Company foundCompany = companyRepository.findIdByName("Company1");
        assertNotNull(foundCompany);
        assertEquals(1, foundCompany.getId());
        
	}

}
