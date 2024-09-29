/**
 * Project: Tellar Banking System
 * Desc : This is the JPA repository interface for EmployeeBalance table.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.EmployeeBalance;

@Repository
public interface EmployeeBalanceRepository extends JpaRepository<EmployeeBalance, Integer> {
	
	/**
	 * Desc: This is to get the employee details by employee id
	 * @param employeeId
	 * @return EmployeeBalance object
	 */
	public EmployeeBalance findByEmployeeId(Integer employeeId);

}
