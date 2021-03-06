package com.leovictor.estruturadados.pilha.teste;

import com.leovictor.estruturadados.pilha.Pilha;

public class ConversorBase {

	public static void main(String[] args) {
		imprimeConversao(31, 2);
		imprimeConversao(31, 8);
		imprimeConversao(31, 16);
		imprimeConversao(256, 2);
		imprimeConversao(256, 8);
		imprimeConversao(256, 16);
	}
	
	public static void imprimeConversao(int decimal, int base) {
		if (base > 16) {
			System.out.println("A maior base permitida é 16");
		} else {
			System.out.println(decimal + " na base " + base + " = " + converte(decimal, base));			
		}
	}
	
	public static String converte(int decimal, int base) {
		Pilha<Integer> pilha = new Pilha<Integer>();
		StringBuilder sb = new StringBuilder();
		final String valores = "0123456789ABCDEF";
		
		while (decimal > 0) {
			pilha.empilha(decimal % base);
			decimal /= base;
		}
		
		while (!pilha.estaVazia()) {
			sb.append(valores.charAt(pilha.desempilha()));
		}
		
		return sb.toString();
	}

}
