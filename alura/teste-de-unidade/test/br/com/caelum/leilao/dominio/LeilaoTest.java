package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.testDataBuilder.ConstrutorDeLeilao;

public class LeilaoTest {
	private Usuario joao;
	private Usuario maria;

	@Before
	public void setup() {
		joao = new Usuario("João");
		maria = new Usuario("Maria");
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAceitarLanceComValorZero() {
		Leilao leilao = new Leilao("Notebook I7");
		leilao.propoe(new Lance(joao, 0.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAceitarLanceComValorNegativo() {
		Leilao leilao = new Leilao("Notebook I7");
		leilao.propoe(new Lance(joao, -10.0));
	}

	@Test
	public void naoDeveAceitarDoisLancesEmSequenciaDoMesmoUsuario() {
		Leilao leilao = new ConstrutorDeLeilao().para("Notebook I7").comLance(new Lance(joao, 100.0))
				.comLance(new Lance(joao, 200.0)).constroi();

		assertEquals(1, leilao.getLances().size());
		assertEquals(100.0, leilao.getLances().get(0).getValor(), 0.000001);
	}

	@Test
	public void naoDeveAceitarMaisQueCincoLancesDoMesmoUsuario() {
		Leilao leilao = new ConstrutorDeLeilao().para("Notebook I7").comLance(new Lance(joao, 100.0))
				.comLance(new Lance(maria, 200.0)).comLance(new Lance(joao, 300.0)).comLance(new Lance(maria, 400.0))
				.comLance(new Lance(joao, 500.0)).comLance(new Lance(maria, 600.0)).comLance(new Lance(joao, 700.0))
				.comLance(new Lance(maria, 800.0)).comLance(new Lance(joao, 900.0)).comLance(new Lance(maria, 1000.0))
				.comLance(new Lance(joao, 1100.0)).constroi();

		assertEquals(10, leilao.getLances().size());
		assertEquals(1000.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.000001);
	}

	@Test
	public void podeIncluirUmLanceComValorDobradoDoUltimoLanceDoUsuario() {
		Leilao leilao = new ConstrutorDeLeilao().para("Notebook I7").comLance(new Lance(joao, 100.0))
				.comLance(new Lance(maria, 200.0)).dobraLance(joao).constroi();

		assertEquals(3, leilao.getLances().size());
		assertEquals(200.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.000001);
	}

	@Test
	public void deveIgnorarDobrarOValorSeNãoTiverLanceDoUsuario() {
		Leilao leilao = new ConstrutorDeLeilao().para("Notebook I7").comLance(new Lance(joao, 100.0)).dobraLance(maria)
				.constroi();

		assertEquals(1, leilao.getLances().size());
		assertEquals(100.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.000001);
	}

	@Test
	public void naoPodeDobrarSeUltimoLanceForDoUsuario() {
		Leilao leilao = new ConstrutorDeLeilao().para("Notebook I7").comLance(new Lance(joao, 100.0)).dobraLance(joao)
				.constroi();

		assertEquals(1, leilao.getLances().size());
		assertEquals(100.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.000001);
	}

	@Test
	public void naoPodeDobrarSeUsuarioJaDeu5Lances() {
		Leilao leilao = new ConstrutorDeLeilao().para("Notebook I7").comLance(new Lance(joao, 100.0))
				.comLance(new Lance(maria, 200.0)).comLance(new Lance(joao, 300.0)).comLance(new Lance(maria, 400.0))
				.comLance(new Lance(joao, 500.0)).comLance(new Lance(maria, 600.0)).comLance(new Lance(joao, 700.0))
				.comLance(new Lance(maria, 800.0)).comLance(new Lance(joao, 900.0)).comLance(new Lance(maria, 1000.0))
				.dobraLance(joao).constroi();

		assertEquals(10, leilao.getLances().size());
		assertEquals(1000.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.000001);
	}
}
