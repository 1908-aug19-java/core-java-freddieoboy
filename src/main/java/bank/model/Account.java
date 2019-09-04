package bank.model;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int accountNum;
	private float currentBalance;
	
	
	
	public Account() {
		super();
		this.currentBalance = 0;
		// TODO Auto-generated constructor stub
	}
	public Account(int accountNum, float currentBalance) {
		super();
		this.accountNum = accountNum;
		this.currentBalance = currentBalance;
	}
	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", currentBalance=" + currentBalance + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNum;
		result = prime * result + Float.floatToIntBits(currentBalance);
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
		Account other = (Account) obj;
		if (accountNum != other.accountNum)
			return false;
		if (Float.floatToIntBits(currentBalance) != Float.floatToIntBits(other.currentBalance))
			return false;
		return true;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public float getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	

}
