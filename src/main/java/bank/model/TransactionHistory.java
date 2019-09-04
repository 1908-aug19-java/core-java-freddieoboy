package bank.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TransactionHistory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int transactionId;
	private Timestamp transactionDate;
	private String transactionDesc;
	// private int accountNum;
	private Account account;
	private float amount;
	private float balance;
	
	
	
	public TransactionHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TransactionHistory(int transactionId, Timestamp transactionDate, String transactionDesc,
			Account account, float amount, float balance) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionDesc = transactionDesc;
		this.account = account;
		this.amount = amount;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "TransactionHistory [transactionId=" + transactionId + ", transactionDate=" + transactionDate
				+ ", transactionDesc=" + transactionDesc + ", account=" + account + ", amount=" + amount + ", balance="
				+ balance + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((transactionDesc == null) ? 0 : transactionDesc.hashCode());
		result = prime * result + transactionId;
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
		TransactionHistory other = (TransactionHistory) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionDesc == null) {
			if (other.transactionDesc != null)
				return false;
		} else if (!transactionDesc.equals(other.transactionDesc))
			return false;
		if (transactionId != other.transactionId)
			return false;
		return true;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public Timestamp getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionDesc() {
		return transactionDesc;
	}
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	

}
