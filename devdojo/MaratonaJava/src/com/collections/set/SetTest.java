package com.collections.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import com.collections.classes.Person;

public class SetTest {

	public static void main(String[] args) {
		Set<Person> pessoas = new  LinkedHashSet<>();
		pessoas.add(new Person(1, "Pessoa 01", "01"));
		pessoas.add(new Person(2, "Pessoa 02", "02"));
		pessoas.add(new Person(3, "Pessoa 03", "03"));
		pessoas.add(new Person(4, "Pessoa 04", "04"));
		for(Person p : pessoas) {
			System.out.println(p);
		}
		System.out.println("-----------------------------------------------------------");
		NavigableSet<Person> pessoasOrdenadas = new TreeSet<Person>();
		pessoasOrdenadas.add(new Person(2, "Pessoa 02", "02"));
		pessoasOrdenadas.add(new Person(5, "Pessoa 05", "05"));
		pessoasOrdenadas.add(new Person(1, "Pessoa 01", "01"));
		pessoasOrdenadas.add(new Person(3, "Pessoa 03", "03"));
		pessoasOrdenadas.add(new Person(4, "Pessoa 04", "04"));
		for(Person p : pessoasOrdenadas) {
			System.out.println(p);
		}
		// lower - <
		// floor - <=
		// higher - >
		// ceiling - >=
		System.out.println(pessoasOrdenadas.lower(new Person(3, "Pessoa 03", "03")));
		System.out.println(pessoasOrdenadas.floor(new Person(3, "Pessoa 03", "03")));
		System.out.println(pessoasOrdenadas.higher(new Person(3, "Pessoa 03", "03")));
		System.out.println(pessoasOrdenadas.ceiling(new Person(3, "Pessoa 03", "03")));

	}
}