package com.employees.department.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.employees.department.model.Department;

public interface DepartmentCRUDRepository extends CrudRepository<Department, String>{
	
	List<Department> findByOrganizationId(Long organizationId);

}
