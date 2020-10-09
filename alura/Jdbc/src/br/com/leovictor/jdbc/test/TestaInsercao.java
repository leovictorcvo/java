package br.com.leovictor.jdbc.test;

import java.sql.Connection;

import br.com.leovictor.jdbc.dao.ProdutoDAO;
import br.com.leovictor.jdbc.factory.ConnectionFactory;
import br.com.leovictor.jdbc.models.Produto;

public class TestaInsercao {

	public static void main(String[] args) {
		Produto produto = new Produto("Pendrive 16GB", "Kingston 16GB");
		ConnectionFactory factory = new ConnectionFactory(); 
		try (Connection connection = factory.getConnection();) {
			ProdutoDAO produtoDao = new ProdutoDAO(connection);
			produtoDao.salvar(produto);
			produtoDao.listar().stream().forEach(pdt -> System.out.println(pdt));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
