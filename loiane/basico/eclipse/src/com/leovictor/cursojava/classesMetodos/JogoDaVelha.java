package com.leovictor.cursojava.classesMetodos;

public class JogoDaVelha {
	char[][] jogoVelha;
	int jogada;

	public JogoDaVelha() {
		jogoVelha = new char[3][3];
		jogada = 1;
	}

	public boolean validarJogada(int linha, int coluna, char sinal) {
		if (jogoVelha[linha][coluna] == 0) {
			jogoVelha[linha][coluna] = sinal;
			jogada++;
			return true;
		}

		return false;
	}

	public void imprimirTabuleiro() {
		System.out.println();
		for (char[] linha : jogoVelha) {
			for (char coluna : linha) {
				if (coluna == 0) {
					System.out.print("  | ");
				} else {
					System.out.print(coluna + " | ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public boolean verificarSeGanhou(char sinal) {
		return ((jogoVelha[0][0] == sinal && jogoVelha[0][1] == sinal && jogoVelha[0][2] == sinal)
				|| (jogoVelha[1][0] == sinal && jogoVelha[1][1] == sinal && jogoVelha[1][2] == sinal)
				|| (jogoVelha[2][0] == sinal && jogoVelha[2][1] == sinal && jogoVelha[2][2] == sinal)
				|| (jogoVelha[0][0] == sinal && jogoVelha[1][0] == sinal && jogoVelha[2][0] == sinal)
				|| (jogoVelha[0][1] == sinal && jogoVelha[1][1] == sinal && jogoVelha[2][1] == sinal)
				|| (jogoVelha[0][2] == sinal && jogoVelha[1][2] == sinal && jogoVelha[2][2] == sinal)
				|| (jogoVelha[0][0] == sinal && jogoVelha[1][1] == sinal && jogoVelha[2][2] == sinal)
				|| (jogoVelha[0][2] == sinal && jogoVelha[1][1] == sinal && jogoVelha[2][0] == sinal));
	}

	public boolean vezJogador1() {
		return (jogada % 2 == 1);
	}

	public boolean empatou() {
		return jogada > 9;
	}
}
