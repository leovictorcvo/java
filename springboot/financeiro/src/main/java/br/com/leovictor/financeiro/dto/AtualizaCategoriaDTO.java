package br.com.leovictor.financeiro.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.leovictor.financeiro.domain.Categoria;
import br.com.leovictor.financeiro.repository.CategoriaRepository;

public class AtualizaCategoriaDTO {
	@NotNull
	@NotEmpty
	@Length(min = 3, max = 200)
	private String nome;

	public AtualizaCategoriaDTO(String nome){
		this.nome = nome;
	}
	
	public AtualizaCategoriaDTO(){
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria converter(CategoriaRepository categoriaRepository, Long id) {
		Categoria categoria = categoriaRepository.getOne(id);
		categoria.setNome(this.getNome());
		return categoria;
	}

}
