package com.jdbc.classes;

public class Comprador {
	private int id;
	private String nome;
	private String cpf;

	public Comprador() {
	}

	public Comprador(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public Comprador(int id, String nome, String cpf) {
		this(nome, cpf);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comprador other = (Comprador) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comprador [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
	}

}
