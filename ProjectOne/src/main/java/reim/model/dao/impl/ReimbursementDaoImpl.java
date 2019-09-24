package reim.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import reim.model.Department;
import reim.model.Employee;
import reim.model.Reimbursement;
import reim.model.dao.EmployeeDao;
import reim.model.dao.ReimbursementDao;
import reim.model.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {

	Reimbursement r = null;
	EmployeeDao ed = new EmployeeDaoImpl();
	
	@Override
	public List<Reimbursement> getReimbursement() {
		String sql = "select * from reim.\"Reimbursement\"";

		List<Reimbursement> reimbursements = new ArrayList<>();

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
				
				//int reimId, Employee employee, Timestamp submitDate, Timestamp date, String expenseType,String desc, String status, double amt, Timestamp updatedDate, int updatedByEmplId
				/*
reim_id serial PRIMARY KEY,
empl_id int not null,
submit_dt TIMESTAMP not null,
dt TIMESTAMP not null,
expense_type varchar(30) NOT NULL,
"desc" varchar(200) NOT NULL,
status varchar(30) not null, 
amt numeric(8,2) NOT null,
updated_dt timestamp,
updated_by_empl_id int
				 */
				int reimId = rs.getInt("reim_id");
				int emplId = rs.getInt("empl_id");
				Employee employee = ed.getEmployeeById(emplId);
				Timestamp submitDate = rs.getTimestamp("submit_dt");
				Timestamp dateOfReceipt = rs.getTimestamp("dt");
				String expenseType = rs.getString("expense_type");
				String desc = rs.getString("desc");
				String status = rs.getString("status");
				
				double amount = rs.getDouble("amt");
				Timestamp updatedDate = rs.getTimestamp("updated_dt");
				int updatedByEmplId = rs.getInt("updated_by_empl_id");
				

//				System.out.println(deptId+deptName+salary);
				// Employee e = new Employee(emplId, username, firstname, lastname, email,
				// lastLogin);
				r = new Reimbursement(reimId, employee, submitDate, dateOfReceipt, expenseType, desc, status, amount, updatedDate, updatedByEmplId);
				// System.out.println(e);
				reimbursements.add(r);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reimbursements;
	}

	@Override
	public List<Reimbursement> getReimbursementsByDepartment(Department d) {
		String sql = "select * from reim.\"Reimbursement\"" + 
				" where empl_id in (select empl_id from reim.\"Employee\"" + 
				" where dept_id = ?) order by status desc";
		List<Reimbursement> reimbursements = new ArrayList<>();
		

		// makes it autocloseable
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
		
				ps.setInt(1, d.getDeptId());
				ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				int reimId = rs.getInt("reim_id");
				int emplId = rs.getInt("empl_id");
				
				System.out.println("get reim by dept emplID: " + emplId);
				
				Employee employee = ed.getEmployeeById(emplId);
				
				Timestamp submitDate = rs.getTimestamp("submit_dt");
				Timestamp dateOfReceipt = rs.getTimestamp("dt");
				String expenseType = rs.getString("expense_type");
				String desc = rs.getString("desc");
				String status = rs.getString("status");
				
				double amount = rs.getDouble("amt");
				Timestamp updatedDate = rs.getTimestamp("updated_dt");
				int updatedByEmplId = rs.getInt("updated_by_empl_id");
				

//				System.out.println(deptId+deptName+salary);
				// Employee e = new Employee(emplId, username, firstname, lastname, email,
				// lastLogin);
				r = new Reimbursement(reimId, employee, submitDate, dateOfReceipt, expenseType, desc, status, amount, updatedDate, updatedByEmplId);
				// System.out.println(e);
				reimbursements.add(r);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reimbursements;
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmployee(Employee e) {
		String sql = "select * from reim.\"Reimbursement\" where empl_id = ? order by status desc";
		List<Reimbursement> reimbursements = new ArrayList<>();
		

		// makes it autocloseable
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
		
				ps.setInt(1, e.getEmplId());
				ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				int reimId = rs.getInt("reim_id");
				int emplId = rs.getInt("empl_id");
				Employee employee = ed.getEmployeeById(emplId);
				Timestamp submitDate = rs.getTimestamp("submit_dt");
				Timestamp dateOfReceipt = rs.getTimestamp("dt");
				String expenseType = rs.getString("expense_type");
				String desc = rs.getString("desc");
				String status = rs.getString("status");
				
				double amount = rs.getDouble("amt");
				Timestamp updatedDate = rs.getTimestamp("updated_dt");
				int updatedByEmplId = rs.getInt("updated_by_empl_id");
				

//				System.out.println(deptId+deptName+salary);
				// Employee e = new Employee(emplId, username, firstname, lastname, email,
				// lastLogin);
				r = new Reimbursement(reimId, employee, submitDate, dateOfReceipt, expenseType, desc, status, amount, updatedDate, updatedByEmplId);
				// System.out.println(e);
				reimbursements.add(r);

			}

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		return reimbursements;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement getReimbursementByEmplId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement getReimbursementByManagerEmplId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement getReimbursementByStatus(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement getReimbursementByExpenseType(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement getReimbursementInBetweenDates(Timestamp a, Timestamp b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		return 0;
	}

}
