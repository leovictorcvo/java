package com.leovictor.estruturadados.pilha.teste;

import java.util.Stack;

public class Hanoi {

	public static void main(String[] args) {
		Stack<Integer> original = new Stack<Integer>();
		Stack<Integer> destino = new Stack<Integer>();
		Stack<Integer> aux = new Stack<Integer>();

		original.push(5);
		original.push(4);
		original.push(3);
		original.push(2);
		original.push(1);

		torreDeHanoi(original.size(), original, destino, aux);
	}

	public static void torreDeHanoi(
			int qtdeDiscos, 
			Stack<Integer> original, 
			Stack<Integer> destino, 
			Stack<Integer> aux) {
		if (qtdeDiscos > 0) {
			torreDeHanoi(qtdeDiscos - 1, original, aux, destino);
			destino.push(original.pop());
			System.out.println("********************************");
			System.out.println("Original = " + original);
			System.out.println("Destino = " + destino);
			System.out.println("Aux = " + aux);
			torreDeHanoi(qtdeDiscos - 1, aux, destino, original);
		}
	}
}
