package br.com.leovictor.financeiro.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.leovictor.financeiro.domain.Categoria;
import br.com.leovictor.financeiro.dto.AtualizaCategoriaDTO;
import br.com.leovictor.financeiro.dto.CategoriaDTO;
import br.com.leovictor.financeiro.dto.CriaCategoriaDTO;
import br.com.leovictor.financeiro.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public Page<CategoriaDTO> listar(@RequestParam(required = false) String nomeCategoria,
			@PageableDefault(page = 0, size = 5, sort = "nome", direction = Direction.ASC) Pageable paginacao) {
		return CategoriaDTO.converter(nomeCategoria == null ? categoriaRepository.findAll(paginacao)
				: categoriaRepository.findByNomeIgnoreCase(nomeCategoria, paginacao));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> detalhar(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			return ResponseEntity.ok(new CategoriaDTO(categoria.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDTO> criar(@RequestBody @Valid CriaCategoriaDTO criarCategoriaDTO,
			UriComponentsBuilder uriBuilder) {
		Categoria salvo = categoriaRepository.save(criarCategoriaDTO.converter());
		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(salvo.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDTO(salvo));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizaCategoriaDTO atualizaCategoriaDTO) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			Categoria saved = atualizaCategoriaDTO.converter(categoriaRepository, id);
			return ResponseEntity.ok(new CategoriaDTO(saved));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
