package com.revature.service;

import java.util.List;

import reim.model.Department;
import reim.model.Employee;
import reim.model.dao.EmployeeDao;
import reim.model.dao.impl.EmployeeDaoImpl;

public class EmployeeService {
	
	private EmployeeDao ed = new EmployeeDaoImpl();
	
	public List<Employee> getAllEmployees() {
		return ed.getEmployees();
	}
	
	public Employee getEmployeeById(int id) {
		return ed.getEmployeeById(id);
	}
	
	public Employee getEmployeeByUsername(String username) {
		return ed.getEmployeeByUsername(username);
	}
	
	public Employee getEmployeeByUsernameAndPassword(String username, String password) {
		return ed.getEmployeeByUsernameAndPassword(username, password);
	}
	
	public List<Employee> getEmployeesByDeptId(int id) {
		Department d = new Department(id);
		return ed.getEmployeesByDepartment(d);
	}
	
	public boolean createEmployee(Employee e, String password) {
		
		int employeesCreated = ed.createEmployee(e, password);
		
		if (employeesCreated != 0) {
			return true;
		}
		return false;
	}


	
	// update and delete methods
}
