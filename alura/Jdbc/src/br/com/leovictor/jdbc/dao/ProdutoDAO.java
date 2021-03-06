package br.com.leovictor.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.leovictor.jdbc.models.Produto;

public class ProdutoDAO {
	private Connection connection = null;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Produto produto) throws SQLException {
		final String baseSql = "INSERT INTO Produto (nome, descricao) VALUES (? , ?)";

		PreparedStatement stm = connection.prepareStatement(baseSql, Statement.RETURN_GENERATED_KEYS);
		stm.setString(1, produto.getNome());
		stm.setString(2, produto.getDescricao());

		stm.execute();

		ResultSet rst = stm.getGeneratedKeys();
		if (rst.next()) {
			System.out.println("O id gerado foi o " + rst.getInt(1));
			produto.setId(rst.getInt(1));
		}
		rst.close();
		stm.close();
	}

	public List<Produto> listar() throws SQLException {
		List<Produto> lista = new ArrayList<Produto>();
		final String sql = "SELECT id, nome, descricao FROM Produto";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					lista.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao")));
				}
			}
		}
		
		return lista;
	}
}
