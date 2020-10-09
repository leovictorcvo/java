package com.leovictor.cursojava.aula52;

public class Contato {
	private static int id = 0;
	private String nome;
	private String telefone;
	
	public Contato() {
		id = id + 1;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public static int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Contato " + id + " [nome=" + nome + ", telefone=" + telefone + "]";
	}
	
}
