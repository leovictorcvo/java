package com.collections.classes;

import java.util.Comparator;

public class SortPersonByCpf implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		return o1.getCpf().compareTo(o2.getCpf());
	}

}
