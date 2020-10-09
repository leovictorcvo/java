package com.leovictor.estruturadados.vetor.teste;

import java.util.Scanner;

import com.leovictor.estruturadados.vetor.Vetor;

public class TesteContato {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int opcaoMenu = 0;

		Vetor<Contato> contatos = new Vetor<Contato>(20);
		criarContatosIniciais(20, contatos);

		while (opcaoMenu != 12) {
			opcaoMenu = mostraMenu(scan);
			switch (opcaoMenu) {
			case 1:
				adicionaContato(scan, contatos, false);
				break;
			case 2:
				adicionaContato(scan, contatos, true);
				break;
			case 3:
				consultaContatoPosicao(scan, contatos);
				break;
			case 4:
				consultaContato(scan, contatos, true);
				break;
			case 5:
				consultaContato(scan, contatos, false);
				break;
			case 6:
				verificaSeContatoEstaRegistrado(scan, contatos);
				break;
			case 7:
				removeContatoPorPosicao(scan, contatos);
				break;
			case 8:
				removeContato(scan, contatos);
				break;
			case 9:
				removeTodosContatos(contatos);
				break;
			case 10:
				System.out.println("Contatos registrados: " + contatos.tamanho());
				break;
			case 11:
				System.out.println(contatos.toString());
				break;
			case 12:
				System.out.println();
				System.out.println("************ Até mais ************");
				break;
			}
		}

		scan.close();
		scan = null;
	}

	private static void adicionaContato(Scanner scan, Vetor<Contato> contatos, boolean perguntaPorPosicao) {
		try {
			Contato contato = obterInformacoesContato(scan);
			if (perguntaPorPosicao) {
				int posicao = lePosicaoDesejada(scan, contatos.tamanho());
				contatos.adiciona(contato, posicao);
			} else {
				contatos.adiciona(contato);
			}
			System.out.println("Contato adicionado com sucesso.");
			System.out.println();

		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao adicionar o contato. Tente novamente.");
			System.out.println();
		}
	}

	private static void criarContatosIniciais(int quantidade, Vetor<Contato> contatos) {
		Contato contato;
		for (int i = 1; i <= quantidade; i++) {
			contato = new Contato();
			contato.setNome("Contato " + i);
			contato.setEmail("contato" + i + "@contato.com");

			contatos.adiciona(contato);
		}
	}

	private static void consultaContato(Scanner scan, Vetor<Contato> contatos, boolean primeiraOcorrencia) {
		Contato contato = obterInformacoesContato(scan);
		int posicao = -1;

		if (primeiraOcorrencia) {
			posicao = contatos.buscaIndice(contato);
		} else {
			posicao = contatos.ultimoIndice(contato);
		}

		System.out.println("");
		if (posicao > -1) {
			System.out.println("Contato encontrado na posição " + posicao);
		} else {
			System.out.println("Contato não encontrado");
		}
	}

	private static void consultaContatoPosicao(Scanner scan, Vetor<Contato> contatos) {
		int posicao = lePosicaoDesejada(scan, contatos.tamanho());
		Contato contato = contatos.busca(posicao);
		System.out.println();
		System.out.println(contato);
		System.out.println();
	}

	private static String leDadosDoTeclado(String label, Scanner scan) {
		System.out.println(label);
		String entrada = scan.nextLine();
		return entrada;
	}

	private static int leDadosInteiroDoTeclado(String label, Scanner scan) {
		boolean entradaInvalida = true;
		int valor = 0;
		String entrada;

		while (entradaInvalida) {
			try {
				System.out.println(label);
				entrada = scan.nextLine();
				valor = Integer.parseInt(entrada);
				entradaInvalida = false;
			} catch (Exception e) {
				System.out.println();
				System.out.println("Entrada inválida. Tente novamente.");
				System.out.println();
			}
		}
		return valor;
	}

	private static int lePosicaoDesejada(Scanner scan, int tamanhoVetor) {
		int posicao = -1;
		while (!posicaoValida(posicao, tamanhoVetor)) {
			posicao = leDadosInteiroDoTeclado("Posição desejada:", scan);
			if (!posicaoValida(posicao, tamanhoVetor)) {
				System.out.println();
				System.out.println("Posição inválida. Tente novamente.");
				System.out.println();
			}
		}
		return posicao;
	}

	private static int mostraMenu(Scanner scan) {
		int opcao = 0;

		while (!verificaSeOpcaoEValida(opcao)) {
			System.out.println();
			System.out.println("********* MENU *********");
			System.out.println("1 - Adiciona contato no final do vetor");
			System.out.println("2 - Adiciona contato em um posição específica");
			System.out.println("3 - Consulta o contato em um posição específica");
			System.out.println("4 - Obtem o índice da primeira ocorrência do contato");
			System.out.println("5 - Obtem o índice da última ocorrência do contato");
			System.out.println("6 - Verifica se o contato está registrado");
			System.out.println("7 - Remove por posição no vetor");
			System.out.println("8 - Remove o contato");
			System.out.println("9 - Remover todos os contatos");
			System.out.println("10 - Verifica o tamanho do vetor");
			System.out.println("11 - Mostra os contatos cadastrados");
			System.out.println("12 - Sair");

			System.out.println();
			opcao = leDadosInteiroDoTeclado("Informe a opção desejada:", scan);

			if (!verificaSeOpcaoEValida(opcao)) {
				System.out.println();
				System.out.println("Opção inválida. Tente novamente");
				System.out.println();
			}
		}

		return opcao;
	}

	private static Contato obterInformacoesContato(Scanner scan) {
		System.out.println();
		System.out.println("Criando um contato, entre com as informações");
		System.out.println();
		String nome = leDadosDoTeclado("Informe o nome do contato:", scan);
		String email = leDadosDoTeclado("Informe o email do contato:", scan);

		return new Contato(nome, email);
	}

	private static boolean posicaoValida(int posicao, int tamanho) {
		return posicao > -1 && posicao < tamanho;
	}

	private static void removeContatoPorPosicao(Scanner scan, Vetor<Contato> contatos) {
		int posicao = lePosicaoDesejada(scan, contatos.tamanho());
		contatos.remove(posicao);
		System.out.println();
		System.out.println("Contato excluido com sucesso.");
	}

	private static void removeContato(Scanner scan, Vetor<Contato> contatos) {
		Contato contato = obterInformacoesContato(scan);
		System.out.println();
		try {
			contatos.remove(contato);
			System.out.println("Contato excluido com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro ao excluir o contato. Verifique as informações fornecidas e tente novamente");
		}
	}

	private static void removeTodosContatos(Vetor<Contato> contatos) {
		contatos.removeTudo();
		System.out.println("Todos os contatos foram removidos");
	}

	private static void verificaSeContatoEstaRegistrado(Scanner scan, Vetor<Contato> contatos) {
		Contato contato = obterInformacoesContato(scan);
		System.out.println();
		if (contatos.contem(contato)) {
			System.out.println("Esse contato está registrado.");
		} else {
			System.out.println("Esse contato não foi encontrado.");
		}
	}

	private static boolean verificaSeOpcaoEValida(int opcao) {
		return opcao > 0 && opcao < 13;
	}

}
