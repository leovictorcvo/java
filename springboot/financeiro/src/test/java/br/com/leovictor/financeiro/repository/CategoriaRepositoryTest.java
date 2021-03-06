package br.com.leovictor.financeiro.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import br.com.leovictor.financeiro.domain.Categoria;

@DataJpaTest
@DisplayName("Teste do repositório de categorias")
class CategoriaRepositoryTest {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Test
	void deveCriarUmaCategoriaComSucesso() {
		Categoria nova = new Categoria("Teste");
		
		Categoria salva = categoriaRepository.save(nova);
		
		assertThat(salva.getId()).isNotNull();
		assertThat(salva.getNome()).isNotNull();
		assertThat(salva.getNome()).isEqualTo(nova.getNome());
	}

	@Test
	void deveGerarUmExcecaoAoSalvarUmaCategoriaSemNome() {
		Categoria nova = new Categoria("");

		assertThatThrownBy(() -> categoriaRepository.save(nova)).isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void deveGerarUmExcecaoAoSalvarUmaCategoriaOndeAlterouONomeParaNull() {
		Categoria nova = new Categoria("teste");
		nova.setNome(null);

		assertThatThrownBy(() -> categoriaRepository.save(nova)).isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void deveAtualizarUmaCategoriaComSucesso() {
		Categoria nova = new Categoria("Teste");
		
		Categoria salva = categoriaRepository.save(nova);
		salva.setNome("atualizada");
		
		Categoria atualizada = categoriaRepository.save(salva);
		
		assertThat(atualizada.getId()).isNotNull();
		assertThat(atualizada.getNome()).isNotNull();
		assertThat(atualizada.getId()).isEqualTo(salva.getId());
		assertThat(atualizada.getNome()).isEqualTo(salva.getNome());
	}

	@Test
	void deveGerarUmaExcecaoAoAtualizarUmaCategoriaSemNome() {
		Categoria nova = new Categoria("Teste");
		
		Categoria salva = categoriaRepository.saveAndFlush(nova);
		salva.setNome(null);
		
		assertThatThrownBy(() -> categoriaRepository.saveAndFlush(salva)).isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	void deveExcluirUmaCategoriaComSucesso() {
		Categoria nova = new Categoria("Teste");
		Categoria salva = categoriaRepository.save(nova);

		categoriaRepository.deleteById(salva.getId());
		Optional<Categoria> optional = categoriaRepository.findById(salva.getId());
		
		assertThat(optional.isEmpty()).isTrue();
	}

	@Test
	void deveEncontrarCategoriasPeloNomeComSucesso() {
		Categoria teste = new Categoria("Teste");
		Categoria teste2 = new Categoria("Segundo teste");
		Categoria teste3 = new Categoria("Não deve ser encontrada");
		categoriaRepository.saveAll(List.of(teste, teste2, teste3));

		Page<Categoria> encontrados = categoriaRepository.findByNomeIgnoreCase("teste", null);

		assertThat(encontrados.getContent().size()).isEqualTo(1);
	}
}
