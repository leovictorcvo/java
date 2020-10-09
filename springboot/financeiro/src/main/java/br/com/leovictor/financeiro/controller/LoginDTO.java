package br.com.leovictor.financeiro.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.sun.istack.NotNull;

public class LoginDTO {
	@NotNull
	@NotEmpty
	@Email
	private String email;
	@NotNull
	@NotEmpty
	private String senha;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

}
