package com.revature.main;

import com.revature.service.EmployeeService;

import reim.model.Employee;
import reim.model.dao.EmployeeDao;
import reim.model.dao.impl.EmployeeDaoImpl;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		
		EmployeeDao ed = new EmployeeDaoImpl();
		Employee empl = ed.getEmployeeByUsernameAndPassword("samudor","pswd");
		System.out.println(empl);
		
		EmployeeService es = new EmployeeService();
		empl = es.getEmployeeByUsernameAndPassword("samudor","pswd");
		System.out.println(empl);
		
		/*
		EmployeeDao ed = new EmployeeDaoImpl();
		List<Employee> employees = ed.getEmployees();
		for (Employee e: employees) {
			System.out.println(e);
		}
		
		
		System.out.println("-------------------------------------------------------------------------------------");
		DepartmentDao dd = new DepartmentDaoImpl();
		List<Department> departments = dd.getDepartment();
		
		for (Department d : departments) {
			System.out.println(d);
		}
		System.out.println("-------------------------------------------------------------------------------------");
		
		System.out.println(ed.getEmployeeById(21));
		System.out.println(dd.getDepartmentById(1));
		*/
		/*
		System.out.println("-------------------------------------------------------------------------------------");
		AuthorizationDao aud = new AuthorizationDaoImpl();
		List<Authorization> authorizations = aud.getAuthorization();
		
		for (Authorization au : authorizations) {
			System.out.println(au);
		}
		*/
/*
		
		DepartmentDao dd = new DepartmentDaoImpl();
		List<Department> departments = dd.getDepartment();
		
		for (Department d : departments) {
			System.out.println(d);
		}
		
		
		
		// step 2
		System.out.println(dd.getDepartmentById(4));
		// this is suppose to return null
		System.out.println(dd.getDepartmentById(25));
		
		//step 3
		// 5 is a placeholder
		Department d = new Department(5, "Accounting", 8000);
		d = new Department("Accounting", 8000);
		
		int depts = dd.createDepartment(d);
		System.out.println(depts);
		
		for (Department x : departments) {
			System.out.println(x);
		}
		
		//d = new Department(2, "Training", 90000.09);
		d = dd.getDepartmentById(2);
		d.setMonthlyBudget(8000);
		System.out.println(d);
		
		depts = dd.updateDepartment(d);
		System.out.println(depts);
		
		
		// step 5
		//depts = dd.deleteDepartment(6);
		System.out.println(depts);
		*/
	}

}
