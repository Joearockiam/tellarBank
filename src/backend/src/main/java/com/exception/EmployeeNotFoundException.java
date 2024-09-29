/**
 * Project: Tellar Banking System
 * Desc: This is to handle,  if the employee is not found for the given name/id.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 **/

package com.exception;

public class EmployeeNotFoundException extends RuntimeException{

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
