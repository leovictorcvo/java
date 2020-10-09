package com.leovictor.cursojava.classesMetodos;

public class ExecutaFibonacci {

	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			System.out.println("Fibonacci("+i+") = " + FibonacciRecursivo.fibonacci(i));
		}
	}

}
