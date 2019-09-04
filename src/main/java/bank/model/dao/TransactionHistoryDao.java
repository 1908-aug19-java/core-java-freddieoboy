package bank.model.dao;

import java.sql.Timestamp;
import java.util.List;

import bank.model.Account;
import bank.model.TransactionHistory;

public interface TransactionHistoryDao {

	public List<TransactionHistory> getTransactionHistory();
	
	public Timestamp getTransactionHistoryById(int id);
	
	public List<TransactionHistory> getTransactionHistoryByAccountNumber(int num);
	public List<TransactionHistory> getTransactionHistoryByDesc(String s);
	public List<TransactionHistory> getTransactionHistoryByAmount(float f);
	public List<TransactionHistory> getTransactionHistoryByBalance(float f);
	public List<TransactionHistory> getTransactionHistoryByTimestamp(Timestamp t);
	public Timestamp getTransactionHistoryMaxTimestamp(List<Account> accounts);
	
	/*
	public int createTransactionHistory(TransactionHistory th);
	
	public int updateTransactionHistoryId(TransactionHistory th);
	public int updateTransactionHistoryTimestamp(TransactionHistory th);
	public int updateTransactionHistoryDesc(TransactionHistory th);
	public int updateTransactionHistoryAccountNumber(TransactionHistory th);
	public int updateTransactionHistoryAmount(TransactionHistory th);
	public int updateTransactionHistoryBalance(TransactionHistory th);
	*/
	//not implementing remove
	
}
