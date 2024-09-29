/**
 * Project: Tellar Banking System
 * Desc : This is the JPA repository interface for Account Transaction table.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */

package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	/**
	 * Desc: This is get the company object by passing the company from company table.
	 * @param companyName
	 * @return Company object
	 */
	public Company findIdByName(String companyName);
	
	/**
	 * Desc: This function is to get all the companies based on status(Active/Inactive)
	 * @param status
	 * @return List<Comapny>
	 */
	public List<Company> findAllByStatus(String status);
}
