package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeaminfo.pojos.Department;

public interface DepartmentDao extends JpaRepository<Department,Long>{
	//select e from Employee e where e.selectedDept.id = :id;
	
//	List<Department> getAllDepartments();
}
