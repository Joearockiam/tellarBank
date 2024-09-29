package com.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.model.AccountTransaction;
import com.model.Company;
import com.model.EmployeeBalance;
import com.repository.AccountRepository;
import com.repository.EmployeeBalanceRepository;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceImplTest {

	 @InjectMocks
	 private AccountServiceImpl accountService;

	 @Mock
	 private AccountRepository accountRepository;

	 @Mock
	 private EmployeeBalanceRepository employeeBalanceRepository;

	 @Mock
	 private CompanyService companyService;
	 
	 private Integer employeeId;
	 private Integer companyId;
	 private Company company;
	 
	 
	 @BeforeEach
	 public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        employeeId = 1;
	        companyId = 100;
	        company = new Company();
	        company.setDefaultCredit(1000.0); 
	  }
	 
	@Test
	@DisplayName("Test 1: AccountServiceImpl - CreateAccount")
	@Order(1)
	void testCreateAccount() {
		when(companyService.getCompany(companyId)).thenReturn(company);
        EmployeeBalance savedEmployeeBalance = EmployeeBalance.builder()
        		.employeeId(employeeId)
                .balance(company.getDefaultCredit())
                .accountNo(UUID.randomUUID().toString())
                .build();
        when(employeeBalanceRepository.findByEmployeeId(employeeId)).thenReturn(savedEmployeeBalance);

        double balance = accountService.createAccount(employeeId, companyId);
        
        assertEquals(1000.0, balance);  
        verify(accountRepository).save(any(AccountTransaction.class));  
        verify(employeeBalanceRepository).save(any(EmployeeBalance.class));
        
	}
	
	@Test
	@DisplayName("Test 2: AccountServiceImpl - UpdateAccountDebit")
	@Order(2)
	void testUpdateAccountDebit() {
		EmployeeBalance employeeBalance = EmployeeBalance.builder()
	            .employeeId(employeeId)
	            .balance(1000.0)
	            .accountNo(UUID.randomUUID().toString())
	            .build();
	    when(employeeBalanceRepository.findByEmployeeId(employeeId)).thenReturn(employeeBalance);
	    when(accountRepository.save(any(AccountTransaction.class))).thenAnswer(invocation -> invocation.getArgument(0));
	        
	    double updatedBalance = accountService.updateAccount(employeeId, companyId, "debit", 200.0);
	    assertEquals(800.0, updatedBalance);  
        verify(accountRepository).save(any(AccountTransaction.class));  
        verify(employeeBalanceRepository).save(any(EmployeeBalance.class));
        
	}
	
	@Test
	@DisplayName("Test 3: AccountServiceImpl - UpdateAccountCredit")
	@Order(3)
	void testUpdateAccountCredit() {
		EmployeeBalance employeeBalance = EmployeeBalance.builder()
	            .employeeId(employeeId)
	            .balance(1000.0)
	            .accountNo(UUID.randomUUID().toString())
	            .build();
	    when(employeeBalanceRepository.findByEmployeeId(employeeId)).thenReturn(employeeBalance);
	    when(accountRepository.save(any(AccountTransaction.class))).thenAnswer(invocation -> invocation.getArgument(0));
	    
	    double updatedBalance = accountService.updateAccount(employeeId, companyId, "credit", 200.0);
	    
	    assertEquals(1200.0, updatedBalance);  
        verify(accountRepository).save(any(AccountTransaction.class)); 
        verify(employeeBalanceRepository).save(any(EmployeeBalance.class)); 
        
	}
}
