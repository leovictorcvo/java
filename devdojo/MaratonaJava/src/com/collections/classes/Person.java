package com.collections.classes;

public class Person implements Comparable<Person>{
	private int id;
	private String nome;
	private String cpf;
	
	
	public Person() {
	}

	public Person(int id, String name, String cpf) {
		this.id = id;
		this.nome = name;
		this.cpf = cpf;
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
	public String toString() {
		return "Person [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return this.cpf != null && this.cpf.equals(other.getCpf());
	}

	@Override
	public int compareTo(Person o) {
		// Esse objeto vem antes do parametro = retornar numero negativo
		// Esse objeto tem o mesmo valor que o parametro = retornar 0
		// Esse objeto vem depois do parametro = retornar numero positivo
		//
		// Em vez de implementar uma função nova, o ideal é utilizar uma que já foi implementada como abaixo
		return this.nome.compareTo(o.getNome());
	}
	
	
}
