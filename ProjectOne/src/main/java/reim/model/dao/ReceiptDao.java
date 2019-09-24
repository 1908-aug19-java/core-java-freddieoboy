package reim.model.dao;

import java.util.List;

import reim.model.Receipt;

public interface ReceiptDao {
	
	public List<Receipt> getReceipt();
	//public List<Employee> getEmployeesAgain();
	
	public Receipt getReceiptById(int id);
	
	public int createReceipt(Receipt e);
	public int updateReceipt(Receipt e);
	
	//public int deleteReceiptById(int id);

}
