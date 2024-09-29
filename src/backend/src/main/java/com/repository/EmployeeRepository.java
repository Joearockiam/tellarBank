/**
 * Project: Tellar Banking System
 * Desc: This is for the employee table.
 * Author: Arockiam Joseph
 * Created Date: 29-09-2024
 * Version: 1.0
 */
package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Company;
import com.model.Employee;
import com.model.EmployeeBalanceDTO;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	/**
	 * This method will find and return the data for the given employee name and companyId
	 * @param employeeName
	 * @param companyId
	 * @return Employee object
	 */
	public Employee findIdByNameAndCompanyId(String name, Integer companyId);
	
	/**
	 * This method will find the employee name and employee balance for all the employee
	 * for the given company id who is active.
	 * @param companyId
	 * @return
	 */
	@Query(value="select emp.name as name, emp_bal.balance as balance from employee emp "
			+ "join employee_balance emp_bal on emp_bal.employee_id=emp.id where lower(emp.status)='active'"
			+ "and emp.company_id=?1",nativeQuery = true)
	public List<Object[]> findAllEmpoyeeByCompanyId(Integer companyId);
	
	/**
	 * This is to get the list of employees by CompanyId and status
	 * @param companyId
	 * @return List<Employees>
	 */
	public List<Employee> findByCompanyIdAndStatus(Integer companyId, String status);
	
}
