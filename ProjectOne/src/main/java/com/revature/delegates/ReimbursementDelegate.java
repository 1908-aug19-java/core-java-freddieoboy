package com.revature.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.service.ReimbursementService;

import reim.model.Reimbursement;

public class ReimbursementDelegate {

	private ReimbursementService rs = new ReimbursementService();

	public void getReimbursements(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String emplId = request.getParameter("emplId");
		String deptId = request.getParameter("deptId");

		if (emplId == null && deptId == null) {
			List<Reimbursement> reimbursements = rs.getAllReimbursements();

			try (PrintWriter pw = response.getWriter()) {
				pw.write(new ObjectMapper().writeValueAsString(reimbursements));
			}
		}
		else if (emplId != null) {
			if(emplId.matches("^\\d+$")) {
				List<Reimbursement> reimbursements = rs.getReimbursementsByEmplId(Integer.parseInt(emplId));
				System.out.println("reim delegate emplId returned with list of reimbursements");
				if(reimbursements == null) {
					System.out.println("list = null");
					response.sendError(404, "No reimbursements for that given employee with that id");
				} else {
					System.out.println("we got reims");
					try(PrintWriter pw = response.getWriter()){
						pw.write(new ObjectMapper().writeValueAsString(reimbursements));
					}
				}
			}
		}
		else if (deptId != null) {
			if(deptId.matches("^\\d+$")) {
				List<Reimbursement> reimbursements = rs.getReimbursementsByDeptId(Integer.parseInt(deptId));
				if(reimbursements == null) {
					response.sendError(404, "No reimbursements for that given department with that id");
				} else {
					try(PrintWriter pw = response.getWriter()){
						pw.write(new ObjectMapper().writeValueAsString(reimbursements));
					}
				}
			}
		}
		else {
			response.sendError(400, "Invalid parameter");
		}

	}
	
	public void postReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//System.out.println(request.getParameter("desc"));
				//name=Samuel+Doritoz&date=2019-09-20&expense-type=2&desc=ewsfsd&number=dfsds&status=Pending
				String name = request.getParameter("name");
				String date = request.getParameter("date");
				String expenseTypeStr = request.getParameter("expense-type");
				String desc = request.getParameter("desc");
				String priceStr = request.getParameter("number");
				String status = request.getParameter("status");
				
				if (name == "" || date == "" || expenseTypeStr == "" || desc == "" || priceStr == "" || status == "") {
					System.out.println("this should be validated on js side");
					response.sendError(400, "All fields were not filled out.");
					/*
					try(PrintWriter pw = response.getWriter()){
						pw.write("All fields were not filled out correctly.");
					}
					*/
				} 
				else {
					System.out.println("Validated haha");
					try(PrintWriter pw = response.getWriter()){
						pw.write("Submitted.");
					}
					
				}
				/*
				 * lol dont do below
				String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				System.out.println(requestBody);
				String[] params = requestBody.split("&");
				System.out.println(Arrays.deepToString(params));
				*/
	}
	
}
