package br.com.leovictor.financeiro.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.leovictor.financeiro.config.validation.CPFValid;
import br.com.leovictor.financeiro.domain.CPF;
import br.com.leovictor.financeiro.domain.Usuario;

public class CriaUsuarioDTO {
	@NotNull
	@NotEmpty
	@Length(min = 5, max = 250)
	private String nome;
	@NotNull
	@NotEmpty
	@CPFValid
	private String cpf;
	@NotNull
	@NotEmpty
	@Email
	private String email;
	@NotNull
	@NotEmpty
	@Length(min = 6)
	private String senha;

	public CriaUsuarioDTO() {
		
	}
	
	public CriaUsuarioDTO(String nome, String cpf, String email, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario converter() {
		return new Usuario(nome, new CPF(cpf), email, senha);
	}

}
