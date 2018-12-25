package fr.acceis.forum.servlet;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtils {
	
	private Connection connexion;
	
	public void openConnection() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		this.connexion = DriverManager.getConnection("jdbc:hsqldb:C:\\Users\\Sina Khoubi\\eclipse-workspace\\forum\\data\\basejpa", "sa",  "");
	}
	
	public void closeConnection() throws Exception {
		this.connexion.close();
	}
	
	public Connection getConnexion() {
		return this.connexion;
	}
}
