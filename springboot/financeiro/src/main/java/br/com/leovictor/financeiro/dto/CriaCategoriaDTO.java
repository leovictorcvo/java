package br.com.leovictor.financeiro.dto;


import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.leovictor.financeiro.domain.Categoria;


public class CriaCategoriaDTO {
	@NotBlank
	@Length(min = 3, max = 200)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria converter() {
		return new Categoria(nome);
	}
}
