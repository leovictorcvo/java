package br.com.leovictor.financeiro.integrationTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.leovictor.financeiro.controller.LoginDTO;
import br.com.leovictor.financeiro.dto.TokenDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(br.com.leovictor.financeiro.integrationTest.AuthenticationUtil.class)
public class AuthControllerTest {
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private AuthenticationUtil util;
	
	@BeforeEach
	public void setUp() {
		util.ConfiguraMocks();
	}
	
	@Test
	void deveFazerLoginDeUsuarioCadastrado() {
		LoginDTO login = new LoginDTO();
		login.setEmail(util.usuarioAutenticado.getEmail());
		login.setSenha("123456");
		HttpEntity<LoginDTO> httpEntity = util.createJsonHttpEntity(login);

		ResponseEntity<TokenDTO> responseEntity = testRestTemplate.postForEntity("/auth", httpEntity, TokenDTO.class);
		
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
	}

	@Test
	void naoDeveFazerLoginDeUsuarioNaoCadastrado() {
		LoginDTO login = new LoginDTO();
		login.setEmail(util.usuarioInvalido.getEmail());
		login.setSenha("123456");
		HttpEntity<LoginDTO> httpEntity = util.createJsonHttpEntity(login);

		ResponseEntity<TokenDTO> responseEntity = testRestTemplate.postForEntity("/auth", httpEntity, TokenDTO.class);
		
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isNull();
	}
}
