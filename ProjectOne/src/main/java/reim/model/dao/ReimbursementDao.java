package reim.model.dao;

import java.sql.Timestamp;
import java.util.List;

import reim.model.Department;
import reim.model.Employee;
import reim.model.Reimbursement;

public interface ReimbursementDao {
	
	public List<Reimbursement> getReimbursement();
	
	public List<Reimbursement> getReimbursementsByDepartment(Department d);

	public List<Reimbursement> getReimbursementsByEmployee(Employee e);
	//public List<Employee> getEmployeesAgain();
	
	public Reimbursement getReimbursementById(int id);
	public Reimbursement getReimbursementByEmplId(int id);
	public Reimbursement getReimbursementByManagerEmplId(int id);
	public Reimbursement getReimbursementByStatus(String s);
	public Reimbursement getReimbursementByExpenseType(String s);
	public Reimbursement getReimbursementInBetweenDates(Timestamp a, Timestamp b);
	
	public int createReimbursement(Reimbursement r);
	
	public int updateReimbursement(Reimbursement r);
	
	//public int deleteReimbursementById(int id);
	

}
