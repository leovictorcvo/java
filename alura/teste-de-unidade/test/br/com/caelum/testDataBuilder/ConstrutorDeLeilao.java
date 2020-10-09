package br.com.caelum.testDataBuilder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class ConstrutorDeLeilao {

	private Leilao leilao;

	public ConstrutorDeLeilao para(String descricao) {
		leilao = new Leilao(descricao);
		return this;
	}
	
	public ConstrutorDeLeilao comLance(Lance lance) {
		leilao.propoe(lance);
		return this;
	}
	
	public ConstrutorDeLeilao dobraLance(Usuario usuario) {
		leilao.dobraProposta(usuario);
		return this;
	}
	
	public Leilao constroi() {
		return leilao;
	}
}
