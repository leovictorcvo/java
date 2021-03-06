package br.com.leovictor.financeiro.integrationTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.leovictor.financeiro.domain.Categoria;
import br.com.leovictor.financeiro.domain.Lancamento;
import br.com.leovictor.financeiro.dto.AtualizaLancamentoDTO;
import br.com.leovictor.financeiro.dto.CriaLancamentoDTO;
import br.com.leovictor.financeiro.dto.LancamentoDTO;
import br.com.leovictor.financeiro.repository.CategoriaRepository;
import br.com.leovictor.financeiro.repository.LancamentoRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(br.com.leovictor.financeiro.integrationTest.AuthenticationUtil.class)
public class LancamentoControllerTest {
	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	@MockBean
	private LancamentoRepository lancamentoRepositoryMock;

	@MockBean
	private CategoriaRepository categoriaRepositoryMock;

	@Autowired
	private AuthenticationUtil util;

	private PageImpl<Lancamento> lancamentosAdmin = null;
	private PageImpl<Lancamento> lancamentosUsuario = null;
	private Categoria categoriaValida = new Categoria(1L, "Categoria válida");
	private Long categoriaInvalidaId = 2L;
	private CriaLancamentoDTO lancamentoParaCriar = new CriaLancamentoDTO(LocalDateTime.now(), "Lançamento para criar", categoriaValida.getId(), new BigDecimal(100.00));
	private CriaLancamentoDTO lancamentoParaCriarInvalido = new CriaLancamentoDTO(LocalDateTime.now(), "Lançamento inválido", categoriaInvalidaId, new BigDecimal(100.00));
	private AtualizaLancamentoDTO lancamentoParaAtualizar = new AtualizaLancamentoDTO("Lançamento atualizado", categoriaValida.getId(), new BigDecimal(2000.20));
	private AtualizaLancamentoDTO lancamentoParaAtualizarInvalido = new AtualizaLancamentoDTO("Lançamento inválido", categoriaInvalidaId, new BigDecimal(5000.20));
	private Lancamento lancamentoSalvo;
			
	@BeforeEach
	public void setUp() {
		util.ConfiguraMocks();
		lancamentosAdmin = new PageImpl<>(List.of(new Lancamento(1L, LocalDateTime.now(), "lançamento Admin",
				util.adminAutenticado, new Categoria(1L, "teste"), new BigDecimal(100.01))));
		lancamentosUsuario = new PageImpl<>(List.of(new Lancamento(2L, LocalDateTime.now(), "lançamento Usuario",
				util.usuarioAutenticado, new Categoria(1L, "teste"), new BigDecimal(200.00))));

		lancamentoSalvo = new Lancamento(1L, lancamentoParaCriar.getData(), lancamentoParaCriar.getDescricao(), util.adminAutenticado, categoriaValida, lancamentoParaCriar.getValor());
		
		BDDMockito.when(categoriaRepositoryMock.findById(ArgumentMatchers.eq(categoriaValida.getId()))).thenReturn(Optional.of(categoriaValida));
		BDDMockito.when(categoriaRepositoryMock.findById(ArgumentMatchers.eq(categoriaInvalidaId))).thenReturn(Optional.empty());
		
		BDDMockito.when(lancamentoRepositoryMock.findByUsuarioId(ArgumentMatchers.eq(util.adminAutenticado.getId()),
				ArgumentMatchers.any(Pageable.class))).thenReturn(lancamentosAdmin);
		BDDMockito.when(lancamentoRepositoryMock.findByUsuarioId(ArgumentMatchers.eq(util.usuarioAutenticado.getId()),
				ArgumentMatchers.any(Pageable.class))).thenReturn(lancamentosUsuario);
		BDDMockito.when(lancamentoRepositoryMock.findById(ArgumentMatchers.eq(lancamentoSalvo.getId()))).thenReturn(Optional.of(lancamentoSalvo));
		BDDMockito.when(lancamentoRepositoryMock.save(ArgumentMatchers.any(Lancamento.class))).thenReturn(lancamentoSalvo);
	}

	@Test
	public void naoDeveListarLancamentosParaUsuarioNaoAutenticado() {
		ResponseEntity<?> responseEntity = testRestTemplate.exchange("/lancamentos", HttpMethod.GET, null, Void.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
		assertThat(responseEntity.getBody()).isNull();
	}

	@Test
	public void deveListarSomenteOsLancamentosParaUsuarioAutenticado() {
		HttpEntity<Void> httpEntity = util.createAuthenticatedJsonHttpEntity(null, true);
		ResponseEntity<PageableResponse<LancamentoDTO>> responseEntity = testRestTemplate.exchange("/lancamentos",
				HttpMethod.GET, httpEntity, new ParameterizedTypeReference<PageableResponse<LancamentoDTO>>() {
				});

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().toList()).isNotEmpty();
		assertThat(responseEntity.getBody().toList().size()).isEqualTo(1);
		assertThat(responseEntity.getBody().toList().get(0).getId())
				.isEqualTo(lancamentosAdmin.toList().get(0).getId());

		httpEntity = util.createAuthenticatedJsonHttpEntity(null, false);
		responseEntity = testRestTemplate.exchange("/lancamentos", HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<PageableResponse<LancamentoDTO>>() {
				});

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().toList()).isNotEmpty();
		assertThat(responseEntity.getBody().toList().size()).isEqualTo(1);
		assertThat(responseEntity.getBody().toList().get(0).getId())
				.isEqualTo(lancamentosUsuario.toList().get(0).getId());
	}
	
	@Test
	public void deveCriarLancamentosParaUsuarioAutenticado() {
		HttpEntity<CriaLancamentoDTO> httpEntity = util.createAuthenticatedJsonHttpEntity(lancamentoParaCriar, true);
		ResponseEntity<LancamentoDTO> responseEntity = testRestTemplate.exchange("/lancamentos", HttpMethod.POST, httpEntity, LancamentoDTO.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().getId()).isEqualTo(lancamentoSalvo.getId());
		assertThat(responseEntity.getBody().getData()).isEqualTo(lancamentoSalvo.getData());
	}

	@Test
	public void naoDeveCriarLancamentosComCategoriaInvalida() {
		HttpEntity<CriaLancamentoDTO> httpEntity = util.createAuthenticatedJsonHttpEntity(lancamentoParaCriarInvalido, true);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange("/lancamentos", HttpMethod.POST, httpEntity, String.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isNotNull();
	}

	@Test
	public void deveAtualizarLancamentosParaUsuarioAutenticado() {
		HttpEntity<AtualizaLancamentoDTO> httpEntity = util.createAuthenticatedJsonHttpEntity(lancamentoParaAtualizar, true);
		ResponseEntity<LancamentoDTO> responseEntity = testRestTemplate.exchange("/lancamentos/" + lancamentoSalvo.getId(), HttpMethod.PUT, httpEntity, LancamentoDTO.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().getId()).isEqualTo(lancamentoSalvo.getId());
		assertThat(responseEntity.getBody().getData()).isEqualTo(lancamentoSalvo.getData());
	}

	@Test
	public void naoDeveAtualizarLancamentosComCategoriaInvalida() {
		HttpEntity<AtualizaLancamentoDTO> httpEntity = util.createAuthenticatedJsonHttpEntity(lancamentoParaAtualizarInvalido, true);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange("/lancamentos/" + lancamentoSalvo.getId(), HttpMethod.PUT, httpEntity, String.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isNotNull();
	}
	
	@Test
	void deveExcluirSemRetornarConteudo() {
		HttpEntity<Void> httpEntity = util.createAuthenticatedJsonHttpEntity(null, true);
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/lancamentos/" + lancamentoSalvo.getId(),
				HttpMethod.DELETE, httpEntity, Void.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		assertThat(responseEntity.getBody()).isNull();
	}

	@Test
	void deveProibirExcluirLancamentoQuenNaoSejaDoUsuario() {
		HttpEntity<Void> httpEntity = util.createAuthenticatedJsonHttpEntity(null, false);
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/lancamentos/" + lancamentoSalvo.getId(),
				HttpMethod.DELETE, httpEntity, Void.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

}
