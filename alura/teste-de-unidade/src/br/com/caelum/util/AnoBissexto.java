package br.com.caelum.util;

public class AnoBissexto {
	public static boolean isAnoBissexto(int ano) {
		return (ano % 400 == 0 || (ano % 100 != 0 && ano % 4 == 0));
	}
}
