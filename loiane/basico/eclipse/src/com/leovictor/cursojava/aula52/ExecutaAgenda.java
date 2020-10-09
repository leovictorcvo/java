package com.leovictor.cursojava.aula52;

import java.util.Scanner;

public class ExecutaAgenda {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Agenda agenda = new Agenda();
		
		int opcaoSelecionada = mostrarMenu(scan);

		while (opcaoSelecionada < 3) {
			if (opcaoSelecionada == 1) {
				criarContato(scan, agenda);
			} else {
				consultarContato(scan, agenda);
			}
			
			opcaoSelecionada = mostrarMenu(scan);
		}

	}

	private static void criarContato(Scanner scan, Agenda agenda) {
		try {
			Contato contato = new Contato();
			contato.setNome(obterTexto("Nome", scan));
			contato.setTelefone(obterTexto("Telefone:", scan));
			agenda.adicionarContato(contato);
		} catch (AgendaCheiaException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		}
	}
	
	private static void consultarContato(Scanner scan, Agenda agenda) {
		try {
			String nomeContato = obterTexto("Nome do contato para pesquisar", scan);
			Contato contato = agenda.consultaContato(nomeContato);
			System.out.println("Contato encontrado - " + contato);
		} catch (ContatoNaoExisteException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		}
	}
	
	private static int mostrarMenu(Scanner scan) {
		int opcaoSelecionada = 0;

		do {
			System.out.println("Digite a opção desejada:");
			System.out.println();
			System.out.println("1 - Adicionar contato");
			System.out.println("2 - Consultar contato");
			System.out.println("3 - Sair");

			try {
				String entrada = scan.nextLine();
				opcaoSelecionada = Integer.parseInt(entrada);
				if (opcaoSelecionada < 1 || opcaoSelecionada > 3) {
					throw new Exception();
				}

			} catch (Exception e) {
				System.out.println();
				System.out.println("Opção inválida. Tente novamente.");
				System.out.println();
				opcaoSelecionada = 0;
			}
		} while (opcaoSelecionada < 1 || opcaoSelecionada > 3);
		return opcaoSelecionada;
	}

	private static String obterTexto(String label, Scanner scan) {
		boolean entradaValida = false;
		String valor;
		do {
			System.out.println(label);
			valor = scan.nextLine();
			entradaValida = (valor != null && valor.length() > 0);
		} while (!entradaValida);
		return valor;
	}
}
