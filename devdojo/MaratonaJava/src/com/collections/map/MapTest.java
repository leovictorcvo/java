package com.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.collections.classes.Person;

public class MapTest {

	public static void main(String[] args) {
		Map<Integer, Person> pessoas = new HashMap<>();
		pessoas.put(4, new Person(4, "Pessoa 04", "04"));
		pessoas.put(1, new Person(1, "Pessoa 01", "01"));
		pessoas.put(3, new Person(3, "Pessoa 03", "03"));
		pessoas.put(2, new Person(2, "Pessoa 02", "02"));
		
		for(int key : pessoas.keySet()) {
			System.out.println(key);
		}
		
		for(Person p : pessoas.values()) {
			System.out.println(p);
		}
		
		for(Entry<Integer, Person> entry : pessoas.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

}
