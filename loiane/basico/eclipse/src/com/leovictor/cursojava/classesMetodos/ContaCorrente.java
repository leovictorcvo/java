package com.leovictor.cursojava.classesMetodos;

import java.text.NumberFormat;

public class ContaCorrente {
	String agencia;
	String numero;
	boolean especial;
	double limiteEspecial;
	double saldo;

	boolean sacar(double valor) {
		if (valor < saldo + limiteEspecial) {
			saldo -= valor;
			return true;
		}

		return false;
	}

	void depositar(double valor) {
		saldo += valor;
	}

	void consultarSaldo() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		System.out.println("O saldo da conta é: " + nf.format(saldo));
	}
	
	boolean verificarUsoChequeEspecial() {
		return saldo < 0;
	}
}
