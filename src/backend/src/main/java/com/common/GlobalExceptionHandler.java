/**
 * Project: Tellar Banking System
 * Description: This class is for the global exception handler.
 *              All the global exception are declared here.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0 
 */

package com.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exception.CompanyNotFoundException;
import com.exception.EmployeeNameAlreadyExist;
import com.exception.EmployeeNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * This exception is to handle if company is not found for the given company name.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({CompanyNotFoundException.class})
	public ResponseEntity<String> handleCompanyNotFoundException(CompanyNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	/**
	 * This exception will be thrown if the employee not found.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({EmployeeNotFoundException.class})
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}

	/**
	 * This exceotion will be throws if we try to register same employee in a company already exist.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({EmployeeNameAlreadyExist.class})
	public ResponseEntity<String> handleEmployeeNameAlreadyExist(EmployeeNameAlreadyExist ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
}
