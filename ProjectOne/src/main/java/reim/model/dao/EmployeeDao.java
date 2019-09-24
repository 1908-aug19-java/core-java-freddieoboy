package reim.model.dao;

import java.sql.Timestamp;
import java.util.List;

import reim.model.Department;
import reim.model.Employee;


public interface EmployeeDao {
	
	public List<Employee> getEmployees();
	
	public List<Employee> getEmployeesByDepartment(Department d);
	
	
	
	//public List<Employee> getEmployeesAgain();
	
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByUsername(String s);
	public Employee getEmployeeByUsernameAndPassword(String username, String password);
	
	public Employee getEmployeeByEmail(String s);
	
	/*
	 * NEEDS TO RETURN RESULT SET SO THAT I CAN GET EMPLID AND CURRENTTIMESTAMP THAT SQL USES
	 */
	public int createEmployee(Employee e, String password);
	
	public int updateEmployee(Employee e);
	
	public int deleteEmployeeById(int id);
	
	public Timestamp getCurrentTime();

}
