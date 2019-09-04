package bank.model.dao;

import java.sql.Timestamp;
import java.util.List;

import bank.model.Member;

public interface MemberDao {
	
	public List<Member> getMember();
	
	public Member getMemberById(int id);
	public Member getMemberByUsername(String s);
	public Member getMemberByEmail(String s);
	
	public int createMember(Member m, String password);
	
	public int updateMember(Member m);
	
	public Timestamp getCurrentTime();
	/*
	public int updateMemberUsername(Member m);
	public int updateMemberFirstname(Member m);
	public int updateMemberLastname(Member m);
	public int updateMemberEmail(Member m);
	public int updateMemberLastLogin(Member m);
	*/
	
	// not going to implement delete because records are important with banks
	//public int removeMemberById(int id);
	

}
