package com.leovictor.cursojava.classesMetodos;

public class Lampada {
	String modelo;
	String tensao;
	int potenciaEmWatts;
	String cor;
	String tipoDeLuz;
	int garantiaEmMeses;
	String[] tipos;
	boolean ligada;

	void ligar() {
		ligada = true;
	}

	void desligar() {
		ligada = false;
	}

	void mostrarEstado() {
		if (ligada) {
			System.out.println("A lampada está ligada");
		} else {
			System.out.println("A lampada está desligada");
		}
	}
	
	void mudarEstado() {
		if (ligada) {
			desligar();
		} else {
			ligar();
		}
	}
}
