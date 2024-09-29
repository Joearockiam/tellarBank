/**
 * project: Tellar Banking System
 * Desc: This class implememting the code for the abstract method declared in the company service interface.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exception.CompanyNotFoundException;
import com.model.Company;
import com.repository.CompanyRepository;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService{

	private static final Logger logger = LogManager.getLogger(CompanyServiceImpl.class);

	@Autowired
	private CompanyRepository companyRepository;
	
	@Value("${default.credit.amount}")
	private Double defaultCredit;
	
	/**
	 * This method is to get te company details for the given company id
	 * @param company id
	 * @return companyId or null;
	 */
	@Override
	public Company getCompany(Integer companyId) {
		Optional<Company> dbCompanyOptional = this.companyRepository.findById(companyId);
		logger.info("----getCompany----"+companyId);
		if (dbCompanyOptional.isPresent()) {
			logger.info("----getCompany----"+companyId+" have data");
			 return dbCompanyOptional.get();
		 }
		logger.info("----getCompany---- returns null");
		 return null;
		
	}

	/**
	 * This is to create the company record in company table
	 * @param company name
	 * @return company id or null
	 */
	@Override
	public Integer createCompany(String companyName) {
		Company company = Company.builder().name(companyName)
		          .status("Active")
		          .defaultCredit(defaultCredit)
		          .build();
		this.companyRepository.save(company);
		
		Company dbCompany = this.companyRepository.findIdByName(companyName);
		Optional<Company> optCompany = Optional.ofNullable(dbCompany);
		if (optCompany.isPresent())
			return optCompany.get().getId();
		
		return null;
	}

	/**
	 * This is to update the company status
	 * @param company name, status
	 * return true or false
	 */
	@Override
	public boolean updateCompany(String companyName, String status) {
		Company dbCompany = this.companyRepository.findIdByName(companyName);
		Optional<Company> optCompany = Optional.ofNullable(dbCompany);
		logger.info("----updateCompany----companyName:"+companyName+" status:"+status);
		if (optCompany.isPresent()) {
			dbCompany.setStatus(status);
			this.companyRepository.save(dbCompany);
			return true;
		}
		else
			return false;
	}

	/**
	 * This method is to get the company id for the given company name
	 * @param company name
	 * @return company id or null 
	 */
	@Override
	public Integer getCompanyId(String companyName) {
		
		Company dbCompany = this.companyRepository.findIdByName(companyName);
		Optional<Company> dbCompanyOptional = Optional.ofNullable(dbCompany);

		if (dbCompanyOptional.isPresent())
			 return dbCompanyOptional.get().getId();
		
		return null;
	}

	@Override
	public Map<Integer, String> getAllActiveCompany(String status) {
		// TODO Auto-generated method stub
		List<Company> dbCompanyList = companyRepository.findAllByStatus(status);
		Optional<List<Company>> dbCompanyListOptional = Optional.ofNullable(dbCompanyList);
		Map<Integer, String> aMap = null;
		if (dbCompanyListOptional.isPresent()) {
			aMap = new HashMap<>();
			 for(Company c: dbCompanyList) {
				 aMap.put(c.getId(), c.getName());
			 }
		
		}
		return aMap;
	}
}
