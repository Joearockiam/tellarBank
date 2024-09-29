package com.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.model.Company;
import com.model.Employee;
import com.model.EmployeeBalance;

@DataJpaTest 
class EmployeeRepositoryTest {

	@Autowired
    private EmployeeRepository employeeRepository;
	
	@Autowired
    private EmployeeBalanceRepository employeeBalanceRepository;
	
	@Test
	@DisplayName("Test 1: EmployeeRepository - FindIdByNameAndCompanyId")
	void testFindIdByNameAndCompanyId() {
		Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setCompanyId(101);
        employee.setStatus("active");
        employeeRepository.save(employee);
        
        Employee foundEmployee = employeeRepository.findIdByNameAndCompanyId("John Doe", 101);
        
        assertNotNull(foundEmployee);
        assertEquals("John Doe", foundEmployee.getName());
        assertEquals(101, foundEmployee.getCompanyId());
        
	}

	@Test
	@DisplayName("Test 2: EmployeeRepository - FindAllEmpoyeeByCompanyId")
	void testFindAllEmpoyeeByCompanyId() {
		 Employee emp1 = new Employee();
		 emp1.setName("John Doe");
		 emp1.setCompanyId(101);
		 emp1.setStatus("active");
	     employeeRepository.save(emp1);
	     
	     EmployeeBalance emp1Balance = new EmployeeBalance();
	     emp1Balance.setEmployeeId(employeeRepository.findIdByNameAndCompanyId("John Doe", 101).getId());
	     emp1Balance.setBalance(1000.00);
	     employeeBalanceRepository.save(emp1Balance);
	     
	     Employee emp2 = new Employee();
	     emp2.setName("Jane Smith");
	     emp2.setCompanyId(101);
	     emp2.setStatus("active");
	     employeeRepository.save(emp2);
	     	     
	     EmployeeBalance emp2Balance = new EmployeeBalance();
	     emp2Balance.setEmployeeId(employeeRepository.findIdByNameAndCompanyId("Jane Smith", 101).getId());
	     emp2Balance.setBalance(1000.00);
	     employeeBalanceRepository.save(emp2Balance);
	     
	     List<Object[]> empList = employeeRepository.findAllEmpoyeeByCompanyId(101);
	     
	     assertNotNull(empList);
	     assertEquals(2, empList.size());
     
	}

}
