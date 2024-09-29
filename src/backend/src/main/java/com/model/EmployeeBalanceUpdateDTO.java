/**
 * Project: Tellar Banking System
 * Desc: This is the data transfer object. used to update the employee credit
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0      
 */
package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBalanceUpdateDTO {
	private String companyName;
	private String employeeName;
	private String trxnType;
	private Double amount;
}
