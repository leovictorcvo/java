package com.leovictor.cursojava.aula52;

public class AgendaCheiaException extends Exception {
	@Override
	public String getMessage() {
		return "Essa agenda já está cheia";
	}
}
