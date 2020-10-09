package com.leovictor.cursojava.aula52;

public class ContatoNaoExisteException extends Exception {

	private String nomeContato;

	public ContatoNaoExisteException(String nome) {
		this.nomeContato = nome;
	}

	@Override
	public String getMessage() {
		return nomeContato + " - Contato não encontrado";
	}

}
