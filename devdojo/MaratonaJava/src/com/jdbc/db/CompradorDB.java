package com.jdbc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.JdbcRowSet;

import com.jdbc.classes.Comprador;
import com.jdbc.conn.ConexaoFactory;

public class CompradorDB {
	public static void create(Comprador comprador) {
		String sql = "INSERT INTO agencia.comprador (cpf, nome) VALUES (?, ?)";
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comprador.getCpf());
			stmt.setString(2, comprador.getNome());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.close(conn, stmt);
		}
	}

	public static void delete(Comprador comprador) {
		if (comprador == null || comprador.getId() < 1) {
			return;
		}

		String sql = "DELETE FROM agencia.comprador WHERE id = ?";
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comprador.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.close(conn, stmt);
		}
	}

	public static List<Comprador> getAll() {
		String sql = "SELECT id, nome, cpf FROM agencia.comprador";
		Connection conn = ConexaoFactory.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Comprador> compradores = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				compradores.add(new Comprador(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			compradores.clear();
		} finally {
			ConexaoFactory.close(conn, stmt, rs);
		}
		return compradores;
	}

	public static List<Comprador> getByName(String name) {
		String sql = "SELECT id, nome, cpf FROM agencia.comprador WHERE nome LIKE ?";
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Comprador> compradores = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + name + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				compradores.add(new Comprador(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			compradores.clear();
		} finally {
			ConexaoFactory.close(conn, stmt, rs);
		}
		return compradores;
	}

	public static List<Comprador> getByNameUsingRowSet(String name) {
		String sql = "SELECT id, nome, cpf FROM agencia.comprador WHERE nome LIKE ?";
		JdbcRowSet jrs = ConexaoFactory.getRowSetConnection();
		List<Comprador> compradores = new ArrayList<>();
		try {
			jrs.setCommand(sql);
			jrs.setString(1, "%" + name + "%");
			jrs.execute();
			while (jrs.next()) {
				compradores.add(new Comprador(jrs.getInt("id"), jrs.getString("nome"), jrs.getString("cpf")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			compradores.clear();
		} finally {
			ConexaoFactory.close(jrs);
		}
		return compradores;
	}

	public static void update(Comprador comprador) {
		if (comprador == null || comprador.getId() < 1) {
			return;
		}

		String sql = "UPDATE agencia.comprador SET nome = ?, cpf = ? WHERE id = ?";
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comprador.getNome());
			stmt.setString(2, comprador.getCpf());
			stmt.setInt(3, comprador.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.close(conn, stmt);
		}
	}

	public static void updateUsingRowSet(Comprador comprador) {
		String sql = "SELECT id, nome, cpf FROM agencia.comprador WHERE id = ?";
		JdbcRowSet jrs = ConexaoFactory.getRowSetConnection();
		try {
			jrs.setCommand(sql);
			jrs.setInt(1, comprador.getId());
			jrs.execute();
			jrs.next();
			jrs.updateString("nome", comprador.getNome());
			jrs.updateString("cpf", comprador.getCpf());
			jrs.updateRow();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexaoFactory.close(jrs);
		}
	}

}
