package br.com.leovictor.financeiro.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.leovictor.financeiro.config.validation.CPFValid;
import br.com.leovictor.financeiro.domain.CPF;
import br.com.leovictor.financeiro.domain.Usuario;
import br.com.leovictor.financeiro.repository.UsuarioRepository;

public class AtualizaUsuarioDTO {
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

	

	public AtualizaUsuarioDTO(String nome, String cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public AtualizaUsuarioDTO() {
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

	public Usuario converter(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setNome(getNome());
		usuario.setCpf(new CPF(getCpf()));
		usuario.setEmail(getEmail());
		return usuario;
	}
}
