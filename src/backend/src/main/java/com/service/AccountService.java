/**
 * Projct: Tellar Banking System
 * This interface contains the abstract method for account creation and update the account.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.service;

import com.model.AccountTransaction;

public interface AccountService {
	/**
	 * This method is to create an account when registering an employe
	 * @param employeeId
	 * @param companyId
	 * @return doube value
	 */
	public double createAccount(Integer employeeId, Integer companyId);
	
	/**
	 * This method is to update an employee account.
	 * @param employeeId
	 * @param companyId
	 * @param trxnType
	 * @param trxnAmount
	 * @return
	 */
	public double updateAccount(Integer employeeId, Integer companyId, 
			String trxnType, double trxnAmount);
}
