package com.leovictor.cursojava.classesMetodos;

public class FibonacciRecursivo {

	static int tamanhoCache = 30;
	
	static int[] cache = new int[tamanhoCache];
	
	public static int fibonacci(int num) {
		if (num < 2) {
			return 1;
		}
		
		if (num < tamanhoCache && cache[num] > 0) {
			return cache[num];
		}
		
		cache[num] = fibonacci(num - 1) + fibonacci(num - 2); 
		return cache[num];
	}
}
