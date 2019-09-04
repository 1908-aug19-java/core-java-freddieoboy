package bank.model;

import java.io.Serializable;

public class Authorization implements Serializable {

	private static final long serialVersionUID = 1L;

	//private int memberId;
	private Member member;
	private String password;
	
	public Authorization() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Authorization(Member member, String password) {
		super();
		this.member = member;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Authorization [member=" + member + ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member == null) ? 0 : member.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Authorization other = (Authorization) obj;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
