package br.com.leovictor.financeiro.dto;

import br.com.leovictor.financeiro.domain.Usuario;

public class UsuarioDTO {
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf().toString();
		this.email = usuario.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}
	
	
}
