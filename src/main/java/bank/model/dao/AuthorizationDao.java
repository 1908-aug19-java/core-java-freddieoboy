package bank.model.dao;

import java.util.List;

import bank.model.Authorization;

public interface AuthorizationDao {

	public List<Authorization> getAuthorization();
	
	public Authorization getAuthorizationById(int id);
	
	//found out i shouldnt have made my own authorization table that complicates things
	// authorizations are created when creating new member
	//public int createAuthorization(Member m, Authorization au);
	
	public int updateAuthorization(Authorization au);
	
	/*
	public int updateAuthorizationId(Authorization au);
	public int updateAuthorizationPassword(Authorization au);
	*/
	//were not allowing this for a bank
	//public int removeAccount(Account a);	
	
}
