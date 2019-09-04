package bank.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import bank.model.Account;
import bank.model.TransactionHistory;
import bank.model.dao.AccountDao;
import bank.model.dao.TransactionHistoryDao;
import bank.model.util.ConnectionUtil;

public class TransactionHistoryDaoImpl implements TransactionHistoryDao {
	
	TransactionHistory th = null;
	AccountDao ad = new AccountDaoImpl();
	Account account = null;
	
	@Override
	public List<TransactionHistory> getTransactionHistory() {
		
		//NOTICE 999999.99 is rounding to 1 million for some reason !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// floats are failing at addition/subtraction
		String sql = "select * from bank.\"TransactionHistory\";";
		
		
		
		List<TransactionHistory> transactions = new ArrayList<>();
		
		// makes it autocloseable
		try (	Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			/*
			for(int i =0;i<rs.getMetaData().getColumnCount();i++) {
				System.out.println(rs.getMetaData().getColumnName(i+1));
			}*/
			
			while (rs.next()) {
				/*
				 *  private int transactionId;
					private Timestamp transactionDate;
					private String transactionDesc;
					// private int accountNum;
					private Account account;
					private float amount;
					private float balance;
				 */
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				int transactionId = rs.getInt("tran_id");
				Timestamp transactionDate = rs.getTimestamp("tran_dt");
				String transactionDesc = rs.getString("tran_desc");
				int accountNum = rs.getInt("acct_num");
				float amount = rs.getFloat("amt");
				float balance = rs.getFloat("bal");
				
				account = ad.getAccountByAcctNum(accountNum);
//				System.out.println(deptId+deptName+salary);
				th = new TransactionHistory(transactionId, transactionDate, transactionDesc, account, amount, balance);
				//System.out.println(m);
				transactions.add(th);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return transactions;
	}

	@Override
	public Timestamp getTransactionHistoryById(int membId) {
		
		return null;
	}

	@Override
	public List<TransactionHistory> getTransactionHistoryByAccountNumber(int acctNum) {
		List<TransactionHistory> transactions = new ArrayList<>();
		String sql = "select * from bank.\"TransactionHistory\"" + 
				"where acct_num = ?" + 
				"order by tran_id desc;";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			ps.setInt(1, acctNum);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				int transactionId = rs.getInt("tran_id");
				Timestamp transactionDate = rs.getTimestamp("tran_dt");
				String transactionDesc = rs.getString("tran_desc");
				int accountNum = rs.getInt("acct_num");
				float amount = rs.getFloat("amt");
				float balance = rs.getFloat("bal");
				
				account = ad.getAccountByAcctNum(accountNum);
//				System.out.println(deptId+deptName+salary);
				th = new TransactionHistory(transactionId, transactionDate, transactionDesc, account, amount, balance);
				//System.out.println(m);
				transactions.add(th);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		return transactions;
	}

	@Override
	public List<TransactionHistory> getTransactionHistoryByDesc(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionHistory> getTransactionHistoryByAmount(float f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionHistory> getTransactionHistoryByBalance(float f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionHistory> getTransactionHistoryByTimestamp(Timestamp t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTransactionHistoryMaxTimestamp(List<Account> accounts) {
		Timestamp t = null;
		String sql = "select max(tran_dt) from bank.\"TransactionHistory\" where ";
		
		for (Account a : accounts) {
			sql += "acct_num = " + a.getAccountNum() + " or ";
		}
		sql = sql.substring(0, sql.length() - 3);
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			// handle the first parameterization
			// jdbc 1 based index
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				// might be a good idea to have a static final list of these so you dont have to change everywhere
				t = (Timestamp) rs.getObject(1); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}

	

}
