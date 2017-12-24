package br.ufc.qx.agenda.webdev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/agenda",
					"root", "mysql12345");
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

}
