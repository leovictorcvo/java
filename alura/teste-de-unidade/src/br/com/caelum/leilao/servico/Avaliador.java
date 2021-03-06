package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	private Double menorLance = Double.POSITIVE_INFINITY;
	private Double maiorLance = Double.NEGATIVE_INFINITY;
	private Double media = 0.0;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao) {
		if (leilao.getLances().size() == 0) {
			throw new RuntimeException("Não é possível avaliar um leilão sem lances.");
		}
		
		Double total = 0.0;
		for (Lance lance : leilao.getLances()) {
			total += lance.getValor();
			if (lance.getValor() > maiorLance) maiorLance = lance.getValor();
			if (lance.getValor() < menorLance) menorLance = lance.getValor();
		}
		
		media = leilao.getLances().size() == 0 ? 0 : total / leilao.getLances().size();
		pegaOsMaiores(leilao.getLances());
	}

	private void pegaOsMaiores(List<Lance> lances) {
		ArrayList<Lance> lista = new ArrayList<Lance>(lances);
		Collections.sort(lista, (o1, o2) -> Double.compare(o2.getValor(), o1.getValor()));
		maiores = lista.subList(0, lista.size() > 3 ? 3 : lista.size());		
	}

	public Double getMenorLance() {
		return menorLance;
	}

	public Double getMaiorLance() {
		return maiorLance;
	}
	
	public Double getMedia() {
		return media;
	}
	
	public List<Lance> getMaiores() {
		return maiores;
	}
}
