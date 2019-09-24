package reim.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
empl_id serial PRIMARY KEY,
user_name varchar(20) UNIQUE NOT NULL,
email varchar(50) UNIQUE NOT NULL,
first_name varchar(30) NOT NULL,
last_name varchar(30) NOT NULL,
dept_id int not null,
job_title varchar(30) not null,
is_employed boolean not null,
hire_dt TIMESTAMP, 
last_login TIMESTAMP
	 */
	private int emplId;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	// private int dept_id;
	private Department department;
	private String jobTitle;
	private boolean isEmployed;
	private Timestamp hireDate;
	private Timestamp lastLogin;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int emplId) {
		super();
		this.emplId = emplId;
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String username, String firstname, String lastname, String email,
			Department department, String jobTitle, Timestamp lastLogin) {
		super();
		
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.department = department;
		this.jobTitle = jobTitle;
		this.isEmployed = true;
		//this.hireDate = hireDate; = time and date added done in sql
		this.lastLogin = lastLogin;
	}
	
	public Employee(int emplId, String username, String firstname, String lastname, String email,
			Department department, String jobTitle, Timestamp lastLogin) {
		super();
		this.emplId = emplId;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.department = department;
		this.jobTitle = jobTitle;
		this.isEmployed = true;
		//this.hireDate = hireDate; = time and date added done in sql
		this.lastLogin = lastLogin;
	}
						
	public Employee(int empl_id, String username, String firstname, String lastname, String email,
			Department department, String jobTitle, boolean isEmployed, Timestamp hireDate, Timestamp lastLogin) {
		super();
		this.emplId = empl_id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.department = department;
		this.jobTitle = jobTitle;
		this.isEmployed = isEmployed;
		this.hireDate = hireDate;
		this.lastLogin = lastLogin;
	}

	public Employee(String username, String firstname, String lastname, String email, Department department,
			String jobTitle, boolean isEmployed, Timestamp hireDate, Timestamp lastLogin) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.department = department;
		this.jobTitle = jobTitle;
		this.isEmployed = isEmployed;
		this.hireDate = hireDate;
		this.lastLogin = lastLogin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + emplId;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result + (isEmployed ? 1231 : 1237);
		result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + ((lastLogin == null) ? 0 : lastLogin.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emplId != other.emplId)
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (isEmployed != other.isEmployed)
			return false;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (lastLogin == null) {
			if (other.lastLogin != null)
				return false;
		} else if (!lastLogin.equals(other.lastLogin))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [empl_id=" + emplId + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", department=" + department + ", jobTitle=" + jobTitle
				+ ", isEmployed=" + isEmployed + ", hireDate=" + hireDate + ", lastLogin=" + lastLogin + "]";
	}

	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int empl_id) {
		this.emplId = empl_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public boolean isEmployed() {
		return isEmployed;
	}

	public void setEmployed(boolean isEmployed) {
		this.isEmployed = isEmployed;
	}

	public Timestamp getHireDate() {
		return hireDate;
	}

	public void setHireDate(Timestamp hireDate) {
		this.hireDate = hireDate;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	
}
