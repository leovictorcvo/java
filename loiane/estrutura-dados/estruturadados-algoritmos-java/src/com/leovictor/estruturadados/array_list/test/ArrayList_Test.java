package com.leovictor.estruturadados.array_list.test;

import java.util.ArrayList;

public class ArrayList_Test {

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<String>();
	
		arrayList.add("A");
		arrayList.add("C");
		System.out.println(arrayList);
		
		arrayList.add(1, "B");
		System.out.println(arrayList);
		
		System.out.println(arrayList.contains("B"));
		System.out.println(arrayList.indexOf("B"));
		System.out.println(arrayList.indexOf("D"));
		System.out.println(arrayList.get(2));
	}

}
