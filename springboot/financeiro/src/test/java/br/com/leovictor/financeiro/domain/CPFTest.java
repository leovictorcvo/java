package br.com.leovictor.financeiro.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CPFTest {

	@Test
	void deveAceitarCPFValidos() {
		CPF cpf1 = new CPF("31224480090");
		CPF cpf2 = new CPF("312.244.800-90");
		assert(cpf1.getValue()).equals("31224480090");
		assert(cpf2.getValue()).equals("31224480090");
	}
	
	@Test
	void deveRejeitarCPFInvalido() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new CPF("31224480091");			
		});
		
		String mensagemEsperada ="CPF inválido.";
		String mensagemRecebida = exception.getMessage();
		
		assertTrue(mensagemRecebida.contains(mensagemEsperada));
	}

}
