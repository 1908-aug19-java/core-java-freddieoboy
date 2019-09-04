package bank.model;

import java.io.Serializable;

public class MemberAcct implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//private int memberId;
	private Member member;
	//private int accountNum;
	private Account account;

	public MemberAcct() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberAcct(Member member, Account account) {
		super();
		this.member = member;
		this.account = account;
	}

	@Override
	public String toString() {
		return "MemberAcct [member=" + member + ", account=" + account + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
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
		MemberAcct other = (MemberAcct) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		return true;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}
