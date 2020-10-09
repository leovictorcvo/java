package br.com.leovictor.jdbc.test;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.leovictor.jdbc.factory.ConnectionFactory;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection(); 
		
		connection.close();

	}

}
