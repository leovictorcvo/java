package br.com.leovictor.financeiro.dto;

import org.springframework.data.domain.Page;

import br.com.leovictor.financeiro.domain.Categoria;

public class CategoriaDTO {
	private Long id;
	private String nome;
	
	public CategoriaDTO(Long id, String nome){
		this.id = id;
		this.nome = nome;
	}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static Page<CategoriaDTO> converter(Page<Categoria> lista) {
		return lista != null ? lista.map(CategoriaDTO::new) : null;
	}
	
}
