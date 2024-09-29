/**
 * Project: Tellar Banking System
 * Desc: This is the data transfer object. used to register the employee with company name.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0      
 */
package com.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegisterDTO {
	private String email;
	private String company;
	private String employeeName;

}
