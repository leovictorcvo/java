package com.jdbc.test;

import java.util.List;

import com.jdbc.classes.Comprador;
import com.jdbc.db.CompradorDB;

public class TesteConexao {

	public static void main(String[] args) {
		//alterar();
		//alterarComRowSet();
		//inserir();
		//excluir();
		selecionarTodos();
		//pesquisarPorNome();
		//pesquisarPorNomeRowSet();
	}
	
	public static void alterar() {
		CompradorDB.update(new Comprador(3, "Gertrudesa", "222.222.222-22"));
	}
	
	public static void alterarComRowSet() {
		CompradorDB.updateUsingRowSet(new Comprador(5, "Bruno", "223.223.223-23"));
	}
	
	public static void excluir() {
		Comprador comprador = new Comprador();
		comprador.setId(3);
		CompradorDB.delete(comprador);
	}

	public static void inserir() {
		CompradorDB.create(new Comprador("Pisquila", "222.222.222-22"));
	}

	public static void pesquisarPorNome() {
		List<Comprador> compradores = CompradorDB.getByName("A");
		for(Comprador comprador : compradores) {
			System.out.println(comprador);
		}
	}

	public static void pesquisarPorNomeRowSet() {
		List<Comprador> compradores = CompradorDB.getByNameUsingRowSet("A");
		for(Comprador comprador : compradores) {
			System.out.println(comprador);
		}
	}

	public static void selecionarTodos() {
		List<Comprador> compradores = CompradorDB.getAll();
		for(Comprador comprador : compradores) {
			System.out.println(comprador);
		}
	}
}
