/**
 * Project: Tellar Banking System
 * Desc : This is the JPA repository interface for Account Transaction table.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.AccountTransaction;
import com.model.EmployeeBalance;

@Repository
public interface AccountRepository extends JpaRepository<AccountTransaction, Integer>{
	/**
	 * This is to find employee by Id
	 * @param employeeId
	 * @return AccountTrasaction object.
	 */
	public AccountTransaction findByEmployeeId(Integer employeeId);
}
