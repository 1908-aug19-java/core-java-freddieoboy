package bank.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bank.model.Account;
import bank.model.Member;
import bank.model.MemberAcct;
import bank.model.dao.MemberAcctDao;
import bank.model.util.ConnectionUtil;

public class MemberAcctDaoImpl implements MemberAcctDao {

	Member member = null;
	Account account = null;
	MemberAcct memberAcct = null;
	
	@Override
	public List<MemberAcct> getMemberAcct() {
		String sql = "select m.memb_id, m.user_name, m.email, m.first_name, m.last_name, m.last_login, a.acct_num, a.cur_bal from bank.\"Member\" m " + 
				"join bank.\"MemberAcct\" ma on m.memb_id = ma.memb_id " + 
				"join bank.\"Account\" a on ma.acct_num = a.acct_num;";
		
		List<MemberAcct> memberAccts = new ArrayList<>();
		
		// makes it autocloseable
		try (	Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			/*
			for(int i =0;i<rs.getMetaData().getColumnCount();i++) {
				System.out.println(rs.getMetaData().getColumnName(i+1));
			}*/
			
			while (rs.next()) {
				
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				int memberId = rs.getInt("memb_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				Timestamp lastLogin = rs.getTimestamp("last_login");
				
				int accountNum = rs.getInt("acct_num");
				float currentBalance = rs.getFloat("cur_bal");
				
				
//				System.out.println(deptId+deptName+salary);
				
				// create new member with member fields
				// create new acct with acct fields
				member = new Member(memberId, username, firstname, lastname, email, lastLogin);
				account = new Account(accountNum, currentBalance);
				
				memberAcct = new MemberAcct(member, account);
				//System.out.println(m);
				memberAccts.add(memberAcct);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memberAccts;
	}

	@Override
	public List<MemberAcct> getMemberAcctById(int membId) {
		List<MemberAcct> memberAccts = new ArrayList<>();
		String sql = "select m.memb_id, m.user_name, m.email, m.first_name, m.last_name, m.last_login, a.acct_num, a.cur_bal from bank.\"Member\" m " + 
				"join bank.\"MemberAcct\" ma on m.memb_id = ma.memb_id " + 
				"join bank.\"Account\" a on ma.acct_num = a.acct_num " +
				"where m.memb_id = ?;";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			ps.setInt(1, membId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				int memberId = rs.getInt("memb_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				Timestamp lastLogin = rs.getTimestamp("last_login");
				
				int accountNum = rs.getInt("acct_num");
				float currentBalance = rs.getFloat("cur_bal");
				
				
//				System.out.println(deptId+deptName+salary);
				
				// create new member with member fields
				// create new acct with acct fields
				member = new Member(memberId, username, firstname, lastname, email, lastLogin);
				account = new Account(accountNum, currentBalance);
				
				memberAcct = new MemberAcct(member, account);
				memberAccts.add(memberAcct);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		return memberAccts;
	}
	/*
	@Override
	public int createMemberAcct(MemberAcct ma) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMemberAcct(MemberAcct ma) {
		// TODO Auto-generated method stub
		return 0;
	}
	*/

}
