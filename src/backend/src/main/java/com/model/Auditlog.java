/**
 * Project: Tellar Banking System
 * Desc: This is to map the Audit log table
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "auditlog")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auditlog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "user_name")
	private String user;
	
	@Column(name = "api_call")
	private String apiCall;
	
	@Column(name = "api_payload")
	private String apiPayload;
	
	@Column(name = "old_value")
	private String oldValue;
	
	@Column(name = "updated_status")
	private String updatedStatus;
	
	@Column(name="created_date")
	@CreationTimestamp
	private Timestamp createdDate;
	
}
