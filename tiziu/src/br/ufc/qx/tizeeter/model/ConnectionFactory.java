package br.ufc.qx.tizeeter.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/tizeeter";
		String user = "root";
		String password = "34346duck";
		
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException cnfe) {
			throw new RuntimeException();
		} catch (SQLException sqle) {
			throw new RuntimeException();
		}
	}
}
