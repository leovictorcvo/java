package com.leovictor.estruturadados.fila;

import com.leovictor.estruturadados.base.EstruturaEstatica;

public class Fila<T> extends EstruturaEstatica<T> {
	public Fila() {
		super(10);
	}

	public Fila(int capacidade) {
		super(capacidade);
	}

	public T desenfileira() {
		if (super.estaVazia()) {
			return null;
		}

		T item = this.elementos[0];
		
		super.remove(0);
		
		return item;		
	}
	
	public void enfileira(T item) {
		super.adiciona(item);
	}

	public T espia() {
		if (super.estaVazia()) {
			return null;
		}

		return this.elementos[0];
	}
}
