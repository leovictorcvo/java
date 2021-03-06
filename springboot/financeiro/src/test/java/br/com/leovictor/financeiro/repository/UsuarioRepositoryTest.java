package br.com.leovictor.financeiro.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import br.com.leovictor.financeiro.domain.CPF;
import br.com.leovictor.financeiro.domain.Usuario;

@DataJpaTest
class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private Usuario usuarioNovo = new Usuario("teste", new CPF("55761387085"), "teste@teste.com", "123456");

	@BeforeEach
	void setUp() {
	}

	@Test
	void deveCriarUmUsuarioComSucesso() {
		Usuario novo = usuarioRepository.save(usuarioNovo);

		assertThat(novo).isNotNull();
		assertThat(novo.getId()).isNotNull();
		assertThat(novo.getNome()).isEqualTo(usuarioNovo.getNome());
	}
	
	@Test
	void deveGerarUmExcecaoAoSalvarUmaUsuarioInvalido() {
		Usuario usuarioInvalido = new Usuario();

		assertThatThrownBy(() -> usuarioRepository.save(usuarioInvalido)).isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void deveAtualizarUmUsuarioComSucesso() {
		Usuario novo = usuarioRepository.save(usuarioNovo);
		novo.setNome("Atualizado");
		Usuario atualizado = usuarioRepository.save(novo);
		
		assertThat(atualizado).isNotNull();
		assertThat(novo.getId()).isEqualTo(atualizado.getId());
		assertThat(novo.getNome()).isEqualTo(atualizado.getNome());
	}
	
	@Test
	void deveGerarUmExcecaoAoAtualizarUmaUsuarioComDadosInvalidos() {
		Usuario salvo = usuarioRepository.saveAndFlush(usuarioNovo);
		salvo.setNome(null);
		assertThatThrownBy(() -> usuarioRepository.saveAndFlush(salvo)).isInstanceOf(ConstraintViolationException.class);
	}
	
	@Test
	void deveExcluirUmUsuarioComSucesso() {
		Usuario salvo = usuarioRepository.save(usuarioNovo);
		
		usuarioRepository.deleteById(salvo.getId());
		
		Optional<Usuario> optional = usuarioRepository.findById(salvo.getId());
		
		assertThat(optional).isEmpty();
	}
	
	@Test
	void deveEncontrarUmUsuarioPeloEmail() {
		Usuario salvo = usuarioRepository.save(usuarioNovo);
		
		Optional<Usuario> optional = usuarioRepository.findByEmail(salvo.getEmail());
		
		assertThat(optional).isNotEmpty();
		assertThat(optional.get().getEmail()).isEqualTo(salvo.getEmail());
	}
}
