package com.hamburg.ThymeleafProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hamburg.ThymeleafProject.entity.Employee;
import com.hamburg.ThymeleafProject.service.EmployeeService;



@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	

	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		// get employees from db
		List<Employee> theEmployees = employeeService.findAll();
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		return "employees/list-employees";
	}
	
	
	
	@GetMapping("/showFormForAdd")
	public String showFormforAdd(Model theModel) {
		
		// create model attirbut to bind 
		Employee theEmployee = new Employee();
		
		// add to the spring model
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/employee-form")
	public String employeeForm(Model theModel) {
		
		// create model attirbut to bind 
		Employee theEmployee = new Employee();
		
		// add to the spring model
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId")int theId, 
									Model theModel) {
		
		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		
		// send over to our form
		return "employees/employee-form";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId")int theId) {
		
		// get the employee from the service
		employeeService.deleteById(theId);	
		
		// send over to our form
		return "redirect:/employees/list";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		// redirect to to prevent duplicate submissions
		return "redirect:/employees/list";		
		
	}
	
	/*
	 * @GetMapping("/delete") public String delete(@RequestParam("employeeName")
	 * String theName, Model theModel) {
	 * 
	 * // delete the employee List<Employee> theEmployees =
	 * employeeService.searchBy(theName);
	 * 
	 * // add to the spring model theModel.addAttribute("employees", theEmployees);
	 * 
	 * // send to /employees/list return "/employees/list-employees"; }
	 */
	
	@GetMapping("/search")
	public String search(@RequestParam("employeeName") String theName,
						 Model theModel) {
		
		// delete the employee
		List<Employee> theEmployees = employeeService.searchBy(theName);
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		// send to /employees/list
		return "/employees/list-employees";
		
	}
	
	
}













