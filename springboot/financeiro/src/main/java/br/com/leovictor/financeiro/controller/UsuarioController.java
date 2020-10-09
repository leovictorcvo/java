package br.com.leovictor.financeiro.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.leovictor.financeiro.domain.Usuario;
import br.com.leovictor.financeiro.dto.AtualizaUsuarioDTO;
import br.com.leovictor.financeiro.dto.CriaUsuarioDTO;
import br.com.leovictor.financeiro.dto.UsuarioDTO;
import br.com.leovictor.financeiro.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<UsuarioDTO> listar() {
		return Usuario.converter(usuarioRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> detalhar(@PathVariable Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new UsuarioDTO(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDTO> criar(@RequestBody @Valid CriaUsuarioDTO criaUsuarioDTO,
			UriComponentsBuilder uriBuilder) {
		Usuario salvo = usuarioRepository.save(criaUsuarioDTO.converter());
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(salvo.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(salvo));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaUsuarioDTO atualizaUsuarioDTO) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = atualizaUsuarioDTO.converter(id, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDTO(usuario));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			usuarioRepository.deleteById(id);
		}
		return ResponseEntity.noContent().build();
	}
}
