package com.leovictor.estruturadados.vetor;

import com.leovictor.estruturadados.base.EstruturaEstatica;

public class Vetor<T> extends EstruturaEstatica<T>{

	public Vetor() {
		super(10);
	}
	
	public Vetor(int capacidade) {
		super(capacidade);
	}

	public Vetor(int capacidade, Class<T> tipoClasse) {
		super(capacidade, tipoClasse);
	}

	public void adiciona(T item) {
		super.adiciona(item);
	}

	public void adiciona(T item, int posicao) throws IllegalArgumentException {
		super.adiciona(item, posicao);
	}

	public T busca(int posicao) throws IllegalArgumentException {
		super.verificaPosicaoValida(posicao);
		return this.elementos[posicao];
	}

	public int buscaIndice(T item) {
		for (int i = 0; i < this.tamanho; i++) {
			if (this.elementos[i].equals(item)) {
				return i;
			}
		}
		return -1;
	}

	public boolean contem(T elemento) {
		return this.buscaIndice(elemento) > -1;
	}
	
	public void remove(int posicao) {
		super.remove(posicao);
	}

	public void remove(T elemento) {
		int posicao = this.buscaIndice(elemento);
		if (posicao == -1) {
			throw new IllegalArgumentException("Elemento não encontrado no Vetor");
		}
		this.remove(posicao);
	}

	public void removeTudo() {
		super.removeTudo();
	}
	

	public int ultimoIndice(T item) {
		for(int i = this.tamanho - 1; i >= 0; i--) {
			if (this.elementos[i].equals(item)) {
				return i;
			}
		}
		return -1;
	}

}
