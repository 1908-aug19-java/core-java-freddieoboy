package reim.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import reim.model.Authorization;
import reim.model.Employee;
import reim.model.dao.AuthorizationDao;
import reim.model.dao.EmployeeDao;
import reim.model.util.ConnectionUtil;

public class AuthorizationDaoImpl implements AuthorizationDao {

	EmployeeDao em = new EmployeeDaoImpl();
	Employee Employee = null;
	Authorization au = null;
	
	@Override
	public List<Authorization> getAuthorization() {
		String sql = "select * from reim.\"Authorization\"";
		
		
		
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
				int EmployeeId = rs.getInt("empl_id");
				String password = rs.getString("password");
				Employee = em.getEmployeeById(EmployeeId);
//				System.out.println(deptId+deptName+salary);
				au = new Authorization(Employee, password);
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
		String sql = "select * from reim.\"Authorization\" where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			// this should not be a while this only returns 1 record
			while (rs.next()) {
				
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				int EmployeeId = rs.getInt("empl_id");
				String password = rs.getString("password");
				
				Employee = em.getEmployeeById(EmployeeId);
				au = new Authorization(Employee, password);
		
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
		
		String sql = "update reim.\"Authorization\" set password = ? where empl_id = ?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			/*
			 *  private int EmployeeId;
				private String username;
				private String firstname;
				private String lastname;
				private String email;
				private Timestamp lastLogin;
			 */
			
			ps.setString(1, au.getPassword());
			ps.setInt(2, (au.getEmployee()).getEmplId());
			
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
