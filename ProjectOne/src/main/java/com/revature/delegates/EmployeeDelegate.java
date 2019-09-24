package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.service.EmployeeService;

import reim.model.Employee;

public class EmployeeDelegate {

	private EmployeeService es = new EmployeeService();

	public void getEmployees(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String emplId = request.getParameter("emplId");
		String deptId = request.getParameter("deptId");

		if (emplId == null && deptId == null) {
			List<Employee> employees = es.getAllEmployees();

			try (PrintWriter pw = response.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(employees));
			}
		}
		else if (emplId != null) {
			if(emplId.matches("^\\d+$")) {
				Employee e = es.getEmployeeById(Integer.parseInt(emplId));
				if(e == null) {
					response.sendError(404, "No employee with given id");
				} else {
					try(PrintWriter pw = response.getWriter()){
						pw.write(new ObjectMapper().writeValueAsString(e));
					}
				}
			}
		}
		else if (deptId != null) {
			if(deptId.matches("^\\d+$")) {
				List<Employee> employees = es.getEmployeesByDeptId(Integer.parseInt(deptId));
				if(employees == null) {
					response.sendError(404, "No employees in that given department");
				} else {
					try(PrintWriter pw = response.getWriter()){
						pw.write(new ObjectMapper().writeValueAsString(employees));
					}
				}
			}
		}
		else {
			response.sendError(400, "Invalid parameter");
		}

	}
	
	public void postEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("do nothing inside post employee");
	}

}
