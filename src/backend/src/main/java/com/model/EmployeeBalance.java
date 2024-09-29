/**
 * Project: Tellar Banking System
 * Desc: This is to map the Employee balance table
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_balance")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBalance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "employee_id")
	private Integer employeeId;
	
	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "balance")
	private Double balance;

	@Column(name="created_date")
	@CreationTimestamp
	private Timestamp createdDate;
	
	@Column(name = "updated_date")
	@UpdateTimestamp
	private Timestamp updatedDate;
}
