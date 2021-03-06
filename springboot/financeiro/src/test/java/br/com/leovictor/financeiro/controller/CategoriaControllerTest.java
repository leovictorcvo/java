package br.com.leovictor.financeiro.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.leovictor.financeiro.domain.Categoria;
import br.com.leovictor.financeiro.dto.AtualizaCategoriaDTO;
import br.com.leovictor.financeiro.dto.CategoriaDTO;
import br.com.leovictor.financeiro.dto.CriaCategoriaDTO;
import br.com.leovictor.financeiro.repository.CategoriaRepository;

@ExtendWith(SpringExtension.class)
class CategoriaControllerTest {
	@InjectMocks
	private CategoriaController categoriaController;
	
	@Mock
	private CategoriaRepository categoriaRepositoryMock;
	
	private final Categoria categoriaParaSalvar = new Categoria("Categoria");
	private final Categoria categoriaSalva = new Categoria(1L, "Categoria");
	private final String categoriaInexistente = "Não Cadastrada";
	private final AtualizaCategoriaDTO atualizaCategoriaDTO = new AtualizaCategoriaDTO("Categoria");
	
	@BeforeEach
	public void setUp() throws URISyntaxException {
		PageImpl<Categoria> categoriaPage = new PageImpl<>(List.of(categoriaSalva));
		
		BDDMockito.when(categoriaRepositoryMock.getOne(ArgumentMatchers.anyLong())).thenReturn(categoriaSalva);
		BDDMockito.when(categoriaRepositoryMock.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(categoriaPage);
		BDDMockito.when(categoriaRepositoryMock.findByNomeIgnoreCase(ArgumentMatchers.eq(categoriaSalva.getNome()), ArgumentMatchers.any(Pageable.class))).thenReturn(categoriaPage);
		BDDMockito.when(categoriaRepositoryMock.findByNomeIgnoreCase(ArgumentMatchers.eq(categoriaInexistente), ArgumentMatchers.any(Pageable.class))).thenReturn(null);
		BDDMockito.when(categoriaRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(categoriaSalva));
		BDDMockito.when(categoriaRepositoryMock.save(ArgumentMatchers.any(Categoria.class))).thenReturn(categoriaSalva);
		BDDMockito.doNothing().when(categoriaRepositoryMock).deleteById(ArgumentMatchers.anyLong());
	}
	
	@Test
	void deveListarTodasAsCategoriasPaginadas() {
		Page<CategoriaDTO> page = categoriaController.listar(null, PageRequest.of(0, 5));
		
		Assertions.assertThat(page).isNotNull();
		Assertions.assertThat(page.toList()).isNotEmpty();
		Assertions.assertThat(page.toList().get(0).getNome()).isEqualTo(categoriaSalva.getNome());
	}

	@Test
	void deveFiltrarAsCategoriasPeloNomeERetornarAsEncontradasPaginadas() {
		Page<CategoriaDTO> page = categoriaController.listar(categoriaSalva.getNome(), PageRequest.of(0, 5));
		
		Assertions.assertThat(page).isNotNull();
		Assertions.assertThat(page.toList()).isNotEmpty();
		Assertions.assertThat(page.toList().get(0).getNome()).isEqualTo(categoriaSalva.getNome());
	}
	
	@Test
	void deveRetornarNullCasoNãoEncontreONomeFiltrado() {
		Page<CategoriaDTO> page = categoriaController.listar(categoriaInexistente, PageRequest.of(0, 5));
		
		Assertions.assertThat(page).isNull();
	}

	@Test
	void deveEncontrarACategoriaPeloId() {
		CategoriaDTO categoria = categoriaController.detalhar(categoriaSalva.getId()).getBody();
		
		Assertions.assertThat(categoria).isNotNull();
		Assertions.assertThat(categoria.getNome()).isNotNull();
		Assertions.assertThat(categoria.getNome()).isEqualTo(categoriaSalva.getNome());
	}

	@Test
	void deveRetornarCategoriaAposSalvar() {
		CriaCategoriaDTO  dto = new CriaCategoriaDTO();
		dto.setNome(categoriaParaSalvar.getNome());
		
		CategoriaDTO categoria = categoriaController.criar(dto, UriComponentsBuilder.newInstance()).getBody();
		
		Assertions.assertThat(categoria).isNotNull();
		Assertions.assertThat(categoria.getId()).isNotNull();
		Assertions.assertThat(categoria.getId()).isEqualTo(categoriaSalva.getId());
		Assertions.assertThat(categoria.getNome()).isNotNull();
		Assertions.assertThat(categoria.getNome()).isEqualTo(categoriaSalva.getNome());
	}

	@Test
	void deveRetornarCategoriaAposAtualizar() {
		CategoriaDTO categoria = categoriaController.atualizar(1L, atualizaCategoriaDTO).getBody();
		
		Assertions.assertThat(categoria).isNotNull();
		Assertions.assertThat(categoria.getId()).isNotNull();
		Assertions.assertThat(categoria.getId()).isEqualTo(categoriaSalva.getId());
		Assertions.assertThat(categoria.getNome()).isNotNull();
		Assertions.assertThat(categoria.getNome()).isEqualTo(categoriaSalva.getNome());
	}

	@Test
	void deveRetornarNadaAposExcluir() {
		ResponseEntity<?> resposta = categoriaController.excluir(1L);
		
		Assertions.assertThat(resposta).isNotNull();
		Assertions.assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		Assertions.assertThat(resposta.getBody()).isNull();
	}
}
