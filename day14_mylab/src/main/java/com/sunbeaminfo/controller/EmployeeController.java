package com.sunbeaminfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeaminfo.entities.Employee;
import com.sunbeaminfo.service.EmployeeService;



@RestController // mandatory class level anno, consist of = @Controller : cls level
// + @ResponseBody : rets type of req handling methods (@Requestmapping/@GetMapping/@PostMapping
@RequestMapping("/employees")
@CrossOrigin(origins="*")
public class EmployeeController {
	// dep: service layer i:f
	@Autowired
	private EmployeeService empService;

	public EmployeeController() {
		System.out.println("in cnstr " + getClass());
	}

	// add a method : REST API end point, to get all emps
	// REq : http://host:port/employees.method ="GET"
	@GetMapping
	public List<Employee> listAllEmps() {
		return empService.getAllEployees();
	}
	// REq : http://host:port/employees method ="POST"
	@PostMapping
	public Employee addEmpDetails(@RequestBody Employee emp) 
	// @RequestBody : mandatory method args lvl anno for deserialing / un-marshalling
	// ->json to java
	{
		System.out.println(emp);
		System.out.println("in save emp " + emp.getPassword());// null(transient)
		return empService.addEmpDetail(emp);
	}
	
	// add a method ; REST API end point. to delete amp details
	// Req : http://localhost:8080/employees/empId, method ="DELETE"
	@DeleteMapping("/{empId}")
	// @PathVariable : method arg level anno , for biding URI template var to request handling method arg
	public String deleteEmpDetails(@PathVariable Long empId) {
		return empService.deleteEmployee(empId);
	}
	
	// add a method ; REST API end point. to get emp details
		// Req : http://localhost:8080/employees/empId, method ="GET"
	@GetMapping("/{id}")
	public Employee getEmpDetailsById(@PathVariable Long id) {
		System.out.println("in get emp details "+ id);
		return empService.getEmpDetails(id);
	}
	
	// add a method ; REST API end point. to delete amp details
		// Req : http://localhost:8080/employees/empId, method ="DELETE"
	@PutMapping
	public Employee updateEmpDetails(@RequestBody Employee detachedEmp) {
		//validate
		System.out.println("in update emp "+ detachedEmp.getId());
		// emp exist by the id --> continue to update
		return empService.addEmpDetail(detachedEmp);
	}
}
