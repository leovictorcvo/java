package com.jdbc.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class ConexaoFactory {
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/agencia";
		String user = "root";
		String password = "docker";
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JdbcRowSet getRowSetConnection() {
		String url = "jdbc:mysql://localhost:3306/agencia";
		String user = "root";
		String password = "docker";
		
		try {
			JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();
			jdbcRowSet.setUrl(url);
			jdbcRowSet.setUsername(user);
			jdbcRowSet.setPassword(password);
			return jdbcRowSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(JdbcRowSet jrs) {
		try {
			if (jrs != null) {
				jrs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, Statement stmt) {
		try {
			if (conn != null) {
				conn.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
			
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
