/**
 * Project: Tellar Banking System
 * Desc: This is to handle the company. if the given company is not found then this exception will be thrown.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 **/

package com.exception;

public class CompanyNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException(String message) {
		super(message);
	}

}
