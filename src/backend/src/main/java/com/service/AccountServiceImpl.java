/**
 * Project: Tellar Banking System
 * This is the concrete class implments te abstract method declared in the account service
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.service;

import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;

import com.controller.EmployeeController;
import com.model.AccountTransaction;
import com.model.Company;
import com.model.EmployeeBalance;
import com.repository.AccountRepository;
import com.repository.CompanyRepository;
import com.repository.EmployeeBalanceRepository;


@Service
public class AccountServiceImpl implements AccountService{
	
	private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private EmployeeBalanceRepository employeeBalanceRepository;

	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * This method creating the record in account transaction table and employe balance table the 
	 * corresponding respository.
	 * @param employeeId, companyId
	 * @return employeeBalance 
	 */
	@Override
	public double createAccount(Integer employeeId, Integer companyId) {
		
		String accountNo = UUID.randomUUID().toString();
		AccountTransaction account = AccountTransaction.builder().accountNo(accountNo)
				          .employeeId(employeeId)
				          .txnType("Credit")
				          .amount(companyService.getCompany(companyId).getDefaultCredit()).build();
		
		EmployeeBalance employeeBalance = EmployeeBalance.builder()
				                          .employeeId(employeeId)
				                          .accountNo(accountNo)
				                          .balance(companyService.getCompany(companyId).getDefaultCredit())
				                          .build();
        this.employeeBalanceRepository.save(employeeBalance);
		this.accountRepository.save(account);
		
		logger.info("---createAccount---method executed successful");
		//
		return employeeBalanceRepository.findByEmployeeId(employeeId).getBalance();
	}
	
	/**
	 * This method update the account transaction table and employee balance table
	 * @param employeeId, companyId
	 * @return employee balance.
	 */
	@Override
	public double updateAccount(Integer employeeId, Integer companyId, 
			String trxnType, double trxnAmount) {
		logger.info("---updateAccount---trxnType:"+trxnType);
		EmployeeBalance empBal = employeeBalanceRepository.findByEmployeeId(employeeId);
		AccountTransaction account = AccountTransaction.builder()
				          .accountNo(empBal.getAccountNo())
				          .employeeId(employeeId)
				          .txnType(trxnType)
				          .amount(trxnAmount).build();
		if (trxnType.equalsIgnoreCase("credit")) {
			empBal.setBalance(empBal.getBalance()+trxnAmount);
		}
		if (trxnType.equalsIgnoreCase("debit")) {
			if (trxnAmount < empBal.getBalance()){
				empBal.setBalance(empBal.getBalance()-trxnAmount);
			}
		}
        this.employeeBalanceRepository.save(empBal);
		this.accountRepository.save(account);
		logger.info("---updateAccount---method executed successful");
		return employeeBalanceRepository.findByEmployeeId(employeeId).getBalance();
	}


}
