package bank;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;

import bank.model.util.ConnectionUtil;



public class Driver {

	public static void main(String[] args) {

		try {
			//Connection c = ConnectionUtil.getHardCodedConnection();
			Connection c = ConnectionUtil.getConnection();
			String driver = c.getMetaData().getDriverName();
			System.out.println(driver + "\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Bank b = new Bank();
		b.start();
		
	}

}


// test driver code
/*
MemberDao md = new MemberDaoImpl();
List<Member> members = md.getMember();

System.out.println("\nList<Member> = getMember()");
System.out.println("----------------------------------------------------------------------------------------------");
for (Member m : members) {
	System.out.println(m);
}

// may need to make a login function in sql
System.out.println("\ngetMemberById()");
System.out.println("--------------------------------------------------------------------------");
System.out.println(md.getMemberById(27));
//System.out.println(md.getMemberById(50));
System.out.println(md.getMemberById(28));

System.out.println(md.getMemberByUsername("howardlt"));
System.out.println(md.getMemberByEmail("randomtest7@gmail.com"));

/*
System.out.println("\ncreateNewMember()");
System.out.println("--------------------------------------------------------------------------");
//!!!!!!! notice java will not have howards membId stores so get id will be 0 by default this is got to be bad practice
 // maybe in createMember we check to see if m and au have membId if not we query for it?
  // nvm just implemtned set m and au membId in createNewMember 
  
Member m1 = new Member("howardlt", "Howard", "Tran", "howardlt@gmail.com");
System.out.println(m1.getMemberId());
Authorization a = new Authorization();
a.setPassword("howie");
m1.toString();
a.toString();
int membs = md.createMember(m1, a);
System.out.println("Members created: " + membs);
System.out.println("----------------------------------------------------------------------------------------------");
for (Member m : members) {
	System.out.println(m);
}
*/
/*

Member m1 = new Member("randomTest15", "Random15", "Test15", "randomtest15@gmail.com");
//System.out.println(m1.getMemberId());
Authorization a = new Authorization();
a.setPassword("test");

int membs = md.createMember(m1, a);
System.out.println("Members created: " + membs);
System.out.println("----------------------------------------------------------------------------------------------");
members = md.getMember();
for (Member m : members) {
	System.out.println(m);
}
*/

/*
Member m1 = md.getMemberByUsername("howie");
m1.setUsername("howardlt");
md.updateMember(m1);

members = md.getMember();
for (Member m : members) {
	System.out.println(m);
}
*/
// auth testing
/*
AuthorizationDao aud = new AuthorizationDaoImpl();
/*
List<Authorization> authorizations = ad.getAuthorization();

System.out.println("\nList<Authorization> = getAuthorization()");
System.out.println("----------------------------------------------------------------------------------------------");
for (Authorization au : authorizations) {
	System.out.println(au);
}
*/
/*
System.out.println("\ngetAuthorizationById()");
System.out.println("--------------------------------------------------------------------------");
System.out.println(aud.getAuthorizationById(27));

Authorization au1 = aud.getAuthorizationById(27);
au1.setPassword("pswd");
aud.updateAuthorization(au1);
*/
/*
List<Authorization> authorizations = aud.getAuthorization();

System.out.println("\nList<Authorization> = getAuthorization()");
System.out.println("----------------------------------------------------------------------------------------------");
for (Authorization au : authorizations) {
	System.out.println(au);
}
*/

// acct testing
/*
AccountDao ad = new AccountDaoImpl();
/*
List<Account> accounts = ad.getAccount();

System.out.println("\nList<Account> = getAccount()");
System.out.println("----------------------------------------------------------------------------------------------");
for (Account a : accounts) {
	System.out.println(a);
}
*/
/*
System.out.println("\ngetAccountById()");
System.out.println("--------------------------------------------------------------------------");
System.out.println(ad.getAccountById(30));
*/
/*
Member m1 = new Member("randomTest24", "Random24", "Test24", "randomtest24@gmail.com");
int membs = md.createMember(m1, "myPswd");
Account acct = new Account();
int accts = ad.createAccount(acct,  m1.getMemberId());

//ad.updateAccount(acct);

System.out.println("\nList<Account> = getAccount()");
System.out.println("----------------------------------------------------------------------------------------------");
List<Account> accounts = ad.getAccount();
for (Account a : accounts) {
	System.out.println(a);
}
*/
/*
MemberAcctDao mad = new MemberAcctDaoImpl();
List<MemberAcct> memberAccts = mad.getMemberAcct();

System.out.println("\nList<MemberAcct> = getMemberAcct()");
System.out.println("----------------------------------------------------------------------------------------------");
for (MemberAcct ma : memberAccts) {
	System.out.println(ma);
}

System.out.println("\ngetMemberAcctById()");
System.out.println("--------------------------------------------------------------------------");
List<MemberAcct> memberAcct = mad.getMemberAcctById(27);
for (MemberAcct ma : memberAcct) {
	System.out.println(ma);
}
// tran
TransactionHistoryDao thd = new TransactionHistoryDaoImpl();

List<TransactionHistory> transactions = thd.getTransactionHistory();

//NOTICE 999999.99 is rounding to 1 million for some reason !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
System.out.println("\nList<TransactionHistory> = getTransactionHistory()");
System.out.println("----------------------------------------------------------------------------------------------");
for (TransactionHistory t : transactions) {
	System.out.println(t);
}

System.out.println("\ngetTransactionHistoryByAcctNum()");
System.out.println("--------------------------------------------------------------------------");
List<TransactionHistory> transaction2s = thd.getTransactionHistoryByAccountNumber(30);
for (TransactionHistory t : transaction2s) {
	System.out.println(t);
}
*/