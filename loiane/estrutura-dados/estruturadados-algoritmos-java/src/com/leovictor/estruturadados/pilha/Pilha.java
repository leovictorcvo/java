package com.leovictor.estruturadados.pilha;

import com.leovictor.estruturadados.base.EstruturaEstatica;

public class Pilha<T> extends EstruturaEstatica<T> {
	public Pilha() {
		super(10);
	}

	public Pilha(int capacidade) {
		super(capacidade);
	}

	public void empilha(T item) {
		super.adiciona(item);
	}

	public T desempilha() {
		if (super.estaVazia()) {
			return null;
		}
		
		return this.elementos[--this.tamanho];
	}
	
	public T topo() {
		if (super.estaVazia()) {
			return null;
		}
		
		return this.elementos[this.tamanho - 1];
	}
}
