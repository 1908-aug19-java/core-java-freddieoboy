package reim.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import reim.model.Department;
import reim.model.dao.DepartmentDao;
import reim.model.util.ConnectionUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	Department d = null;
	int deptId = -1;

	@Override
	public List<Department> getDepartment() {
		String sql = "select * from reim.\"Department\"";

		List<Department> departments = new ArrayList<>();

		// makes it autocloseable
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			/*
			 * for(int i =0;i<rs.getMetaData().getColumnCount();i++) {
			 * System.out.println(rs.getMetaData().getColumnName(i+1)); }
			 */

			while (rs.next()) {

				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				int deptId = rs.getInt("dept_id");
				String deptName = rs.getString("dept_name");
				int deptManagerEmplId = rs.getInt("dept_manager_empl_id");
//				System.out.println(deptId+deptName+salary);
				d = new Department(deptId, deptName, deptManagerEmplId);
				// System.out.println(m);
				departments.add(d);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {
		String sql = "select * from reim.\"Department\" where dept_id = ?";

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			// handle the first parameterization
			// jdbc 1 based index
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			// this should not be a while this only returns 1 record
			while (rs.next()) {

				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				int deptManagerEmplId = rs.getInt("dept_manager_empl_id");
				String deptName = rs.getString("dept_name");

				d = new Department(id, deptName, deptManagerEmplId);

			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return d;
	}

	@Override
	public int createDepartment(Department d) {
		String sql = "select reim.createNewDepartment (?,?)";
		int departmentsCreated = 0;
		
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, d.getDeptName());
			ps.setInt(2, d.getDeptManagerEmplId());

			// returns the row count or zero if none created
			// employeesCreated = ps.executeUpdate();
			ResultSet rs = ps.executeQuery();

			// this should be rs.next(); not a while loop !!!!!!!!!!!!!!
			while (rs.next()) {
				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				deptId = (int) rs.getObject(1);

			}
			if (deptId != -1) {
				d.setDeptId(deptId);
				
				//(a.getEmployee()).setEmplId(emplId); // this will throw a null ptr exc until auth dao impl class is built
				departmentsCreated++;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return departmentsCreated;
	}

	@Override
	public int updateDepartment(Department d) {
		int employeesUpdated = -1;

		String sql = "update reim.\"Department\" dept_name = ?, dept_manager_empl_id = ? where dept_id = ?";


		// sql += " where empl_id = ?";

		// System.out.println(sql);

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			// handle the first parameterization
			// jdbc 1 based index
			/*
			 * private int emplId; private String username; private String firstname;
			 * private String lastname; private String email; private Timestamp lastLogin;
			 */

			ps.setString(1, d.getDeptName());
			ps.setInt(2, d.getDeptManagerEmplId());
			ps.setInt(3, d.getDeptId());

			employeesUpdated = ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return employeesUpdated;
	}

	@Override
	public int deleteDepartment(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
