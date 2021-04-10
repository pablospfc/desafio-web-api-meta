package br.com.restcontato.config;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class DBConfig {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/testemeta?useSSL=false", "root", "45enef80khs1");
	}
}
