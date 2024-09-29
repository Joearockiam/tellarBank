/**
 * Project: Tellar Banking System
 * Description: This controller is to handle the request related to employee such as employee registration,
 *               query the employees balance and update the employee credit/debit transaction.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */

package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exception.CompanyNotFoundException;
import com.exception.EmployeeNameAlreadyExist;
import com.exception.EmployeeNotFoundException;
import com.model.EmployeeBalanceDTO;
import com.model.EmployeeBalanceUpdateDTO;
import com.model.EmployeeRegisterDTO;
import com.service.CompanyService;
import com.service.EmployeeService;

import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping(value = "/api/employee/")
@CrossOrigin(origins = "*")
public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * This endpoint is to handle the employee registration.
	 * @param employeename, company name, email id.
	 * @return json with success and credit keys with values true/false and credit value.
	 * @throws Exception
	 */
	@PostMapping(value="/register")
	public ResponseEntity<Map<String, Object>> registerEmployee(@RequestBody EmployeeRegisterDTO employee) throws Exception{
		Map<String, Object> retmap= new HashMap<>();
		try {
			logger.info("Employee registration");
			Double result = this.empService.registerEmployee(employee);
			Optional<Double> optResult = Optional.ofNullable(result);
			if (optResult.isPresent()) {
				retmap.put("success", "true");
				retmap.put("credit", result);
				logger.info("Employee registration successful");
				return new ResponseEntity(retmap, HttpStatus.OK);
			}else {
				//retmap.put("success", "false");
				logger.info("Employee registration unsuccessful");
				return new ResponseEntity(retmap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch (EmployeeNameAlreadyExist e) {
			retmap.put("success", "false");
			logger.error("Employee registration unsuccessful "+e.getMessage());
			throw new EmployeeNameAlreadyExist(e.getMessage());
			//return new ResponseEntity(retmap, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			//retmap.put("success", "false");
			logger.error("Employee registration unsuccessful "+e.getMessage());
			return new ResponseEntity(retmap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * This is to check the credit balance of an employee in a company.
	 * @param employeename, company name, email id.
	 * @return json with credit amount.
	 */
	@PostMapping(value="/credit/balance")
	public ResponseEntity<Map<String, Object>> getCreditBalanceEmployee(@RequestBody EmployeeRegisterDTO employee){
		Map<String, Object> retmap = new HashMap<>();
		logger.info("Employee credit balance check api");
    	Integer companyId = companyService.getCompanyId(employee.getCompany());
    	Optional<Integer> optCompanyId = Optional.ofNullable(companyId);
		//if(companyId.equals(null))
    	if (optCompanyId.isEmpty()) {
    		logger.info("The company id not found for company "+employee.getCompany());
			throw new CompanyNotFoundException("The company "+ employee.getCompany() + " Not found");
    	}
		Integer empId = empService.getEmployeeId(employee.getEmployeeName(), companyId);
		Optional<Integer> optEmpId = Optional.ofNullable(empId);
		if (optEmpId.isEmpty())
			throw new EmployeeNotFoundException("The employee "+ employee.getEmployeeName() + " Not found");
		
		Double balance = empService.getEmployeeBalance(empId);
		Optional<Double> optBalance = Optional.ofNullable(balance);
		if (optBalance.isPresent()) {
			retmap.put("credit", balance);
			logger.info("Employee credit balance check api successful");
			return new ResponseEntity(retmap, HttpStatus.OK);
		}else {
			retmap.put("credit", balance);
			logger.info("Employee credit balance check api unsuccessful");
			return new ResponseEntity(retmap, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	 * Desc: This endpoint is to get the balance of all the employees in a company.
	 * @param companyName
	 * @return json with names and balance.
	 */
	@GetMapping(value="/all/balance")
	public ResponseEntity<List<EmployeeBalanceDTO>> getAllemployeeBalance(@RequestParam String companyName) {
		List<EmployeeBalanceDTO> aList = null;
    	Integer companyId = companyService.getCompanyId(companyName);
    	if(companyId==null) {
    		logger.info("The company not found in db or not active in db.");
    		throw new CompanyNotFoundException("The company "+ companyName +" Not found or Not active");
    	}
    	aList = this.empService.getAllEmployeeBalance(companyId);
    	logger.info("The total numbe of employee with balance is: "+aList.size());
		return new ResponseEntity(aList, HttpStatus.OK);
		
	}
	
	/**
	 * Desc: This endpoint is to update the credit balance.
	 * @param employeeBalanceUpdate
	 * @return json with status true/false.
	 * 
	 */
	@PostMapping(value="/balance/update")
	public ResponseEntity<Map<String, Object>> updateEmployeeBalance(@RequestBody EmployeeBalanceUpdateDTO employeeBalanceUpdate) throws Exception{
		Map<String, Object> retmap= new HashMap<>();
		try {
			Double balance = this.empService.updateEmployeeBalance(employeeBalanceUpdate.getEmployeeName(), employeeBalanceUpdate.getCompanyName(), employeeBalanceUpdate.getTrxnType(), employeeBalanceUpdate.getAmount());
			if(balance!=null) {
				retmap.put("status", "true");
				logger.info("Update employee credit balance is successful");
				return new ResponseEntity(retmap, HttpStatus.OK);
			}else {
				retmap.put("status", "false");
				logger.info("Update employee credit balance is Unsuccessful");
				return new ResponseEntity(retmap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch (Exception e) {
			retmap.put("status", "false");
			logger.info("Update employee credit balance is unsuccessful"+e.getMessage());
			return new ResponseEntity(retmap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 * This endpoint is to get all the employees with active status.
	 * @param companyId
	 * @return
	 */
	@GetMapping(value="/allEmployee")
	public ResponseEntity<Map<Integer, String>> getAllEmployeeByCompany(@RequestParam Integer companyId) {
    	Map<Integer, String> map = this.empService.getAllEmployee(companyId, "Active");
    	logger.info("The number of employees is:"+map.size());
		return new ResponseEntity(map, HttpStatus.OK);
	}
}
