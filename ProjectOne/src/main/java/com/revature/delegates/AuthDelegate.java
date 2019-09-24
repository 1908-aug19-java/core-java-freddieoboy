package com.revature.delegates;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.AuthorizationService;
import com.revature.service.EmployeeService;

import reim.model.Authorization;
import reim.model.Employee;

public class AuthDelegate {
	
	private EmployeeService es = new EmployeeService();
	private AuthorizationService aus = new AuthorizationService();
	
	
	public boolean isAuthorized(HttpServletRequest request) {
		
		String authToken = request.getHeader("Authorization");
		
		// check to see if there is an auth header
		if(authToken!=null) {
			
			String[] tokenArr = authToken.split(":");
			// if the token is formatted the way we expect, we can take the id from it and query for that user
			if(tokenArr.length == 3) {
				String username = tokenArr[0];
				String password = tokenArr[1];
				//String heirarchy = tokenArr[2];
				
				Employee e = es.getEmployeeByUsername(username);
				if (e != null) {
					Authorization au = aus.getAuthorizationById(e.getEmplId());
					if (au.getPassword().equals(password)) {
						return true;
					}
				}
				
			}
		}
		return false;
	}
	
	
	
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		//service layer
		
		Employee e = es.getEmployeeByUsernameAndPassword(username, password);

		if(e != null) {
			
			//String token = u.getId()+":"+u.getUserRole();
			boolean isManager = checkIfEmployeeIsManager(e);
			
			String token = username + ":" + password;
			
			if (isManager) {
				token += ":m";
			}
			else {
				token += ":e";
			}
			
			token += ":" + e.getEmplId() + ":" + e.getDepartment().getDeptId();
			
			System.out.println("valid credientials");
			response.setStatus(200);
			
			response.setHeader("Authorization", token);
			
		} else {
			
			System.out.println("invalid credientials");
			response.sendError(401);
		}
		
		
	}
	
	public boolean checkIfEmployeeIsManager(Employee e) {
		
		if (e.getEmplId() == e.getDepartment().getDeptManagerEmplId()) {
			return true;
		}
		return false;
	}
	

}
