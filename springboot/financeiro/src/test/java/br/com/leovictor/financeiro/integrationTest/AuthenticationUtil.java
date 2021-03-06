package br.com.leovictor.financeiro.integrationTest;

import java.util.List;
import java.util.Optional;

import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.leovictor.financeiro.controller.LoginDTO;
import br.com.leovictor.financeiro.domain.CPF;
import br.com.leovictor.financeiro.domain.Perfil;
import br.com.leovictor.financeiro.domain.Usuario;
import br.com.leovictor.financeiro.dto.TokenDTO;
import br.com.leovictor.financeiro.repository.UsuarioRepository;

@Component
public class AuthenticationUtil {
	@Autowired
	private TestRestTemplate testRestTemplate;

	@MockBean 
	private UsuarioRepository usuarioRepository;
	
	public final Usuario adminAutenticado = new Usuario(1L, "teste", new CPF("55761387085"), "admin@teste.com", "$2a$10$vtOst9bmafORf11rD3Hb8./7avk0.0O0UOWzJHBtawoGID.E1PIwm", List.of(new Perfil(1L, "ROLE_ADMIN")));
	public final Usuario usuarioAutenticado = new Usuario(2L, "teste", new CPF("55761387085"), "usuario@teste.com", "$2a$10$vtOst9bmafORf11rD3Hb8./7avk0.0O0UOWzJHBtawoGID.E1PIwm", List.of(new Perfil(2L, "ROLE_USER")));
	public final Usuario usuarioInvalido = new Usuario(3L, "invalido", new CPF("55761387085"), "invalido@teste.com", "$2a$10$vtOst9bmafORf11rD3Hb8./7avk0.0O0UOWzJHBtawoGID.E1PIwm", null);

	public void ConfiguraMocks() {
		BDDMockito.when(usuarioRepository.findByEmail(adminAutenticado.getEmail())).thenReturn(Optional.of(adminAutenticado));
		BDDMockito.when(usuarioRepository.findById(adminAutenticado.getId())).thenReturn(Optional.of(adminAutenticado));
		BDDMockito.when(usuarioRepository.findByEmail(usuarioAutenticado.getEmail())).thenReturn(Optional.of(usuarioAutenticado));
		BDDMockito.when(usuarioRepository.findById(usuarioAutenticado.getId())).thenReturn(Optional.of(usuarioAutenticado));
	}
	
	private HttpHeaders createJsonHeaders(String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		if (token != null) {
			httpHeaders.setBearerAuth(token);
		}
		return httpHeaders; 
	}
	
	public <T> HttpEntity<T> createJsonHttpEntity(T entity) {
		return new HttpEntity<>(entity, createJsonHeaders(null));
	}

	public <T> HttpEntity<T> createAuthenticatedJsonHttpEntity(T entity, boolean paraAdministrador) {
		String token = getToken(paraAdministrador ? adminAutenticado.getEmail() : usuarioAutenticado.getEmail());
		return new HttpEntity<>(entity, createJsonHeaders(token));
	}

	private String getToken(String email) {
		String token = null;
		try {
			LoginDTO dto = new LoginDTO();
			dto.setEmail(email);
			dto.setSenha("123456");
			HttpEntity<LoginDTO> httpEntity = createJsonHttpEntity(dto);
			ResponseEntity<TokenDTO> responseEntity = testRestTemplate.postForEntity("/auth", httpEntity, TokenDTO.class);
			token = responseEntity.getBody().getToken();
		} catch (Exception e) {
			System.out.println("*********************************************");
			System.out.println("Erro");
			e.printStackTrace();
			System.out.println("*********************************************");
		}
		return token;
	}
	


}
