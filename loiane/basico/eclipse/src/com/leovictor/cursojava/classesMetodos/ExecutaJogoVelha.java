package com.leovictor.cursojava.classesMetodos;

import java.util.Scanner;

public class ExecutaJogoVelha {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		JogoDaVelha jogoDaVelha = new JogoDaVelha();
		
		System.out.println("Jogodor 1 = X");
		System.out.println("Jogodor 2 = O");
		
		boolean ganhou = false;
		char sinal;
		int linha = 0, coluna = 0;
		
		while (!ganhou) {
			if (jogoDaVelha.vezJogador1()) {
				System.out.println("Vez do jogador 1! Escolha a linha e coluna");
				sinal = 'X';
			} else{
				System.out.println("Vez do jogador 2! Escolha a linha e coluna");
				sinal = 'O';
			} 
			
			linha = valor("Linha", scan);
			coluna = valor("Coluna", scan);
			
			if (!jogoDaVelha.validarJogada(linha, coluna, sinal)) {
				System.out.println("Posição já utilizada. Tente novamente.");
			}
			
			jogoDaVelha.imprimirTabuleiro();
			
			if (jogoDaVelha.verificarSeGanhou('X')) {
				ganhou = true;
				System.out.println("O jogador 1 ganhou! Parabéns!");
			} else if (jogoDaVelha.verificarSeGanhou('O')) {
				ganhou = true;
				System.out.println("O jogador 2 ganhou! Parabéns!");
			} else if (jogoDaVelha.empatou()) {
				ganhou = true;
				System.out.println("O jogo terminou empatado!");
			}
		}
	}

	private static int valor(String tipoValor, Scanner scan) {
		int valor = 0;
		boolean valorValido = false;
		while(!valorValido) {
			System.out.print("Entre com a " + tipoValor + " (1, 2 ou 3): ");
			valor = scan.nextInt();
			if (valor > 0 && valor < 4) {
				valorValido = true;
			} else {
				System.out.println(valor + "não é uma entrada válida!");
			}
		}
		return --valor;
	}
}
