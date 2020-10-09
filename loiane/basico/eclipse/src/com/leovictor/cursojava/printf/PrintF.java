package com.leovictor.cursojava.printf;

public class PrintF {

	public static void main(String[] args) {
		System.out.println("**Strings**");
		System.out.printf("Hello %s.%n", "World");
		System.out.printf("Hello %7s.%n", "World");
		System.out.printf("Hello %3s.%n", "World");
		
		System.out.println("**Inteiros**");
		System.out.printf("%d.%n", 1234);
		System.out.printf("%d.%n", -1234);
		System.out.printf("%+d.%n", 1234);
		System.out.printf("%+d.%n", -1234);
		System.out.printf("%10d.%n", 1234);
		System.out.printf("%10d.%n", -1234);
		System.out.printf("%010d.%n", 1234);
		System.out.printf("%010d.%n", -1234);
		
		System.out.println("**Pontos flutuantes**");
		System.out.printf("%.2f.%n", 10.345);
		System.out.printf("%10.2f.%n", 10.345);
	}

}
