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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.leovictor.financeiro.domain.Categoria;
import br.com.leovictor.financeiro.domain.Lancamento;
import br.com.leovictor.financeiro.domain.Usuario;
import br.com.leovictor.financeiro.dto.AtualizaLancamentoDTO;
import br.com.leovictor.financeiro.dto.CriaLancamentoDTO;
import br.com.leovictor.financeiro.dto.LancamentoDTO;
import br.com.leovictor.financeiro.repository.CategoriaRepository;
import br.com.leovictor.financeiro.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public Page<LancamentoDTO> listar(@PageableDefault(page = 0, size = 5, sort = "data", direction = Direction.DESC) Pageable paginacao) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = (Usuario)authentication.getPrincipal();

		return LancamentoDTO.converter(lancamentoRepository.findByUsuarioId(usuario.getId(), paginacao));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@RequestBody @Valid CriaLancamentoDTO criaLancamentoDTO,
			UriComponentsBuilder uriBuilder) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = (Usuario)authentication.getPrincipal();

		Optional<Categoria> categoria = categoriaRepository.findById(criaLancamentoDTO.getCategoriaId());
		if (categoria.isEmpty()) {
			return ResponseEntity.badRequest().body("Código de categoria inválido");
		}
		
		Lancamento salvo = lancamentoRepository.save(criaLancamentoDTO.converter(usuario, categoria.get()));
		URI uri = uriBuilder.path("/lancamentos/{id}").buildAndExpand(salvo.getId()).toUri();
		return ResponseEntity.created(uri).body(new LancamentoDTO(salvo));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaLancamentoDTO atualizaLancamentoDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = (Usuario)authentication.getPrincipal();

		Optional<Categoria> categoria = categoriaRepository.findById(atualizaLancamentoDTO.getCategoriaId());
		if (categoria.isEmpty()) {
			return ResponseEntity.badRequest().body("Código de categoria inválido");
		}
		
		Optional<Lancamento> salvo = lancamentoRepository.findById(id);
		if (salvo.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Lancamento lancamento = salvo.get();
		
		if (lancamento.getUsuario().getId() != usuario.getId()) {
			return ResponseEntity.badRequest().body("Você não pode atualizar lançamento de outra pessoa");
		}
		
		lancamento.setDescricao(atualizaLancamentoDTO.getDescricao());
		lancamento.setCategoria(categoria.get());
		lancamento.setValor(atualizaLancamentoDTO.getValor());

		return ResponseEntity.ok(new LancamentoDTO(lancamento));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
		if (lancamento.isPresent()) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Usuario usuario = (Usuario)authentication.getPrincipal();

			if (lancamento.get().getUsuario().getId() != usuario.getId()) {
				return ResponseEntity.badRequest().body("Você não pode excluir lançamento de outra pessoa");
			}

			lancamentoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
