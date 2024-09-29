/**
 * Project: Tellar Banking System
 * Desc: This interface is to declare the necessary abstract methods for the company transaction.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.service;

import java.util.List;
import java.util.Map;

import com.model.Company;

public interface CompanyService {
	/**
	 * To get the Company details by providing the companyId
	 * @param companyId
	 * @return Company object
	 */
	public Company getCompany(Integer companyId);

	/**
	 * To get the company Id for the given company name
	 * @param companyName
	 * @return companyId
	 */
	public Integer getCompanyId(String companyName);
	
	/**
	 * This is to create the company in company table
	 * @param companyName
	 * @return companyId
	 */
	public Integer createCompany(String companyName);
	/**
	 * This is to update the company 
	 * @param companyName
	 * @return true/false
	 */
	public boolean updateCompany(String companyName, String status);
	
	/**
	 * This is to get list of company names which are active.
	 * @param status
	 * @return Map<Integer, String> companyId, company name
	 */
	public Map<Integer, String> getAllActiveCompany(String status);
}
