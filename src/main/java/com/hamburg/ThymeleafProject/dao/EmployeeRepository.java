package com.hamburg.ThymeleafProject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hamburg.ThymeleafProject.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	
	
	// sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();
}