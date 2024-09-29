/**
 * Project: Tellar Banking System
 * Desc : This is the JPA repository interface for audit log table.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Auditlog;

@Repository
public interface AuditLogRepository extends JpaRepository<Auditlog, Integer>{

}
