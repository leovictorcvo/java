package br.com.leovictor.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/alura_loja_jdbc?use_timezone=true&serverTimezone=UTC", "root", "docker");
	}
}
