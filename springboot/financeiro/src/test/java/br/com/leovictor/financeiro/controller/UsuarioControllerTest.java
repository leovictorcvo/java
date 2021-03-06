package br.com.leovictor.financeiro.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.leovictor.financeiro.domain.CPF;
import br.com.leovictor.financeiro.domain.Usuario;
import br.com.leovictor.financeiro.dto.AtualizaUsuarioDTO;
import br.com.leovictor.financeiro.dto.CriaUsuarioDTO;
import br.com.leovictor.financeiro.dto.UsuarioDTO;
import br.com.leovictor.financeiro.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
class UsuarioControllerTest {
	@InjectMocks
	private UsuarioController usuarioController;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuarioSalvo = new Usuario(1L, "teste", new CPF("55761387085"), "teste@teste.com", "teste");
	private CriaUsuarioDTO usuarioParaCriar = new CriaUsuarioDTO("teste", "55761387085", "teste@teste.com", "teste");
	private AtualizaUsuarioDTO usuarioParaAtualizar = new AtualizaUsuarioDTO("teste de atualização", "55761387085", "teste@teste.com");

	@BeforeEach
	void setUp() {
		List<Usuario> usuarioList = List.of(usuarioSalvo);
		BDDMockito.when(usuarioRepository.findAll()).thenReturn(usuarioList);
		BDDMockito.when(usuarioRepository.findById(ArgumentMatchers.eq(1L))).thenReturn(Optional.of(usuarioSalvo));
		BDDMockito.when(usuarioRepository.findById(ArgumentMatchers.eq(2L))).thenReturn(Optional.empty());
		BDDMockito.when(usuarioRepository.getOne(ArgumentMatchers.eq(1L))).thenReturn(usuarioSalvo);
		BDDMockito.when(usuarioRepository.save(ArgumentMatchers.any(Usuario.class))).thenReturn(usuarioSalvo);
	}
	
	@Test
	void deveListarTodosUsuarios() {
		List<UsuarioDTO> lista = usuarioController.listar();
		
		assertThat(lista).isNotNull();
		assertThat(lista.size()).isEqualTo(1);
	}

	@Test
	void deveRetornarOsDadosDeUmUsuarioExistente() {
		ResponseEntity<UsuarioDTO> resposta = usuarioController.detalhar(1L);

		assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resposta.getBody()).isNotNull();
		assertThat(resposta.getBody().getId()).isEqualTo(1L);
	}

	@Test
	void deveRetornarStatus404QuandoUmUsuarioNaoExiste() {
		ResponseEntity<UsuarioDTO> resposta = usuarioController.detalhar(2L);
		
		assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(resposta.getBody()).isNull();
	}
	
	@Test
	void deveCriarUsuarioComSucesso() {
		ResponseEntity<UsuarioDTO> resposta = usuarioController.criar(usuarioParaCriar, UriComponentsBuilder.newInstance());

		assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(resposta.getBody()).isNotNull();
		assertThat(resposta.getBody().getId()).isEqualTo(1L);
		assertThat(resposta.getBody().getNome()).isEqualTo(usuarioParaCriar.getNome());
		
	}
	
	@Test
	void deveAtualizarUsuarioComSucesso() {
		ResponseEntity<UsuarioDTO> resposta = usuarioController.atualizar(1L, usuarioParaAtualizar);

		assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(resposta.getBody()).isNotNull();
		assertThat(resposta.getBody().getId()).isEqualTo(1L);
		assertThat(resposta.getBody().getNome()).isEqualTo(usuarioParaAtualizar.getNome());
		
	}
	
	@Test
	void deveExcluirUsuarioComSucesso() {
		ResponseEntity<?> resposta = usuarioController.excluir(1L);

		assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		assertThat(resposta.getBody()).isNull();
		
	}
}
