package com.employees.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employees.department.client.EmployeeClient;
import com.employees.department.model.Department;
import com.employees.department.repository.DepartmentCRUDRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DepartmentController {
	
	@Autowired
	DepartmentCRUDRepository repository;
	@Autowired
	EmployeeClient employeeClient;
	
	
	@PostMapping("/")
	public Department add(@RequestBody Department department) {
		log.info("Department add: {}", department);
		return repository.save(department);
	}
	
	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") String id) {
		log.info("Department find: id={}", id);
		return repository.findById(id).get();
	}
	
	@GetMapping("/")
	public Iterable<Department> findAll() {
		log.info("Department find");
		return repository.findAll();
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		log.info("Department find: organizationId={}", organizationId);
		return repository.findByOrganizationId(organizationId);
	}
	
	@GetMapping("/organization/{organizationId}/with-employees")
	public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
		log.info("Department find: organizationId={}", organizationId);
		List<Department> departments = repository.findByOrganizationId(organizationId);
		departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
		return departments;
	}

}
