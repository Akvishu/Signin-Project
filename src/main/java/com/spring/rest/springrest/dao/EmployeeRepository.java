package com.spring.rest.springrest.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.springrest.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {
	
//	Employee findEmployee(String emailId);
	Employee findByEmail(String email);
	boolean existsByEmail(String email);

}
