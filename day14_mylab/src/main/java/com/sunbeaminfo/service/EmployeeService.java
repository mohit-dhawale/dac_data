package com.sunbeaminfo.service;

import java.util.List;

import com.sunbeaminfo.entities.Employee;

public interface EmployeeService {
	List<Employee> getAllEployees();
	
	Employee addEmpDetail(Employee emp);
	
	Employee getEmpDetails(Long empId);
	
	String deleteEmployee (Long empId);
}
