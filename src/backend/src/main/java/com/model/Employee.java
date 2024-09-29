/**
 * Project: Tellar Banking System
 * Desc: This is to map the employee table
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "company_id")
	private Integer companyId;
	
	@Column(name = "name") 
	private String name;
	
	@Column(name="email") 
	private String email;
	
	@Column(name="status") 
	private String status;
	
	@Column(name = "remark") 
	private String remark;
	
	@Column(name = "created_date")
	@CreationTimestamp 
	private Timestamp createdDate;
	
	@Column(name = "updated_date")
	@UpdateTimestamp 
	private Timestamp updatedDate;
//	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "employeeId")
//	private EmployeeBalance employeeBalance;
	
}
