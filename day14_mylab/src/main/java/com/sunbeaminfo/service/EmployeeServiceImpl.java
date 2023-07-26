package com.sunbeaminfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeaminfo.custom_exception.ResourcesNotFoundException;
import com.sunbeaminfo.dao.EmployeeDao;
import com.sunbeaminfo.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	//dep EmployeeDAo : i/f
	@Autowired
	private EmployeeDao empDao;
	@Override
	public List<Employee> getAllEployees() {
		// TODO Auto-generated method stub
		
		return empDao.findAll();
	}
	@Override
	public Employee addEmpDetail(Employee emp) {
		// TODO Auto-generated method stub
		// API of CrudRepository : T save(T entity)
		return empDao.save(emp);
	}// auto dirty checking -->  insert --> session closed --> rets detached emp to the caller
	@Override
	public Employee getEmpDetails(Long empId) {
		// TODO Auto-generated method stub
		return empDao.findById(empId).orElseThrow(()-> new ResourcesNotFoundException("Emp id invalid!!!"));
	}
	@Override
	public String deleteEmployee(Long empId) {
		// TODO Auto-generated method stub
		Employee emp = getEmpDetails(empId);
		empDao.delete(emp);
		return "emp sucessfully deleted";
	}

}
