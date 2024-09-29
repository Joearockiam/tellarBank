/**
 * Projct: Tellar Banking System.
 * Desc: This is to map the Account transaction table
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="account_txn")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	@Column(name = "txn_type")
	private String txnType;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "created_date")
	private Timestamp createdDate;
	
}
