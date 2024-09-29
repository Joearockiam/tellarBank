/**
 * Project: Tellar Banking System
 * Desc: This is to handle the employee already exists in the same company.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 **/
package com.exception;

public class EmployeeNameAlreadyExist extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EmployeeNameAlreadyExist(String message) {
		super(message);
	}

}

