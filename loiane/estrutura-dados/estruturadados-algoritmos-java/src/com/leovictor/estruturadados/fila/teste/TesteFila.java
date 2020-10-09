package com.leovictor.estruturadados.fila.teste;

import com.leovictor.estruturadados.fila.Fila;

public class TesteFila {
	public static void main(String[] args) {
		Fila<Integer> fila = new Fila<Integer>();
		
		System.out.println(fila.estaVazia());
		System.out.println(fila);
		System.out.println(fila.espia());
		fila.enfileira(1);
		fila.enfileira(11);
		System.out.println(fila.estaVazia());
		System.out.println(fila);
		System.out.println(fila.espia());
		System.out.println(fila.desenfileira());
		System.out.println(fila);
		System.out.println(fila.desenfileira());
		System.out.println(fila);
		System.out.println(fila.estaVazia());
	}
}
