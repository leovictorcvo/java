package com.leovictor.estruturadados.pilha.teste;

import com.leovictor.estruturadados.pilha.Pilha;

public class TestePilha {

	public static void main(String[] args) {
		Pilha<Integer> pilha = new Pilha<Integer>();

		System.out.println(pilha.estaVazia());
		System.out.println(pilha);
		System.out.println(pilha.topo());
		pilha.empilha(1);
		pilha.empilha(11);
		System.out.println(pilha.estaVazia());
		System.out.println(pilha);
		System.out.println(pilha.topo());
		System.out.println(pilha.estaVazia());
		System.out.println(pilha.desempilha());
		System.out.println(pilha);
		System.out.println(pilha.desempilha());
		System.out.println(pilha);
		System.out.println(pilha.estaVazia());
	}

}
