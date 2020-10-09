package com.leovictor.estruturadados.vetor.teste;

import com.leovictor.estruturadados.vetor.Vetor;

public class TesteVetor {

	public static void main(String[] args) {
		Vetor<String> vetor = new Vetor(2);
		Vetor<Integer> vetorInteiros = new Vetor(2);
		
		try {
			vetor.adiciona("Primeiro Item");
			vetor.adiciona("Segundo Item");
			vetor.adiciona("Terceiro Item");
			System.out.println(vetor.tamanho());
			System.out.println(vetor);
			System.out.println(vetor.busca(1));
			System.out.println(vetor.buscaIndice("Terceiro Item"));
			System.out.println(vetor.buscaIndice("TERCEIRO ITEM"));
			vetor.adiciona("Elemento zero", 0);
			System.out.println(vetor.tamanho());
			System.out.println(vetor);
			vetor.remove(0);
			System.out.println(vetor.tamanho());
			System.out.println(vetor);			
			vetor.remove(1);
			System.out.println(vetor.tamanho());
			System.out.println(vetor);	
			vetor.remove(1);
			System.out.println(vetor.tamanho());
			System.out.println(vetor);
			vetor.adiciona("Novo item");
			System.out.println(vetor.tamanho());
			System.out.println(vetor);	
			vetor.remove("Primeiro Item");
			System.out.println(vetor.tamanho());
			System.out.println(vetor);	
			vetor.remove("Novo item");
			System.out.println(vetor.tamanho());
			System.out.println(vetor);
			
			vetorInteiros.adiciona(1);
			vetorInteiros.adiciona(2);
			System.out.println(vetorInteiros);
			System.out.println(vetorInteiros.contem(2));
			System.out.println(vetorInteiros.contem(3));
			vetorInteiros.adiciona(1);
			vetorInteiros.adiciona(1);
			System.out.println(vetorInteiros);
			System.out.println(vetorInteiros.buscaIndice(1));
			System.out.println(vetorInteiros.ultimoIndice(1));
			System.out.println(vetorInteiros.ultimoIndice(2));
			System.out.println(vetorInteiros.ultimoIndice(3));
			vetorInteiros.removeTudo();
			System.out.println(vetorInteiros);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
