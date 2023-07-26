package com.sunbeaminfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunbeaminfo.service.EmployeeService;

@Controller
@RequestMapping("/emps")
public class EmployeeController {
	//dependency : emp service i/f
	@Autowired
	private EmployeeService empService;
	
	public EmployeeController() {
		System.out.println("in ctor of " + getClass());
	}
	// add method to display emp details by the selected department
	// http://host:port/day_Practise/emps/list?deptId=10 method = GET
	@GetMapping("/list")
	public String listEmpsByDepartment(Model map,@RequestParam Long deptId)
	// SC : Long deptId = Long.parseLong(request.getParameter("deptId"));
	// @RequestParam : annotation for data binding from req param --> method arg
	{
		System.out.println("in list emps by dept" + map+ " " + deptId);
		//invoke service's method toget the list of emps
		map.addAttribute("emp_list",empService.getAllEmpsByDeprtment(deptId));
		return "/emps/list"; //AVN --> WEB-INF/veiws/emps/list.jsp will goto employee service
	}
}
