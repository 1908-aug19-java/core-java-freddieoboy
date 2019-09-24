package com.revature.service;

import java.util.List;

import reim.model.Department;
import reim.model.Employee;
import reim.model.Reimbursement;
import reim.model.dao.ReimbursementDao;
import reim.model.dao.impl.ReimbursementDaoImpl;

public class ReimbursementService {
	
	ReimbursementDao rd = new ReimbursementDaoImpl();

	public List<Reimbursement> getAllReimbursements() {

		return rd.getReimbursement();
	}
	
	public List<Reimbursement> getReimbursementsByDeptId(int id) { 
		Department d = new Department(id);
		return rd.getReimbursementsByDepartment(d);
	}

	public List<Reimbursement> getReimbursementsByEmplId(int id){
		Employee e = new Employee(id);
		
		//return rd.getReimbursementsByEmployee(e);
		
		List<Reimbursement> reims = rd.getReimbursementsByEmployee(e);
		for (Reimbursement r : reims) {
			System.out.println(r);
		}
		return reims;
		
	}
	
}
