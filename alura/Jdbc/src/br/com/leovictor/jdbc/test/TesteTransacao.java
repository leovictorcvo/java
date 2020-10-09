package br.com.leovictor.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.leovictor.jdbc.factory.ConnectionFactory;

public class TesteTransacao {

	public static void main(String[] args) {
		final String baseSql = "INSERT INTO Produto (nome, descricao) VALUES (? , ?)";

		ConnectionFactory factory = new ConnectionFactory();
		try (Connection connection = factory.getConnection()) {
			connection.setAutoCommit(false);

			try (PreparedStatement stm = connection.prepareStatement(baseSql, Statement.RETURN_GENERATED_KEYS)) {

				insereProduto("TV", "TV Smart 45 polegadas", stm);
				insereProduto("Notebook", "Notebook Samsung", stm);
				connection.commit();
			} catch (Exception e) {
				connection.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void insereProduto(final String nome, final String descricao, PreparedStatement stm)
			throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);

		stm.execute();
		ResultSet rst = stm.getGeneratedKeys();
		if (rst.next()) {
			System.out.println("O id gerado foi o " + rst.getInt(1));
		}
		rst.close();
	}

}
