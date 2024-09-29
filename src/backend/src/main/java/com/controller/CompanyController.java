/**
 * Project: Tellar Banking System
 * Description: This controller is to handle the request related to company 
 * such as to get all the company names.
 *               
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exception.CompanyNotFoundException;
import com.exception.NoDataFoundException;
import com.model.Company;
import com.model.EmployeeBalanceDTO;
import com.service.CompanyService;
import com.service.EmployeeService;

@RestController
@RequestMapping(value = "/api/company/")
@CrossOrigin(origins = "*")
public class CompanyController {
	
	private static final Logger logger = LogManager.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyService companyService;

	/**
	 * This endpoint is to get all the active companies.
	 * @return Map with company names with its Id.
	 */
	@GetMapping(value="/all/name")
	public ResponseEntity<Map<Integer, String>> getAllCompanyNames() {
		Map<Integer, String> map = companyService.getAllActiveCompany("Active");
    	if(map==null) {
    		logger.error("There is no Active employees in the DB");
    		throw new NoDataFoundException("No Active companies Found");
    	}
		return new ResponseEntity(map, HttpStatus.OK);
		
	}
}
