package bank;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import bank.model.Account;
import bank.model.Authorization;
import bank.model.Member;
import bank.model.TransactionHistory;
import bank.model.dao.AccountDao;
import bank.model.dao.AuthorizationDao;
import bank.model.dao.MemberDao;
import bank.model.dao.TransactionHistoryDao;
import bank.model.dao.impl.AccountDaoImpl;
import bank.model.dao.impl.AuthorizationDaoImpl;
import bank.model.dao.impl.MemberDaoImpl;
import bank.model.dao.impl.TransactionHistoryDaoImpl;

public class Bank {

	private Scanner s = new Scanner(System.in);
	boolean credentialsNeeded = true;
	String username;
	Timestamp lastLogin;
	String lastLoginStr;
	List<Account> accounts;

	String input;

	MemberDao md = new MemberDaoImpl();
	AuthorizationDao aud = new AuthorizationDaoImpl();
	AccountDao ad = new AccountDaoImpl();
	TransactionHistoryDao thd = new TransactionHistoryDaoImpl();
	List<TransactionHistory> transactions;
	Account a;
	Authorization au;
	Member m;

	public void start() {

		while (credentialsNeeded) {
			showWelcomePage();
			while (credentialsNeeded) {
				login();
			}

			enterBank();

			try {
				TimeUnit.SECONDS.sleep(2);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void showWelcomePage() {
		printLine();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!! Welcome to Bank of Frederico !!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		printLine();
	}

	public void printLine() {
		System.out.println("------------------------------------------------------------------");
	}
	
	public void printLine2() {
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
	}

	public void login() {
		boolean usernameValid = false;
		boolean passwordValid = false;

		System.out.println("\n");
		printLine();
		System.out.println("Login");
		printLine();

		System.out.print("Username: ");
		username = s.nextLine();

		m = md.getMemberByUsername(username);

		if (m != null) {
			usernameValid = true;

			System.out.print("Password: ");
			String password = s.nextLine();
			au = aud.getAuthorizationById(m.getMemberId());
			if (password.equals(au.getPassword())) {
				passwordValid = true;
				credentialsNeeded = false;
				// "2019-09-01 21:55:07"
				// lastLogin = m.getLastLogin();
				//List<Account> accounts = ad.getAccountById(m.getMemberId());
				//lastLogin = thd.getTransactionHistoryMaxTimestamp(accounts);
				
				lastLoginStr = String.format("%s",m.getLastLogin());
				if (lastLoginStr.length() > 7) {
					lastLoginStr = lastLoginStr.substring(0, lastLoginStr.length() - 7);
				}
				//lastLogin = ;
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
//			    Date date = new Date();
//			    String strDate = formatter.format(date);
				lastLogin = md.getCurrentTime();
				m.setLastLogin(lastLogin);
				md.updateMember(m);

				System.out.println("\nSuccessfully logged in.");
			}

		}
		System.out.println();

		if (!usernameValid) {
			System.out.println("Invalid Username.");
		} else if (!passwordValid) {
			System.out.println("Invalid Password.");
		}

	}

	public void enterBank() {

		showWelcomePage();

		if (lastLogin != null) {
			System.out.println("Last Login: " + lastLoginStr);
		}
		System.out.println("\nWelcome " + m.getFirstname() + ".\n");

		accounts = ad.getAccountById(m.getMemberId());

		System.out.println("Accounts:");
		printLine();
		for (Account a : accounts) {
			System.out.printf("Account Number: " + a.getAccountNum() + "\tCurrent Balance: $%.2f\n",
					a.getCurrentBalance());
		}
		printLine();

		printInput();

		do {
			input = s.next();

			switch (input) {

			case "v":
				printTransactionHistory();
				printInput();
				break;

			case "c":
				createAccount();
				printInput();
				break;

			case "a":
				addJointAccount();
				printInput();
				break;

			case "d":
				depositCash();
				printInput();
				break;

			case "w":
				withdrawCash();
				printInput();
				break;

			case "t":
				transferFunds();
				printInput();
				break;
				
			case "p":
				changePassword();
				printInput();
				break;

			case "l":
				credentialsNeeded = true;
				username = null;
				// need to reset all objects
				break;

			default:
				System.out.println("Invalid input.");
				break;
			}

		} while (!credentialsNeeded);

		System.out.println("Logged out.");

		s.nextLine();

	}
	public void printTransactionHistory() {
		accounts = ad.getAccountById(m.getMemberId());
		float amt;
		String str;
		String temp;
		System.out.println("Accounts:");
		printLine2();
		for (Account a : accounts) {
			System.out.printf("Account Number: " + a.getAccountNum() + "\tCurrent Balance: $%.2f\n",
					a.getCurrentBalance());
			
			transactions = thd.getTransactionHistoryByAccountNumber(a.getAccountNum());
			printLine2();
			System.out.println("tran_id\t\ttran_dt\t\t\t\ttran_desc\t\t\t\tamt\t\tbal");
			printLine2();
			for (TransactionHistory t : transactions) {
				System.out.print("\t"+ t.getTransactionId() + "\t" + t.getTransactionDate() + "\t");
				String desc = t.getTransactionDesc();
				//System.out.print(t.getTransactionDesc());
				//32
				if (desc.length() < 32) {
					for (int i = desc.length(); i < 32; i++) {
						desc += " ";
					}
				}
				System.out.print(desc);
				amt = t.getAmount();
				str = String.format("%s",amt);
				//temp = String.format("%s",amt);
				
				
				System.out.print("\t" + t.getAmount());
				//System.out.print("*" + str.length());
				//System.out.println("!" + temp.length());
				if (str.length() <= 7) {
					System.out.print("\t");
				}
				System.out.println("\t" + t.getBalance());
			}
			printLine2();
			System.out.println("\n");
			printLine2();
		}
		
	}
	public void createAccount() {
		a = new Account();
		int accts = ad.createAccount(a, m.getMemberId());
		if (accts > 0) {
			System.out.println("Account " + a.getAccountNum() + " created.");
		}
	}

	public void addJointAccount() {
		int acctNum = -1;
		System.out.print("What is the account number of the account you would like to join? ");
		String str;
		do {
			str = s.nextLine();
		} while (!str.matches("^\\d+$"));

		acctNum = Integer.parseInt(str);

		a = ad.getAccountByAcctNum(acctNum);

		if (a != null) {
			System.out.println(m.getMemberId() + " a: " + a.getAccountNum());
			ad.addJointAccount(m.getMemberId(), a);
			System.out.println("\n\nJoint account added.");
		} else {
			System.out.println("\n\nInvalid account number.");
		}
	}

	public void depositCash() {
		int acctNum = -1;
		boolean acctNumValid = false;
		float amt = 0;

		accounts = ad.getAccountById(m.getMemberId());

		System.out.println("Accounts:");
		printLine();
		for (Account a : accounts) {
			System.out.printf("Account Number: " + a.getAccountNum() + "\tCurrent Balance: $%.2f\n",
					a.getCurrentBalance());
		}
		printLine();
		String str;
		s.nextLine();
		if (accounts.size() == 1) {
			accounts.get(0).getAccountNum();
		}
		if (accounts.size() != 1) {
			do {
				System.out.print("\nWhat is the account number that you want to deposit cash into? ");

				do {
					str = s.nextLine();
					if (!str.matches("^\\d+$")) {
						System.out.print("Invalid entry. Try again: ");
					}
				} while (!str.matches("^\\d+$"));

				acctNum = Integer.parseInt(str);

				for (Account a : accounts) {

					if (acctNum == a.getAccountNum()) {
						acctNumValid = true;
					}
				}
				if (!acctNumValid) {
					System.out.println("Incorrect account number.");
				}
			} while (!acctNumValid);
		}
		a = ad.getAccountByAcctNum(acctNum);

		float curBalance = a.getCurrentBalance();

		do {
			System.out.print("\n\nHow much would you like to deposit? ");
			do {
				str = s.nextLine();
				if (!str.matches("^\\d+.\\d\\d$")) {
					System.out.print("Invalid number format. Try again: ");
				}
			} while (!str.matches("^\\d+.\\d\\d$"));

			amt = Float.parseFloat(str);

			if (curBalance + amt >= 1000000) {
				System.out.println("Invalid amt requested to deposit.\nYou may not exceed $1million in an account.");
			}
		} while (curBalance + amt >= 1000000);

		ad.depositCash(a, amt);

		System.out.println("\nDeposited cash.");
	}

	public void withdrawCash() {
		int acctNum = -1;

		boolean acctNumValid = false;
		float amt = 0;

		accounts = ad.getAccountById(m.getMemberId());

		System.out.println("Accounts:");
		printLine();
		for (Account a : accounts) {
			System.out.printf("Account Number: " + a.getAccountNum() + "\tCurrent Balance: $%.2f\n",
					a.getCurrentBalance());
		}
		printLine();
		String str;
		s.nextLine();
		if (accounts.size() == 1) {
			accounts.get(0).getAccountNum();
		}
		if (accounts.size() != 1) {
			do {
				System.out.print("\nWhat is the account number that you want to withdraw cash from? ");

				do {
					str = s.nextLine();
					if (!str.matches("^\\d+$")) {
						System.out.print("Invalid entry. Try again: ");
					}
				} while (!str.matches("^\\d+$"));

				acctNum = Integer.parseInt(str);

				for (Account a : accounts) {

					if (acctNum == a.getAccountNum()) {
						acctNumValid = true;
					}
				}
				if (!acctNumValid) {
					System.out.println("Incorrect account number.");
				}
			} while (!acctNumValid);
		}
		a = ad.getAccountByAcctNum(acctNum);

		float curBalance = a.getCurrentBalance();

		do {
			System.out.print("\n\nHow much would you like to withdraw? ");
			do {
				str = s.nextLine();
				if (!str.matches("^\\d+.\\d\\d$")) {
					System.out.print("Invalid number format. Try again: ");
				}
			} while (!str.matches("^\\d+.\\d\\d$"));

			amt = Float.parseFloat(str);

			if (curBalance - amt < 0) {
				System.out.println(
						"Invalid amt requested to withdraw.\nYou may not withdraw more than whats in your account.");
			}
		} while (curBalance - amt < 0);

		ad.withdrawCash(a, amt);

		System.out.println("\nWithdrawed cash.");
	}

	public void transferFunds() {
		int wAcctNum = -1;
		int dAcctNum = -1;
		Account aWith;
		Account aDep;
		
		boolean acctNumValid = false;
		float amt = 0;

		accounts = ad.getAccountById(m.getMemberId());

		if (accounts.size() < 2) {
			System.out.println("You must have atleast 2 accounts to transfer.");
			return;
		}

		System.out.println("Accounts:");
		printLine();
		for (Account a : accounts) {
			System.out.printf("Account Number: " + a.getAccountNum() + "\tCurrent Balance: $%.2f\n",
					a.getCurrentBalance());
		}
		printLine();
		String str;
		s.nextLine();

		do {
			System.out.print("\nWhat is the account number that you want to withdraw cash from? ");

			do {
				str = s.nextLine();
				if (!str.matches("^\\d+$")) {
					System.out.print("Invalid entry. Try again: ");
				}
			} while (!str.matches("^\\d+$"));

			wAcctNum = Integer.parseInt(str);

			for (Account a : accounts) {

				if (wAcctNum == a.getAccountNum()) {
					acctNumValid = true;
				}
			}
			if (!acctNumValid) {
				System.out.println("Incorrect account number.");
			}
		} while (!acctNumValid);

		aWith = ad.getAccountByAcctNum(wAcctNum);
//
		
		float curBalance = aWith.getCurrentBalance();

		do {
			System.out.print("\n\nHow much would you like to withdraw? ");
			do {
				str = s.nextLine();
				if (!str.matches("^\\d+.\\d\\d$")) {
					System.out.print("Invalid number format. Try again: ");
				}
			} while (!str.matches("^\\d+.\\d\\d$"));

			amt = Float.parseFloat(str);

			if (curBalance - amt < 0) {
				System.out.println(
						"Invalid amt requested to withdraw.\nYou may not withdraw more than whats in your account.");
			}
		} while (curBalance - amt < 0);

		//ad.withdrawCash(a, amt);
//end with
		//System.out.println("\nWithdrawed cash.");
		// begin deposit part
		
		do {
			System.out.print("\nWhat is the account number that you want to deposit cash into? ");

			do {
				str = s.nextLine();
				if (!str.matches("^\\d+$")) {
					System.out.print("Invalid entry. Try again: ");
				}
			} while (!str.matches("^\\d+$"));

			dAcctNum = Integer.parseInt(str);

			for (Account a : accounts) {

				if (dAcctNum == a.getAccountNum()) {
					acctNumValid = true;
				}
			}
			if (!acctNumValid) {
				System.out.println("Incorrect account number.");
			}
		} while (!acctNumValid);
	
	aDep = ad.getAccountByAcctNum(dAcctNum);

	curBalance = aDep.getCurrentBalance();

	
		if (curBalance + amt >= 1000000) {
			System.out.println("Invalid amt requested to deposit.\nYou may not exceed $1million in an account.");
			return;
		}
	
		ad.transferFunds(aWith, aDep, amt);
		
		System.out.println("Cash transfered.");
		
	}
	
	public void changePassword() {
		Authorization au = aud.getAuthorizationById(m.getMemberId());
		String oldPassword;
		String newPassword;
		s.nextLine();
		System.out.print("Enter old password: ");
		oldPassword = s.nextLine();
		if (oldPassword.equals(au.getPassword())) {
			System.out.println("Enter new password: ");
			newPassword = s.nextLine();
			au.setPassword(newPassword);
			aud.updateAuthorization(au);
			
			System.out.println("Password changed.");
		}
		else {
			System.out.println("Incorrect password.");
		}
		//au.setPassword("pswd");
		//aud.updateAuthorization(au);
	}

	public void printInput() {
		System.out.println("\nInput: ");
		System.out.println("\t(v)iew account transactions");
		System.out.println("\t(c)reate new account");
		System.out.println("\t(a)dd joint account");
		System.out.println("\t(d)eposit funds");
		System.out.println("\t(w)ithdraw funds");
		System.out.println("\t(t)ransfer funds");
		System.out.println("\t(p)assword change");
		System.out.println("\t(l)og out");
	}
	// display all accounts balances and
}

/*
 * MemberDao md = new MemberDaoImpl(); List<Member> members = md.getMember();
 * 
 * System.out.println("\nList<Member> = getMember()"); System.out.println(
 * "----------------------------------------------------------------------------------------------"
 * ); for (Member m : members) { System.out.println(m); }
 * 
 * // may need to make a login function in sql
 * System.out.println("\ngetMemberById()"); System.out.println(
 * "--------------------------------------------------------------------------")
 * ; System.out.println(md.getMemberById(27));
 * //System.out.println(md.getMemberById(50));
 * System.out.println(md.getMemberById(28));
 * 
 * System.out.println(md.getMemberByUsername("howardlt"));
 * System.out.println(md.getMemberByEmail("randomtest7@gmail.com"));
 * 
 * /* System.out.println("\ncreateNewMember()"); System.out.println(
 * "--------------------------------------------------------------------------")
 * ; //!!!!!!! notice java will not have howards membId stores so get id will be
 * 0 by default this is got to be bad practice // maybe in createMember we check
 * to see if m and au have membId if not we query for it? // nvm just implemtned
 * set m and au membId in createNewMember
 * 
 * Member m1 = new Member("howardlt", "Howard", "Tran", "howardlt@gmail.com");
 * System.out.println(m1.getMemberId()); Authorization a = new Authorization();
 * a.setPassword("howie"); m1.toString(); a.toString(); int membs =
 * md.createMember(m1, a); System.out.println("Members created: " + membs);
 * System.out.println(
 * "----------------------------------------------------------------------------------------------"
 * ); for (Member m : members) { System.out.println(m); }
 */
/*
 * 
 * Member m1 = new Member("randomTest15", "Random15", "Test15",
 * "randomtest15@gmail.com"); //System.out.println(m1.getMemberId());
 * Authorization a = new Authorization(); a.setPassword("test");
 * 
 * int membs = md.createMember(m1, a); System.out.println("Members created: " +
 * membs); System.out.println(
 * "----------------------------------------------------------------------------------------------"
 * ); members = md.getMember(); for (Member m : members) {
 * System.out.println(m); }
 */

/*
 * Member m1 = md.getMemberByUsername("howie"); m1.setUsername("howardlt");
 * md.updateMember(m1);
 * 
 * members = md.getMember(); for (Member m : members) { System.out.println(m); }
 */
// auth testing
/*
 * AuthorizationDao aud = new AuthorizationDaoImpl(); /* List<Authorization>
 * authorizations = ad.getAuthorization();
 * 
 * System.out.println("\nList<Authorization> = getAuthorization()");
 * System.out.println(
 * "----------------------------------------------------------------------------------------------"
 * ); for (Authorization au : authorizations) { System.out.println(au); }
 */
/*
 * System.out.println("\ngetAuthorizationById()"); System.out.println(
 * "--------------------------------------------------------------------------")
 * ; System.out.println(aud.getAuthorizationById(27));
 * 
 * Authorization au1 = aud.getAuthorizationById(27); au1.setPassword("pswd");
 * aud.updateAuthorization(au1);
 */
/*
 * List<Authorization> authorizations = aud.getAuthorization();
 * 
 * System.out.println("\nList<Authorization> = getAuthorization()");
 * System.out.println(
 * "----------------------------------------------------------------------------------------------"
 * ); for (Authorization au : authorizations) { System.out.println(au); }
 */

// acct testing
/*
 * AccountDao ad = new AccountDaoImpl(); /* List<Account> accounts =
 * ad.getAccount();
 * 
 * System.out.println("\nList<Account> = getAccount()"); System.out.println(
 * "----------------------------------------------------------------------------------------------"
 * ); for (Account a : accounts) { System.out.println(a); }
 */
/*
 * System.out.println("\ngetAccountById()"); System.out.println(
 * "--------------------------------------------------------------------------")
 * ; System.out.println(ad.getAccountById(30));
 */
/*
 * Member m1 = new Member("randomTest24", "Random24", "Test24",
 * "randomtest24@gmail.com"); int membs = md.createMember(m1, "myPswd"); Account
 * acct = new Account(); int accts = ad.createAccount(acct, m1.getMemberId());
 * 
 * //ad.updateAccount(acct);
 * 
 * System.out.println("\nList<Account> = getAccount()"); System.out.println(
 * "----------------------------------------------------------------------------------------------"
 * ); List<Account> accounts = ad.getAccount(); for (Account a : accounts) {
 * System.out.println(a); }
 */
/*
 * MemberAcctDao mad = new MemberAcctDaoImpl(); List<MemberAcct> memberAccts =
 * mad.getMemberAcct();
 * 
 * System.out.println("\nList<MemberAcct> = getMemberAcct()");
 * System.out.println(
 * "----------------------------------------------------------------------------------------------"
 * ); for (MemberAcct ma : memberAccts) { System.out.println(ma); }
 * 
 * System.out.println("\ngetMemberAcctById()"); System.out.println(
 * "--------------------------------------------------------------------------")
 * ; List<MemberAcct> memberAcct = mad.getMemberAcctById(27); for (MemberAcct ma
 * : memberAcct) { System.out.println(ma); } // tran TransactionHistoryDao thd =
 * new TransactionHistoryDaoImpl();
 * 
 * List<TransactionHistory> transactions = thd.getTransactionHistory();
 * 
 * //NOTICE 999999.99 is rounding to 1 million for some reason
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * System.out.println("\nList<TransactionHistory> = getTransactionHistory()");
 * System.out.println(
 * "----------------------------------------------------------------------------------------------"
 * ); for (TransactionHistory t : transactions) { System.out.println(t); }
 * 
 * System.out.println("\ngetTransactionHistoryByAcctNum()"); System.out.println(
 * "--------------------------------------------------------------------------")
 * ; List<TransactionHistory> transaction2s =
 * thd.getTransactionHistoryByAccountNumber(30); for (TransactionHistory t :
 * transaction2s) { System.out.println(t); }
 */
