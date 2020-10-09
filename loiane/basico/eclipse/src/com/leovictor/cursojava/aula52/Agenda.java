package com.leovictor.cursojava.aula52;

import java.util.Arrays;

public class Agenda {
	private static final int QTDE_CONTATOS = 5;
	private int contatoAtual = 0;

	private Contato[] contatos;

	public Agenda() {
		contatos = new Contato[QTDE_CONTATOS];
		contatoAtual = 0;
	}

	public void adicionarContato(Contato contato) throws AgendaCheiaException {
		if (contatoAtual < QTDE_CONTATOS) {
			contatos[contatoAtual++] = contato;
		}
		throw new AgendaCheiaException();
	}

	public Contato consultaContato(String nome) throws ContatoNaoExisteException {
		for (int i = 0; i < QTDE_CONTATOS; i++) {
			if (contatos[i] != null && contatos[i].getNome().equalsIgnoreCase(nome)) {
				return contatos[i];
			}
		}

		throw new ContatoNaoExisteException(nome);
	}

	@Override
	public String toString() {
		return "Agenda [contatos=" + Arrays.toString(contatos) + "]";
	}
	
	

}
