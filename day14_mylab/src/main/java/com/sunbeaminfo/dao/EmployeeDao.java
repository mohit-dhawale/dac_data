package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunbeaminfo.entities.Employee;
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long>{
	//How to get all emps ? --> use inherited API : findAll
	
	
	
}
