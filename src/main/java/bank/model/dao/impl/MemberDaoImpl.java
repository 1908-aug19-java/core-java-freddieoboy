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
import bank.model.dao.MemberDao;
import bank.model.util.ConnectionUtil;

public class MemberDaoImpl implements MemberDao {

	Member m = null;
	
	@Override
	public List<Member> getMember() {
		String sql = "select * from bank.\"Member\"";
		
		List<Member> members = new ArrayList<>();
		
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
				//timestamp lastLogin = rs.getTimestamp(lastLogin);
				Timestamp lastLogin = rs.getTimestamp("last_login");
				
//				System.out.println(deptId+deptName+salary);
				Member m = new Member(memberId, username, firstname, lastname, email, lastLogin);
				//System.out.println(m);
				members.add(m);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return members;
	}

	@Override
	public Member getMemberById(int id) {
		String sql = "select * from bank.\"Member\" where memb_id = ?";
		m = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				int memberId = rs.getInt("memb_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				Timestamp lastLogin = rs.getTimestamp("last_login");
				
				m = new Member(memberId, username, firstname, lastname, email, lastLogin);
		
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		return m;
	}

	@Override
	public Member getMemberByUsername(String s) {
		String sql = "select * from bank.\"Member\" where user_name = ?";
		m = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				int memberId = rs.getInt("memb_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				Timestamp lastLogin = rs.getTimestamp("last_login");
				
				m = new Member(memberId, username, firstname, lastname, email, lastLogin);
		
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		return m;
	}

	@Override
	public Member getMemberByEmail(String s) {
		String sql = "select * from bank.\"Member\" where email = ?";
		m = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				int memberId = rs.getInt("memb_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				Timestamp lastLogin = rs.getTimestamp("last_login");
				
				m = new Member(memberId, username, firstname, lastname, email, lastLogin);
		
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		return m;
	}

	@Override
	public int createMember(Member m, String password) {
		int membersCreated = 0;
		int memberId = -1;
		//String sql = "insert into department (dept_name, monthly_budget) values (?, ?)";
		//bank.createNewMember ( usr_nm, first_nm, last_nm, pswd, email	)
		String sql = "select bank.createNewMember (?,?,?,?,?)";
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
			
			ps.setString(1, m.getUsername());
			ps.setString(2, m.getFirstname());
			ps.setString(3, m.getLastname());
			ps.setString(4, password);
			ps.setString(5, m.getEmail());
			
			// returns the row count or zero if none created
			//membersCreated = ps.executeUpdate();
			ResultSet rs = ps.executeQuery();
			
			// this should be rs.next(); not a while loop !!!!!!!!!!!!!!
			while (rs.next()) {
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				memberId = (int) rs.getObject(1); 
						
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
				m.setMemberId(memberId);
				
				//(a.getMember()).setMemberId(memberId); // this will throw a null ptr exc until auth dao impl class is built
				membersCreated++;
			}
			
			//}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return membersCreated;
	}

	@Override
	public int updateMember(Member m) {
		int membersUpdated = 0;
		
		String sql = "update bank.\"Member\" set user_name = ?, first_name = ?, last_name = ?, email = ?, last_login = ? where memb_id = ?";
		
		//int num = 5;
		//ps.setTimestamp(5, );
		//Timestamp timestamp = null;
		//LocalDateTime ldt = null;
		/*
				try {
					System.out.println("cmon");
					String s = m.getLastLogin();
					System.out.println(s);
					//instant
					// timestamp.from
					//Timestamp t1 = 
					ldt = LocalDateTime.now();
					s = ldt.toString();
					System.out.println(s);
					//System.out.println("yes!");
					//Timestamp t = Timestamp.valueOf(s);
					//System.out.println("yes!");
				   // DateFormat dateFormat = new getDateTimeInstance();
				    System.out.println("1");
				    //Date parsedDate = (Date) dateFormat.parse(m.getLastLogin());
				    Date parsedDate = (Date) dateFormat.parse(s);
				    System.out.println(parsedDate);
				    timestamp = new java.sql.Timestamp(parsedDate.getTime());
				    System.out.println(timestamp);
				    
				    num++;
				    sql += ", last_login = ?";
				} catch(Exception e) { //this generic but you can control another types of exception
				    // look the origin of excption 
				}*/
		
		//sql += " where memb_id = ?";
		
		//System.out.println(sql);
		
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
			
			ps.setString(1, m.getUsername());
			ps.setString(2, m.getFirstname());
			ps.setString(3, m.getLastname());
			ps.setString(4, m.getEmail());
			
			ps.setTimestamp(5, m.getLastLogin());
			ps.setInt(6, m.getMemberId());
			
			membersUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersUpdated;
	}
	/*
	@Override
	public int updateMemberUsername(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMemberFirstname(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMemberLastname(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMemberEmail(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMemberLastLogin(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}
	*/

	@Override
	public Timestamp getCurrentTime() {
		Timestamp t = null;
		String sql = "select current_timestamp;";
		
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				t = (Timestamp) rs.getObject(1); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}
}
