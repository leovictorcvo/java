package br.com.leovictor.financeiro.integrationTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URISyntaxException;
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
import br.com.leovictor.financeiro.dto.AtualizaCategoriaDTO;
import br.com.leovictor.financeiro.dto.CategoriaDTO;
import br.com.leovictor.financeiro.repository.CategoriaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(br.com.leovictor.financeiro.integrationTest.AuthenticationUtil.class)
public class CategoriaControllerTest {
	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	@MockBean
	private CategoriaRepository categoriaRepositoryMock;

	@Autowired
	private AuthenticationUtil util;

	private final Categoria categoriaParaSalvar = new Categoria("Categoria");
	private final Categoria categoriaSalva = new Categoria(1L, "Categoria");
	private final String categoriaInexistente = "Não Cadastrada";
	private final AtualizaCategoriaDTO categoriaParaAtualizar = new AtualizaCategoriaDTO("Categoria atualizada");

	@BeforeEach
	public void setUp() throws URISyntaxException {
		PageImpl<Categoria> categoriaPage = new PageImpl<>(List.of(categoriaSalva));

		util.ConfiguraMocks();

		BDDMockito.when(categoriaRepositoryMock.getOne(ArgumentMatchers.anyLong())).thenReturn(categoriaSalva);
		BDDMockito.when(categoriaRepositoryMock.findAll(ArgumentMatchers.any(Pageable.class)))
				.thenReturn(categoriaPage);
		BDDMockito.when(categoriaRepositoryMock.findByNomeIgnoreCase(ArgumentMatchers.eq(categoriaSalva.getNome()),
				ArgumentMatchers.any(Pageable.class))).thenReturn(categoriaPage);
		BDDMockito.when(categoriaRepositoryMock.findByNomeIgnoreCase(ArgumentMatchers.eq(categoriaInexistente),
				ArgumentMatchers.any(Pageable.class))).thenReturn(null);
		BDDMockito.when(categoriaRepositoryMock.findById(ArgumentMatchers.eq(categoriaSalva.getId())))
				.thenReturn(Optional.of(categoriaSalva));
		BDDMockito.when(categoriaRepositoryMock.save(ArgumentMatchers.any(Categoria.class))).thenReturn(categoriaSalva);
		BDDMockito.doNothing().when(categoriaRepositoryMock).deleteById(ArgumentMatchers.anyLong());
	}

	@Test
	void deveRetornarAsCategoriasPaginadas() {
		ResponseEntity<PageableResponse<CategoriaDTO>> responseEntity = testRestTemplate.exchange("/categorias",
				HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<CategoriaDTO>>() {
				});

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().toList()).isNotEmpty();
		assertThat(responseEntity.getBody().toList().get(0).getNome()).isEqualTo(categoriaSalva.getNome());
	}

	@Test
	void deveFiltrarAsCategoriasPeloNomeERetornarAsEncontradasPaginadas() {
		ResponseEntity<PageableResponse<CategoriaDTO>> responseEntity = testRestTemplate.exchange(
				"/categorias?nomeCategoria=" + categoriaSalva.getNome(), HttpMethod.GET, null,
				new ParameterizedTypeReference<PageableResponse<CategoriaDTO>>() {
				});

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().toList()).isNotEmpty();
		assertThat(responseEntity.getBody().toList().get(0).getNome()).isEqualTo(categoriaSalva.getNome());
	}

	@Test
	void deveRetornarNullCasoONomeDaCategoriaNãoSejaEncontradoAoFiltrar() {
		ResponseEntity<PageableResponse<CategoriaDTO>> responseEntity = testRestTemplate.exchange(
				"/categorias?nomeCategoria=" + categoriaInexistente, HttpMethod.GET, null,
				new ParameterizedTypeReference<PageableResponse<CategoriaDTO>>() {
				});

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNull();
	}

	@Test
	void deveEncontrarACategoriaPeloId() {
		ResponseEntity<CategoriaDTO> responseEntity = testRestTemplate.exchange("/categorias/" + categoriaSalva.getId(),
				HttpMethod.GET, null, CategoriaDTO.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().getNome()).isEqualTo(categoriaSalva.getNome());
	}

	@Test
	void deveRetornar404CasoIdNaoEstejaCadastrado() {
		ResponseEntity<CategoriaDTO> responseEntity = testRestTemplate.exchange("/categorias/200", HttpMethod.GET, null,
				CategoriaDTO.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(responseEntity.getBody()).isNull();
	}

	@Test
	void deveRetornarCategoriaAposSalvar() {
		HttpEntity<Categoria> httpEntity = util.createAuthenticatedJsonHttpEntity(categoriaParaSalvar, true);
		ResponseEntity<CategoriaDTO> responseEntity = testRestTemplate.exchange("/categorias", HttpMethod.POST,
				httpEntity, CategoriaDTO.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().getId()).isEqualTo(categoriaSalva.getId());
		assertThat(responseEntity.getBody().getNome()).isEqualTo(categoriaSalva.getNome());
	}

	@Test
	void deveProiberCriarCategoriaParaNaoAdministradores() {
		HttpEntity<Categoria> httpEntity = util.createAuthenticatedJsonHttpEntity(categoriaParaSalvar, false);
		ResponseEntity<CategoriaDTO> responseEntity = testRestTemplate.exchange("/categorias", HttpMethod.POST,
				httpEntity, CategoriaDTO.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}

	@Test
	void deveRetornarCategoriaAposAtualizar() {
		HttpEntity<AtualizaCategoriaDTO> httpEntity = util.createAuthenticatedJsonHttpEntity(categoriaParaAtualizar,
				true);
		ResponseEntity<CategoriaDTO> responseEntity = testRestTemplate.exchange("/categorias/" + categoriaSalva.getId(),
				HttpMethod.PUT, httpEntity, CategoriaDTO.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().getId()).isEqualTo(categoriaSalva.getId());
		assertThat(responseEntity.getBody().getNome()).isEqualTo(categoriaParaAtualizar.getNome());
	}

	@Test
	void deveProibirAtualizarCategoriaParaNaoAdministradores() {
		HttpEntity<AtualizaCategoriaDTO> httpEntity = util.createAuthenticatedJsonHttpEntity(categoriaParaAtualizar,
				false);
		ResponseEntity<CategoriaDTO> responseEntity = testRestTemplate.exchange("/categorias/" + categoriaSalva.getId(),
				HttpMethod.PUT, httpEntity, CategoriaDTO.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}

	@Test
	void deveExcluirSemRetornarConteudo() {
		HttpEntity<Void> httpEntity = util.createAuthenticatedJsonHttpEntity(null, true);
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/categorias/" + categoriaSalva.getId(),
				HttpMethod.DELETE, httpEntity, Void.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		assertThat(responseEntity.getBody()).isNull();
	}

	@Test
	void deveProibirExcluirCategoriaParaNaoAdministradores() {
		HttpEntity<Void> httpEntity = util.createAuthenticatedJsonHttpEntity(null, false);
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/categorias/" + categoriaSalva.getId(),
				HttpMethod.DELETE, httpEntity, Void.class);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}
}
