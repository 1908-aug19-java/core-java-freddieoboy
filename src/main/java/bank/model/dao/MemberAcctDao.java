package bank.model.dao;

import java.util.List;

import bank.model.MemberAcct;


public interface MemberAcctDao {

public List<MemberAcct> getMemberAcct();
	
	public List<MemberAcct> getMemberAcctById(int id);
	/*
	 * sql function does this
	public int createMemberAcct(MemberAcct ma);
	
	public int updateMemberAcct(MemberAcct ma);
	*/
	/*
	public int updateMemberAcctNumber(MemberAcct ma);
	public int updateMemberAcctId(MemberAcct ma);
	*/
	//were not allowing this for a bank
	//public int removeAccount(Account a);
	
}
