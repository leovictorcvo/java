package br.com.leovictor.financeiro.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.leovictor.financeiro.domain.Usuario;
import br.com.leovictor.financeiro.repository.UsuarioRepository;
import br.com.leovictor.financeiro.service.TokenService;

public class AuthUsingTokenFilter extends OncePerRequestFilter {
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AuthUsingTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarTokenDoCabecalho(request);
		boolean tokenValido = tokenService.validaToken(token);
		if (tokenValido) {
			autenticarUsuario(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticarUsuario(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
		if (optional.isPresent()) {
			Usuario usuario = optional.get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
	}

	private String recuperarTokenDoCabecalho(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.split(" ")[1];
	}

}
