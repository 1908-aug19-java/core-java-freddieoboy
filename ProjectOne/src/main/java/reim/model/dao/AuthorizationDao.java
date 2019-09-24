package reim.model.dao;

import java.util.List;

import reim.model.Authorization;



public interface AuthorizationDao {
	
public List<Authorization> getAuthorization();
	
	public Authorization getAuthorizationById(int id);
	
	//found out i shouldnt have made my own authorization table that complicates things
	// authorizations are created when creating new member
	//public int createAuthorization(Member m, Authorization au);
	
	public int updateAuthorization(Authorization au);

}
