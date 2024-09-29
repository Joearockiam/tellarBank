/**
 * Project: Tellar Banking System
 * Desc: This is to handle if no data found in db..
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 **/
package com.exception;

public class NoDataFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NoDataFoundException(String message) {
		super(message);
	}

}
