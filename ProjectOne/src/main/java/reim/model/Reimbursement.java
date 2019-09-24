package reim.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reimbursement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
reim_id serial PRIMARY KEY,
empl_id int not null,
submit_dt TIMESTAMP not null,
dt TIMESTAMP not null,
expense_type varchar(30) NOT NULL,
"desc" varchar(200) NOT NULL,
status varchar(30) not null, 
amt numeric(8,2) NOT null,
updated_dt timestamp not null,
updated_by_empl_id int not null
	 */

	private int reimId;
	//private int emplId;
	private Employee employee;
	private Timestamp submitDate;
	private Timestamp date;
	private String expenseType;
	private String desc;
	private String status;
	private double amt;
	private Timestamp updatedDate;
	private int updatedByEmplId;
	//
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(Employee employee, Timestamp submitDate, String expenseType,
			String desc, String status, double amt) {
		super();
		
		this.employee = employee;
		this.submitDate = submitDate;
		
		this.expenseType = expenseType;
		this.desc = desc;
		this.status = status;
		this.amt = amt;
		
	}
	
	public Reimbursement(int reimId, Employee employee, Timestamp submitDate, Timestamp date, String expenseType,
			String desc, String status, double amt, Timestamp updatedDate, int updatedByEmplId) {
		super();
		this.reimId = reimId;
		this.employee = employee;
		this.submitDate = submitDate;
		this.date = date;
		this.expenseType = expenseType;
		this.desc = desc;
		this.status = status;
		this.amt = amt;
		this.updatedDate = updatedDate;
		this.updatedByEmplId = updatedByEmplId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((expenseType == null) ? 0 : expenseType.hashCode());
		result = prime * result + reimId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitDate == null) ? 0 : submitDate.hashCode());
		result = prime * result + updatedByEmplId;
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amt) != Double.doubleToLongBits(other.amt))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (expenseType == null) {
			if (other.expenseType != null)
				return false;
		} else if (!expenseType.equals(other.expenseType))
			return false;
		if (reimId != other.reimId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submitDate == null) {
			if (other.submitDate != null)
				return false;
		} else if (!submitDate.equals(other.submitDate))
			return false;
		if (updatedByEmplId != other.updatedByEmplId)
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimId=" + reimId + ", employee=" + employee + ", submitDate=" + submitDate + ", date="
				+ date + ", expenseType=" + expenseType + ", desc=" + desc + ", status=" + status + ", amt=" + amt
				+ ", updatedDate=" + updatedDate + ", updatedByEmplId=" + updatedByEmplId + "]";
	}

	public int getReimId() {
		return reimId;
	}

	public void setReimId(int reimId) {
		this.reimId = reimId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Timestamp getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Timestamp submitDate) {
		this.submitDate = submitDate;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUpdatedByEmplId() {
		return updatedByEmplId;
	}

	public void setUpdatedByEmplId(int updatedByEmplId) {
		this.updatedByEmplId = updatedByEmplId;
	}
	
	
	
	
}
