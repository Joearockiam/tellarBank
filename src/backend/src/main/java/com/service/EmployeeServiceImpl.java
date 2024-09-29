/**
 * Project: Tellar Banking System
 * Desc: This class implements the code for the abstract method declared in the Employee interface
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exception.EmployeeNameAlreadyExist;
import com.exception.EmployeeNotFoundException;
import com.model.Company;
import com.model.Employee;
import com.model.EmployeeBalance;
import com.model.EmployeeBalanceDTO;
import com.model.EmployeeRegisterDTO;
import com.repository.AccountRepository;
import com.repository.EmployeeBalanceRepository;
import com.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private EmployeeBalanceRepository empbalanceRepository;
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * This method is to create the employee with default active status
	 * @param empName
	 * @param email
	 * @param companyName
	 * @return true
	 */
	private boolean createEmployee(String empName, String email, String companyName) {
			Employee emp = Employee.builder()
				       .name(empName)
				       .email(email)
				       .status("Active")
				       .companyId(companyService.getCompanyId(companyName))
				       .build();
		   this.employeeRepository.save(emp);
		   return true;
	}

	/**
	 * This method is to get the employee id for the given employee name and company id
	 * @param employee name, company id
	 * @return employee Id
	 */
	@Override
	public Integer getEmployeeId(String empName, Integer companyId) {
		Employee dbEmployee = this.employeeRepository.findIdByNameAndCompanyId(empName, companyId);
		
		Optional<Employee> dbEmployeeOptional = Optional.ofNullable(dbEmployee);

		if (dbEmployeeOptional.isPresent()) {
			 return dbEmployeeOptional.get().getId();
		 }else {
			 throw new EmployeeNotFoundException("The company emp service"+ empName + " Not found");
		 }
		
		 //return null;
	}

	/**
	 * This method is to get the employee balance for the given employee id
	 * @param employee id
	 * @return balance if employee present; otherwise null
	 */
	@Override
	public Double getEmployeeBalance(Integer employeeId) {
		EmployeeBalance empBalance = this.empbalanceRepository.findByEmployeeId(employeeId);
		Optional<EmployeeBalance> dbEmployeeOptional = Optional.ofNullable(empBalance);

		if (dbEmployeeOptional.isPresent()) {
			 return dbEmployeeOptional.get().getBalance();
		 }
		
		 return null;
	}

	/**
	 * This method is to register the employee
	 * @param employee details such as empname,company name, employee email
	 * @return employee default credit value
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public double registerEmployee(EmployeeRegisterDTO register){
		Double balance = null;
		Integer companyId = null;
		try {
			companyId =  this.companyService.getCompanyId(register.getCompany());
			if (companyId == null)
				companyId = this.companyService.createCompany(register.getCompany());
            
			if(companyId!=null) {
				boolean empStatus = createEmployee(register.getEmployeeName(), register.getEmail(), register.getCompany());
				if(empStatus) {
					//companyId = companyService.getCompanyId(register.getCompany());
					int empId = getEmployeeId(register.getEmployeeName(), companyId);
					balance = this.accountService.createAccount(empId, companyId);
				}
			}
		}catch (Exception e) {
			throw new EmployeeNameAlreadyExist("Employee Name already exists in the same company");
		}
		return balance;
	}

	/**
	 * This method to get all the employee balance of a company
	 * @param company id
	 * @return list of employee with name and balance.
	 */
	@Override
	public List<EmployeeBalanceDTO> getAllEmployeeBalance(Integer companyId) {
		 List<EmployeeBalanceDTO> retList = new ArrayList<>();
		 List<Object[]> list = this.employeeRepository.findAllEmpoyeeByCompanyId(companyId);
		 EmployeeBalanceDTO dto = null;
		 for(Object[] o: list) {
			 dto = new EmployeeBalanceDTO();
			 dto.setName(o[0].toString());
			 dto.setBalance(Double.valueOf(o[1].toString()));
			 retList.add(dto);
		 }
		 return retList;
	}

	/**
	 * This is to update the employee balance
	 * @param employee name, company name, transaction type, amount
	 * @return balance
	 */
	@Override
	public Double updateEmployeeBalance(String employeeName, String companyName, String trxnType, double trxnAmount) {
		Integer companyId = this.companyService.getCompanyId(companyName);
	    Integer employeeId = getEmployeeId(employeeName,companyId);
	    Double balance = this.accountService.updateAccount(employeeId, companyId, trxnType, trxnAmount);
		return balance;
	}

	@Override
	public Map<Integer, String> getAllEmployee(Integer companyId, String status) {
		List<Employee> dbEmployeeList = employeeRepository.findByCompanyIdAndStatus(companyId,"Active");
		Optional<List<Employee>> dbEmployeeListOptional = Optional.ofNullable(dbEmployeeList);
		Map<Integer, String> aMap = null;
		if (dbEmployeeListOptional.isPresent()) {
			aMap = new HashMap<>();
			 for(Employee e: dbEmployeeList) {
				 aMap.put(e.getId(), e.getName());
			 }
		
		}
		return aMap;
	}
	
	
}
