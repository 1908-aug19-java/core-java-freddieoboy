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
import reim.model.dao.DepartmentDao;
import reim.model.dao.EmployeeDao;
import reim.model.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	Employee e = null;
	DepartmentDao dd = new DepartmentDaoImpl();


	@Override
	public List<Employee> getEmployees() {
		String sql = "select * from reim.\"Employee\"";

		List<Employee> employees = new ArrayList<>();

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
				int emplId = rs.getInt("empl_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				int deptId = rs.getInt("dept_id");
				Department department = dd.getDepartmentById(deptId);
				String jobTitle = rs.getString("job_title");
				boolean isEmployed = rs.getBoolean("is_employed");
				Timestamp hireDate = rs.getTimestamp("hire_dt");
				// timestamp lastLogin = rs.getTimestamp(lastLogin);
				Timestamp lastLogin = rs.getTimestamp("last_login");

//				System.out.println(deptId+deptName+salary);
				// Employee e = new Employee(emplId, username, firstname, lastname, email,
				// lastLogin);
				e = new Employee(emplId, username, firstname, lastname, email, department, jobTitle, isEmployed,
						hireDate, lastLogin);
				// System.out.println(e);
				employees.add(e);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}
	
	@Override
	public List<Employee> getEmployeesByDepartment(Department d) {
		String sql = "select * from reim.\"Employee\" where dept_id = ?";

		List<Employee> employees = new ArrayList<>();

		// makes it autocloseable
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
		
				ps.setInt(1, d.getDeptId());
				ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				int emplId = rs.getInt("empl_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				int deptId = rs.getInt("dept_id");
				Department department = dd.getDepartmentById(deptId);
				String jobTitle = rs.getString("job_title");
				boolean isEmployed = rs.getBoolean("is_employed");
				Timestamp hireDate = rs.getTimestamp("hire_dt");
				// timestamp lastLogin = rs.getTimestamp(lastLogin);
				Timestamp lastLogin = rs.getTimestamp("last_login");

//				System.out.println(deptId+deptName+salary);
				// Employee e = new Employee(emplId, username, firstname, lastname, email,
				// lastLogin);
				e = new Employee(emplId, username, firstname, lastname, email, department, jobTitle, isEmployed,
						hireDate, lastLogin);
				// System.out.println(e);
				employees.add(e);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}

	
	@Override
	public Employee getEmployeeById(int id) {
		String sql = "select * from reim.\"Employee\" where empl_id = ?";
		e = null;

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			// handle the first parameterization
			// jdbc 1 based index
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				int emplId = rs.getInt("empl_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				int deptId = rs.getInt("dept_id");
				Department department = dd.getDepartmentById(deptId);
				String jobTitle = rs.getString("job_title");
				boolean isEmployed = rs.getBoolean("is_employed");
				Timestamp hireDate = rs.getTimestamp("hire_dt");
				// timestamp lastLogin = rs.getTimestamp(lastLogin);
				Timestamp lastLogin = rs.getTimestamp("last_login");

//				System.out.println(deptId+deptName+salary);
				// Employee e = new Employee(emplId, username, firstname, lastname, email,
				// lastLogin);
				e = new Employee(emplId, username, firstname, lastname, email, department, jobTitle, isEmployed,
						hireDate, lastLogin);

			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return e;
	}

	@Override
	public Employee getEmployeeByUsername(String s) {
		String sql = "select * from reim.\"Employee\" where user_name = ?";

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			// handle the first parameterization
			// jdbc 1 based index
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				int emplId = rs.getInt("empl_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				int deptId = rs.getInt("dept_id");
				Department department = dd.getDepartmentById(deptId);
				String jobTitle = rs.getString("job_title");
				boolean isEmployed = rs.getBoolean("is_employed");
				Timestamp hireDate = rs.getTimestamp("hire_dt");
				Timestamp lastLogin = rs.getTimestamp("last_login");

				e = new Employee(emplId, username, firstname, lastname, email, department, jobTitle, isEmployed,
						hireDate, lastLogin);

			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return e;
	}

	@Override
	public Employee getEmployeeByUsernameAndPassword(String username, String password) {
		
		String sql = "select " +
				"e.empl_id, user_name, email, first_name, last_name, dept_id, job_title, is_employed, hire_dt, last_login " + 
				"from reim.\"Employee\" e " + 
				"join reim.\"Authorization\" au " + 
				"on e.empl_id = au.empl_id " + 
				"where e.user_name = ? and au.password = ?"; // ;
		
		try (Connection c = ConnectionUtil.getConnection(); 
				PreparedStatement ps = c.prepareStatement(sql)) {

			// handle the first parameterization
			// jdbc 1 based index
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				int emplId = rs.getInt("empl_id");
				
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				int deptId = rs.getInt("dept_id");
				Department department = dd.getDepartmentById(deptId);
				String jobTitle = rs.getString("job_title");
				boolean isEmployed = rs.getBoolean("is_employed");
				Timestamp hireDate = rs.getTimestamp("hire_dt");
				Timestamp lastLogin = rs.getTimestamp("last_login");

				e = new Employee(emplId, username, firstname, lastname, email, department, jobTitle, isEmployed,
						hireDate, lastLogin);

			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return e;
		
	}

	
	@Override
	public Employee getEmployeeByEmail(String s) {
		String sql = "select * from reim.\"Employee\" where email = ?";
		e = null;

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			// handle the first parameterization
			// jdbc 1 based index
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				int emplId = rs.getInt("empl_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				int deptId = rs.getInt("dept_id");
				Department department = dd.getDepartmentById(deptId);
				String jobTitle = rs.getString("job_title");
				boolean isEmployed = rs.getBoolean("is_employed");
				Timestamp hireDate = rs.getTimestamp("hire_dt");
				Timestamp lastLogin = rs.getTimestamp("last_login");

				e = new Employee(emplId, username, firstname, lastname, email, department, jobTitle, isEmployed,
						hireDate, lastLogin);

			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return e;
	}
	/*
	 * NEEDS TO RETURN RESULT SET SO THAT I CAN UPDATE EMPLID AND CURRENTTIMESTAMP THAT SQL USES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
	//reim.createnewemployee(usr_nm char, f_nm char, l_nm char, pswd char, eml char, dpt_id, job_t char)
	@Override
	public int createEmployee(Employee e, String password) {
		int employeesCreated = 0;
		int emplId = -1;
		//String sql = "insert into department (dept_name, monthly_budget) values (?, ?)";
		//reim.createNewEmployee ( usr_nm, first_nm, last_nm, pswd, email	)
		String sql = "select reim.createNewEmployee (?,?,?,?,?,?,?)";
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			/*
			 *  private int emplId;
				private String username;
				private String firstname;
				private String lastname;
				private String email;
				private Timestamp lastLogin;
			 */
			
			ps.setString(1, e.getUsername());
			ps.setString(2, e.getFirstname());
			ps.setString(3, e.getLastname());
			ps.setString(4, password);
			ps.setString(5, e.getEmail());
			ps.setInt(6, e.getDepartment().getDeptId());
			ps.setString(7, e.getJobTitle());
			// returns the row count or zero if none created
			//employeesCreated = ps.executeUpdate();
			ResultSet rs = ps.executeQuery();
			
			// this should be rs.next(); not a while loop !!!!!!!!!!!!!!
			while (rs.next()) {
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				emplId = (int) rs.getObject(1); 
						
			}
			
			//if (emplId != 0) {
				// need to test this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				/*
				EmployeeDao md = new EmployeeDaoImpl();
				String usrnm = e.getUsername();
				Employee thism = md.getEmployeeByUsername(usrnm);
				int id = thism.getEmplId();
				e.setEmplId(id);
				
				Employee am = a.getEmployee();
				am.setEmplId(id);
				*/
			
				//set memb id for e and a ***********************************
			if (emplId != -1) {
				e.setEmplId(emplId);
				
				//(a.getEmployee()).setEmplId(emplId); // this will throw a null ptr exc until auth dao impl class is built
				employeesCreated++;
			}
			
			//}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return employeesCreated;
	}
/*
 * 
 * 				int emplId = rs.getInt("empl_id");
				String username = rs.getString("user_name");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");
				int deptId = rs.getInt("dept_id");
				Department department = dd.getDepartmentById(deptId);
				String jobTitle = rs.getString("jobTitle");
				boolean isEmployed = rs.getBoolean("is_employed");
				Timestamp hireDate = rs.getTimestamp("hire_dt");
				// timestamp lastLogin = rs.getTimestamp(lastLogin);
				Timestamp lastLogin = rs.getTimestamp("last_login");

 * 
 */
	@Override
	public int updateEmployee(Employee e) {
		int employeesUpdated = 0;

		String sql = "update reim.\"Employee\" set user_name = ?, first_name = ?, last_name = ?,"+
														" email = ?, dept_id = ?, job_title = ?,"+
														"is_employed = ?, hire_dt = ?, last_login = ? where empl_id = ?";


		// sql += " where empl_id = ?";

		// System.out.println(sql);

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			// handle the first parameterization
			// jdbc 1 based index
			/*
			 * private int emplId; private String username; private String firstname;
			 * private String lastname; private String email; private Timestamp lastLogin;
			 */

			ps.setString(1, e.getUsername());
			ps.setString(2, e.getFirstname());
			ps.setString(3, e.getLastname());
			ps.setString(4, e.getEmail());
			ps.setInt(5, e.getDepartment().getDeptId());
			ps.setString(6, e.getJobTitle());
			ps.setBoolean(7, e.isEmployed());
			ps.setTimestamp(8, e.getHireDate());
			ps.setTimestamp(9, e.getLastLogin());
			ps.setInt(10, e.getEmplId());

			employeesUpdated = ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return employeesUpdated;
	}


	@Override
	public Timestamp getCurrentTime() {
		Timestamp t = null;
		String sql = "select current_timestamp;";

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			// handle the first parameterization
			// jdbc 1 based index
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// might be a good idea to have a static final list of these so you dont have to
				// change everywhere
				t = (Timestamp) rs.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;
	}

	@Override
	public int deleteEmployeeById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
