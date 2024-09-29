package com.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.model.EmployeeBalance;

@DataJpaTest 
class EmployeeBalanceRepositoryTest {

    @Autowired
    private EmployeeBalanceRepository employeeBalanceRepository;
    
	@Test
	void testFindByEmployeeId() {
        EmployeeBalance empBalance = new EmployeeBalance();
        empBalance.setEmployeeId(1020);
        empBalance.setBalance(1000.0);  
        employeeBalanceRepository.save(empBalance); 
        
        EmployeeBalance foundBalance = employeeBalanceRepository.findByEmployeeId(1020);
        
        assertNotNull(foundBalance);
        assertEquals(1001, foundBalance.getEmployeeId());
        assertEquals(1000.0, foundBalance.getBalance());
        
	}

}
