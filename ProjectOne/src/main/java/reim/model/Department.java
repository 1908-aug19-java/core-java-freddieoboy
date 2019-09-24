package reim.model;

import java.io.Serializable;

public class Department implements Serializable {
	
	/*
dept_id serial PRIMARY KEY,
dept_name varchar(30) unique not null,
dept_manager_empl_id int not null
	 */

	private static final long serialVersionUID = 1L;
	
	private int deptId;
	private String deptName;
	private int deptManagerEmplId;
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Department(int deptId) {
		this.deptId = deptId;
	}
	public Department(int deptId, String deptName, int deptManagerEmplId) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptManagerEmplId = deptManagerEmplId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deptId;
		result = prime * result + deptManagerEmplId;
		result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
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
		Department other = (Department) obj;
		if (deptId != other.deptId)
			return false;
		if (deptManagerEmplId != other.deptManagerEmplId)
			return false;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", deptManagerEmplId=" + deptManagerEmplId
				+ "]";
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getDeptManagerEmplId() {
		return deptManagerEmplId;
	}

	public void setDeptManagerEmplId(int deptManagerEmplId) {
		this.deptManagerEmplId = deptManagerEmplId;
	}
	
	

}
