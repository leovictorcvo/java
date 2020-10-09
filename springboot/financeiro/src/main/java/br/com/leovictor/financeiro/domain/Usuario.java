package br.com.leovictor.financeiro.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.leovictor.financeiro.dto.UsuarioDTO;

@Entity
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank @Size(min = 5)
	private String nome;
	@Embedded
	private CPF cpf;
	@Column(unique = true)
	@NotBlank @Email
	private String email;
	@NotBlank @Size(min = 6)
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	public Usuario(Long id, String nome, CPF cpf, String email, String senha, List<Perfil> perfis) {
		this(id, nome, cpf, email, senha);
		this.perfis = perfis;
	}
	
	public Usuario(Long id, String nome, CPF cpf, String email, String senha) {
		this(nome, cpf, email, senha);
		this.id = id;
	}

	public Usuario(String nome, CPF cpf, String email, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
	}

	// Necessário para o Hibernate
	@Deprecated
	public Usuario() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CPF getCpf() {
		return cpf;
	}

	public void setCpf(CPF cpf) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public static List<UsuarioDTO> converter(List<Usuario> lista) {
		return lista.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
