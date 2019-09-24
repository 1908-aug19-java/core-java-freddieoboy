package reim.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection connection;

	//private static boolean IS_TEST = true;
	private static boolean IS_TEST = false;

	public static Connection getHardCodedConnection() throws SQLException {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// use environment variables for connection and saving passwords
		// you would have to restart spring for that
		// url jdbc:postgresql:// host name : port / data base name
		String url = "jdbc:postgresql://freddieoboy.crxyo3jjbo1y.us-east-2.rds.amazonaws.com:5432/postgres";
		String username = "environment_variables";
		String password = "environment_variables";

		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}

		return connection;
	}

	public static Connection getConnection() throws SQLException {

		if (IS_TEST == true) {

			return getH2Connection();
		}

		else {

			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// use environment variables for connection and saving passwords
			// you would have to restart spring for that
			String url = System.getenv("DB_URL");
			String username = System.getenv("DB_USER");
			String password = System.getenv("DB_PASS");

			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(url, username, password);
			}

			return connection;
		}

	}

	public static Connection getH2Connection() {

		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:h2:~/test");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
}
