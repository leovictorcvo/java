package com.leovictor.estruturadados.pilha.teste;

import com.leovictor.estruturadados.pilha.Pilha;

public class Palindromo {

	public static void main(String[] args) {
		imprimeSeEPalindromo("Ada");
		imprimeSeEPalindromo("Abcd");
		imprimeSeEPalindromo("ABCCBA");
		imprimeSeEPalindromo("abba");
		imprimeSeEPalindromo("em me");
		imprimeSeEPalindromo("Java");
	}
	
	public static void imprimeSeEPalindromo(String palavra) {
		System.out.println(palavra + " é palindromo? " + testaPalindromo(palavra));
	}
	
	public static boolean testaPalindromo(String palavra) {
		Pilha<Character> pilha = new Pilha<Character>();
		
		for(int i = 0; i < palavra.length(); i++) {
			pilha.empilha(palavra.charAt(i));
		}
		
		StringBuilder sb = new StringBuilder();
		while (!pilha.estaVazia()) {
			sb.append(pilha.desempilha());
		}
		
		return sb.toString().equalsIgnoreCase(palavra);
	}

}
