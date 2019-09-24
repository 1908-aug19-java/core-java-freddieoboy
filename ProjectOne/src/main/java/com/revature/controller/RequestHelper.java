package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.AuthDelegate;
import com.revature.delegates.EmployeeDelegate;
import com.revature.delegates.ReimbursementDelegate;
import com.revature.delegates.ViewDelegate;

public class RequestHelper {
	
	private ViewDelegate vd = new ViewDelegate();
	private AuthDelegate aud = new AuthDelegate();
	private EmployeeDelegate ed = new EmployeeDelegate();
	private ReimbursementDelegate rd = new ReimbursementDelegate();
	
	
	public void processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uri = request.getServletPath();
		System.out.println("URI: " + uri);
		if(uri.startsWith("/api/")) {
			// we want to handle this as if it's requesting (a) record(s)
			String record = uri.substring(5);
			switch(record) {
			case "employees":
				// process with EmployeeDelegate
				ed.getEmployees(request, response);
				break;
			case "reimbursements":
				// process with DepartmentDelegate
				rd.getReimbursements(request, response);
				//dd.getDepartments(request, response);
				break;
			default:
				response.sendError(404, "record not supported");
			}
		} else {
			// we want to handle this as if it's requesting a view
			vd.returnView(request, response);
		}
	}
	
	public void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getServletPath();
		switch(path) {
		case "/login":
			aud.authenticate(request, response);
			break;
		case "/reimbursements":
			rd.postReimbursement(request, response);
			break;
		default:
			response.sendError(405);
		}
	}

}
