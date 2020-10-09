package com.leovictor.cursojava.classesMetodos;

import java.util.Scanner;

public class ExecutaAgenda {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entre com o nome da agenda");
		String nome = scan.nextLine();
		
		Agenda agenda = new Agenda(nome);
		
		int qtdeContatos = 3;
		
		Contato[] contatos = new Contato[qtdeContatos];
		
		for(int i = 0; i < qtdeContatos; i++) {
			System.out.println("Entre com as informações do contato " + (i + 1));
			Contato contato = new Contato();
			System.out.print("Nome: ");
			contato.setNome(scan.nextLine());
			System.out.print("Email: ");
			contato.setEmail(scan.nextLine());
			System.out.print("Telefone: ");
			contato.setTelefone(scan.nextLine());
			contatos[i] = contato;
		}
		agenda.setContatos(contatos);

		System.out.println();
		System.out.println(agenda.obterInfo());

	}

}
