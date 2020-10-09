package com.leovictor.cursojava.aula12;

import java.util.Scanner;

public class LeituraDadosTeclado {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Digite seu nome: ");
		String nomeCompleto = scan.nextLine();
		
		System.out.print("Digite sua idade: ");
		int idade = scan.nextInt();
		
		System.out.println("Olá " + nomeCompleto + ", você tem " + idade + " anos.");
		
		System.out.println("Digite seu primeiro nome, idade, quantidade de filhos, altura e se tem bicho de estimação: ");
		String primeiroNome = scan.next();
		int idade2 = scan.nextInt();
		byte qtdFilhos = scan.nextByte();
		float altura = scan.nextFloat();
		boolean temPet = scan.nextBoolean();
	
		System.out.println("Primeiro nome: " + primeiroNome);
		System.out.println("Idade: " + idade2);
		System.out.println("Filhos: " + qtdFilhos);
		System.out.println("Altura: " + altura);
		System.out.println("Tem pet?:" + temPet);
		
		scan.close();
	}

}
