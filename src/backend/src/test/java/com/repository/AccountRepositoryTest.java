package com.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.model.AccountTransaction;

@DataJpaTest
class AccountRepositoryTest {

	@Autowired
    private AccountRepository accountRepository;
	
	@Test
	@DisplayName("Test 1: Test whether the function works for correct case")
	void testFindByEmployeeId1() {
		AccountTransaction transaction = new AccountTransaction();
        transaction.setEmployeeId(1); 
        transaction.setAmount(1000.0); 
        accountRepository.save(transaction);  
        
        //retrieve the account transaction using employee id
        AccountTransaction foundTransaction = accountRepository.findByEmployeeId(1);
        
        assertNotNull(foundTransaction);
        assertEquals(1, foundTransaction.getEmployeeId());
        assertEquals(1000.0, foundTransaction.getAmount());
        
	}

	@Test
	@DisplayName("Test 2: Test whether it works when employee id not found")
	void testFindByEmployeeId2() {
       
        //retrieve the account transaction using employee id
        AccountTransaction foundTransaction = accountRepository.findByEmployeeId(2);
        
        assertNull(foundTransaction);
	}
	
}
