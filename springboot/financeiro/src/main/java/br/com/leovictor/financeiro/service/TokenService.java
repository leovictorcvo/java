package br.com.leovictor.financeiro.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.leovictor.financeiro.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	@Value(value = "${financeiro.jwt.expiration}")
	private String expiration;

	@Value(value = "${financeiro.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date expiraEm = new Date(hoje.getTime() + Long.parseLong(expiration));

		return Jwts
				.builder()
				.setIssuer("API de Lançamentos financeiros")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(expiraEm)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean validaToken(String token) {
		try {
			Jwts
				.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts
				.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
		return Long.parseLong(claims.getSubject());
	}

}
