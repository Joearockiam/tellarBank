/**
 * Project: Tellar Banking System
 * Desc: This is the data transfer object. used to send the employee balance as a response
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0      
 */
package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBalanceDTO {
	private String name;
	private Double balance;
	
}
