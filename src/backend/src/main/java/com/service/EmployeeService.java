/**
 * Project: Tellar Banking System
 * Desc: This interface contains the list of abstract method, will be used for employee transaction/management
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.service;

import java.util.List;
import java.util.Map;

import com.model.Employee;
import com.model.EmployeeBalanceDTO;
import com.model.EmployeeRegisterDTO;

public interface EmployeeService {
	/**
	 * This is to get the emlployee id for the given employee name and company id
	 * @param empName
	 * @param companyId
	 * @return employee id or null 
	 */
	public Integer getEmployeeId(String empName, Integer companyId);
	
	/**
	 * This is to get the employee balance for the given employee id
	 * @param employeeId
	 * @return balance or null
	 */
	public Double getEmployeeBalance(Integer employeeId);
	
	/**
	 * This method is to register the employee
	 * 
	 * @param employee register object with employee name, company name, email address
	 * @return default credit value 1000.00 if success otherwise, the status with false.
	 */
	public double registerEmployee(EmployeeRegisterDTO register);
	
	/**
	 * This method is to get all the employee and their balance in the given company
	 * @param companyId
	 * @return list of employee name with their balance.
	 */
	public List<EmployeeBalanceDTO> getAllEmployeeBalance(Integer companyId);
	
	/**
	 * This method is to update the employee balance
	 * @param employeeName
	 * @param companyName
	 * @param trxnType
	 * @param trxnAmount
	 * @return final balance or null
	 */
	public Double updateEmployeeBalance(String employeeName, String companyName, String trxnType, double trxnAmount);
	
	/**
	 * This method is to get all the employees in the 
	 * @param companyId
	 * @return
	 */
	public Map<Integer, String> getAllEmployee(Integer companyId, String status);
}
