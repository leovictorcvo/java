package br.com.leovictor.financeiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.leovictor.financeiro.domain.Usuario;
import br.com.leovictor.financeiro.repository.UsuarioRepository;

@Service
public class AuthService implements UserDetailsService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optional = usuarioRepository.findByEmail(username);
		if (optional.isPresent()) {
			return optional.get();
		}		
		throw new UsernameNotFoundException("Dados inválidos");
	}
}
