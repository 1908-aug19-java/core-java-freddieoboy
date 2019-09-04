package bank.model.dao;

import java.util.List;

import bank.model.Account;

public interface AccountDao {
	
	public List<Account> getAccount();
	
	public Account getAccountByAcctNum(int acctNum);
	
	public List<Account> getAccountById(int memberId);
	
	public int createAccount(Account a, int memberId);
	
	public void addJointAccount(int memberId, Account a);
	
	public float depositCash(Account a, float amt);
	
	public float withdrawCash(Account a, float amt);
	
	public String transferFunds(Account send, Account rec, float amt);
	
	//this breaks a lot of meaning in our code so were just not gunna allow this
	//public int updateAccount(Account a);
	/*
	public int updateAccountNumber(Account a);
	public int updateAccoutBalance(Account a);
	*/
	//were not allowing this for a bank
	//public int removeAccount(Account a);

}
