package bank.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bank.model.Authorization;
import bank.model.Member;
import bank.model.dao.AuthorizationDao;
import bank.model.dao.MemberDao;
import bank.model.util.ConnectionUtil;

public class AuthorizationDaoImpl implements AuthorizationDao {

	MemberDao md = new MemberDaoImpl();
	Member member = null;
	Authorization au = null;
	
	@Override
	public List<Authorization> getAuthorization() {
		String sql = "select * from bank.\"Authorization\"";
		
		
		
		List<Authorization> authorizations = new ArrayList<>();
		
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
				String password = rs.getString("password");
				member = md.getMemberById(memberId);
//				System.out.println(deptId+deptName+salary);
				au = new Authorization(member, password);
				//System.out.println(m);
				authorizations.add(au);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return authorizations;
	}

	@Override
	public Authorization getAuthorizationById(int id) {
		String sql = "select * from bank.\"Authorization\" where memb_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			// this should not be a while this only returns 1 record
			while (rs.next()) {
				
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				int memberId = rs.getInt("memb_id");
				String password = rs.getString("password");
				
				member = md.getMemberById(memberId);
				au = new Authorization(member, password);
		
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		return au;
	}

	@Override
	public int updateAuthorization(Authorization au) {
		int authorizationsUpdated = 0;
		
		String sql = "update bank.\"Authorization\" set password = ? where memb_id = ?";
		
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
			
			ps.setString(1, au.getPassword());
			ps.setInt(2, (au.getMember()).getMemberId());
			
			authorizationsUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authorizationsUpdated;
	}
/*
	@Override
	public Authorization createAuthorization(String password) {
		au.setPassword(password);
		
		return au;
	}
*/
	

}
