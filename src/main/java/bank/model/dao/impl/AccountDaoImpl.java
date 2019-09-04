package bank.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bank.model.Account;
import bank.model.dao.AccountDao;
import bank.model.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {
	Account a = null;
	
	@Override
	public List<Account> getAccount() {
		String sql = "select * from bank.\"Account\"";
		
		List<Account> accounts = new ArrayList<>();
		
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
				int accountNum = rs.getInt("acct_num");
				float currentBalance = rs.getFloat("cur_bal");
				
//				System.out.println(deptId+deptName+salary);
				a = new Account(accountNum, currentBalance);
				//System.out.println(m);
				accounts.add(a);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
	}
	
	@Override
	public List<Account> getAccountById(int memberId) {
		String sql = "select a.acct_num, a.cur_bal from bank.\"Member\" m" + 
				" join bank.\"MemberAcct\" ma on m.memb_id = ma.memb_id" + 
				" join bank.\"Account\" a on ma.acct_num = a.acct_num" + 
				" where m.memb_id = ?;";
		
		List<Account> accounts = new ArrayList<>();
		
		// makes it autocloseable
		try (	Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				) {
			/*
			for(int i =0;i<rs.getMetaData().getColumnCount();i++) {
				System.out.println(rs.getMetaData().getColumnName(i+1));
			}*/
			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				int accountNum = rs.getInt("acct_num");
				float currentBalance = rs.getFloat("cur_bal");
				
//				System.out.println(deptId+deptName+salary);
				a = new Account(accountNum, currentBalance);
				//System.out.println(m);
				accounts.add(a);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public Account getAccountByAcctNum(int acctNum) {
		String sql = "select * from bank.\"Account\" where acct_num = ?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			ps.setInt(1, acctNum);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				int accountNum = rs.getInt("acct_num");
				float currentBalance = rs.getFloat("cur_bal");
				
//				System.out.println(deptId+deptName+salary);
				a = new Account(accountNum, currentBalance);
		
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		return a;
	}

	@Override
	public int createAccount(Account a, int memberId) {
		int accountsCreated = 0;
		int accountNum = -1;
		//String sql = "insert into department (dept_name, monthly_budget) values (?, ?)";
		//bank.createNewMember ( usr_nm, first_nm, last_nm, pswd, email	)
		String sql = "select bank.createNewAccount (?)";
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			/*
			 *  private int memberId;
				private String username;
				private String firstname;
				private String lastname;
				private String email;
				private Timestamp lastLogin;
			 */
			
			ps.setInt(1, memberId);
			
			// returns the row count or zero if none created
			//membersCreated = ps.executeUpdate();
			ResultSet rs = ps.executeQuery();
			
			// this should be rs.next(); not a while loop !!!!!!!!!!!!!!
			while (rs.next()) {
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				accountNum = (int) rs.getObject(1); 
						
			}
			
			//if (memberId != 0) {
				// need to test this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				/*
				MemberDao md = new MemberDaoImpl();
				String usrnm = m.getUsername();
				Member thism = md.getMemberByUsername(usrnm);
				int id = thism.getMemberId();
				m.setMemberId(id);
				
				Member am = a.getMember();
				am.setMemberId(id);
				*/
			
				//set memb id for m and a ***********************************
			if (memberId != -1) {
				a.setAccountNum(accountNum);
				
				//(a.getMember()).setMemberId(memberId); // this will throw a null ptr exc until auth dao impl class is built
				accountsCreated++;
			}
			
			//}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accountsCreated;
	}
	
	@Override
	public void addJointAccount(int memberId, Account a) {
		int accountsCreated = 0;
		int accountNum = -1;
		//String sql = "insert into department (dept_name, monthly_budget) values (?, ?)";
		//bank.createNewMember ( usr_nm, first_nm, last_nm, pswd, email	)
		String sql = "select bank.addMemberAcctConnection (?, ?)";
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			/*
			 *  private int memberId;
				private String username;
				private String firstname;
				private String lastname;
				private String email;
				private Timestamp lastLogin;
			 */
			
			ps.setInt(1, memberId);
			ps.setInt(2, a.getAccountNum());
			
			// returns the row count or zero if none created
			ResultSet rs = ps.executeQuery();
			
			
			//if (memberId != 0) {
				// need to test this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				/*
				MemberDao md = new MemberDaoImpl();
				String usrnm = m.getUsername();
				Member thism = md.getMemberByUsername(usrnm);
				int id = thism.getMemberId();
				m.setMemberId(id);
				
				Member am = a.getMember();
				am.setMemberId(id);
				*/
			
				//set memb id for m and a ***********************************
			
			
			//}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public float depositCash(Account a, float amt) {
		int accountsCreated = 0;
		int accountNum = -1;
		//String sql = "insert into department (dept_name, monthly_budget) values (?, ?)";
		//bank.createNewMember ( usr_nm, first_nm, last_nm, pswd, email	)
		String sql = "select bank.depositCash (?, ?)";
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			/*
			 *  private int memberId;
				private String username;
				private String firstname;
				private String lastname;
				private String email;
				private Timestamp lastLogin;
			 */
			
			ps.setInt(1, a.getAccountNum());
			ps.setFloat(2, amt);
			
			// returns the row count or zero if none created
			ResultSet rs = ps.executeQuery();
			
			
			//if (memberId != 0) {
				// need to test this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				/*
				MemberDao md = new MemberDaoImpl();
				String usrnm = m.getUsername();
				Member thism = md.getMemberByUsername(usrnm);
				int id = thism.getMemberId();
				m.setMemberId(id);
				
				Member am = a.getMember();
				am.setMemberId(id);
				*/
			
				//set memb id for m and a ***********************************
			
			
			//}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public float withdrawCash(Account a, float amt) {
		int accountsCreated = 0;
		int accountNum = -1;
		//String sql = "insert into department (dept_name, monthly_budget) values (?, ?)";
		//bank.createNewMember ( usr_nm, first_nm, last_nm, pswd, email	)
		String sql = "select bank.withdrawCash (?, ?)";
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			/*
			 *  private int memberId;
				private String username;
				private String firstname;
				private String lastname;
				private String email;
				private Timestamp lastLogin;
			 */
			
			ps.setInt(1, a.getAccountNum());
			ps.setFloat(2, amt);
			
			// returns the row count or zero if none created
			ResultSet rs = ps.executeQuery();
			
			
			//if (memberId != 0) {
				// need to test this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				/*
				MemberDao md = new MemberDaoImpl();
				String usrnm = m.getUsername();
				Member thism = md.getMemberByUsername(usrnm);
				int id = thism.getMemberId();
				m.setMemberId(id);
				
				Member am = a.getMember();
				am.setMemberId(id);
				*/
			
				//set memb id for m and a ***********************************
			
			
			//}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public String transferFunds(Account send, Account rec, float amt) {
		int accountsCreated = 0;
		int accountNum = -1;
		//String sql = "insert into department (dept_name, monthly_budget) values (?, ?)";
		//bank.createNewMember ( usr_nm, first_nm, last_nm, pswd, email	)
		String sql = "select bank.transferFunds (?, ?, ?)";
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			/*
			 *  private int memberId;
				private String username;
				private String firstname;
				private String lastname;
				private String email;
				private Timestamp lastLogin;
			 */
			
			ps.setInt(1, send.getAccountNum());
			ps.setInt(2, rec.getAccountNum());
			ps.setFloat(3, amt);
			
			// returns the row count or zero if none created
			ResultSet rs = ps.executeQuery();
			
			// i could get desc string but dont have time 
			
			//if (memberId != 0) {
				// need to test this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				/*
				MemberDao md = new MemberDaoImpl();
				String usrnm = m.getUsername();
				Member thism = md.getMemberByUsername(usrnm);
				int id = thism.getMemberId();
				m.setMemberId(id);
				
				Member am = a.getMember();
				am.setMemberId(id);
				*/
			
				//set memb id for m and a ***********************************
			
			
			//}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "Success.";
	}

/*
	@Override
	public int updateAccount(Account a) {
		int accountsUpdated = 0;
		// calling this is gunna break my transaction history
		// its also going to break memberAcct table
		// both tables would need to be updated if acct_num changes
		// otherwise if current bal only changes it needs to inform transaction table to adjust
		String sql = "update bank.\"Account\" set acct_num = ?, cur_bal = ? where acct_num = 34";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			/*
			 *  private int memberId;
				private String username;
				private String firstname;
				private String lastname;
				private String email;
				private Timestamp lastLogin;
			 
			
			ps.setInt(1, a.getAccountNum());
			ps.setFloat(2, a.getCurrentBalance());
			
			
			accountsUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountsUpdated;
	}
	*/

	
	


}
