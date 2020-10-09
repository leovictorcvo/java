package com.collections.arrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.collections.classes.Person;
import com.collections.classes.SortPersonByCpf;

public class ArrayListTest {

	public static void main(String[] args) {
		List<Person> pessoas = new ArrayList<>();
		List<Person> pessoas2 = new ArrayList<>();

		String id;
		for(int i = 0; i < 20; i = i + 2) {
			id = String.format("%02d", i);
			pessoas.add(new Person(i, "Pessoa " + id, "01" + id));
		}

		//Exibe o item armazenado na posição desejada baseado no indice 0 (no exemplo 2 significa o 3º item)
		System.out.println(pessoas.get(2));

		//Remove o item armazenado na posição desejada baseado no indice 0 (no exemplo 2 significa o 3º item)
		pessoas.remove(2);


		for(int i = 1; i < 20; i = i + 2) {
			id = String.format("%02d", i);
			pessoas2.add(new Person(i, "Pessoa " + id, "02" + id));
		}

		//Adiciona todos os items de pessoas2 no final de pessoas
		pessoas.addAll(pessoas2);

		//Remove todos os items de pessoas2 porém não remove a referencia a lista.
		pessoas2.clear();

		pessoas2.add(new Person(200, "Ainda pode usar pessoas 2", "200"));
		System.out.println(pessoas2.get(0));

		for(Person pessoa : pessoas) {
			System.out.println(pessoa);
		}
		System.out.println("Quantidade de itens em pessoas: " + pessoas.size());

		//Verifica se o item existe na lista. Utiliza o metodo Equals para realizar a verificação
		System.out.println(pessoas.contains(new Person(1, "Pessoa_2 1", "1")));
		//Como o CPF existe indica que está na coleção mesmo que id e nome não estejam
		System.out.println(pessoas.contains(new Person(100, "Pessoa_2 100", "1"))); 

		//Informa o indice do item no array ou -1. Utiliza o metodo Equals para realizar a verificação
		System.out.println(pessoas.indexOf(new Person(1, "Pessoa_2 1", "1")));
		System.out.println(pessoas.indexOf(new Person(100, "Pessoa_2 100", "100")));

		//Ordenando a lista. Lembrando que se a classe deve implementar a interface Comparable
		Collections.sort(pessoas);
		System.out.println("*** Ordenado pelo nome ***");
		for(Person pessoa : pessoas) {
			System.out.println(pessoa);
		}

		//Podemos também ordernar o arrayList por um outro membro da classe utilizando uma  
		//classe que implemente a interface Comparator
		Collections.sort(pessoas, new SortPersonByCpf());
		System.out.println("*** Ordenado pelo cpf***");
		for(Person pessoa : pessoas) {
			System.out.println(pessoa);
		}

		//Para binarySearch, devemos informar a forma de ordenação utilizada para que o valor retornado seja confiável.
		//Caso o item não seja encontrado, o valor será o seguinte: -(valor do ponto de inserção) - 1
		//Ordenando pelo nome
		Collections.sort(pessoas);
		//A pesquisa abaixo deve retornar -1, pois a posicao de inserção é 0 e pela formula -(0)-1 = -1
		System.out.println(Collections.binarySearch(pessoas, new Person(0, "A", "0000"))); 
		//Se usar um comparator, o mesmo deve ser informado na pesquisa
		Collections.sort(pessoas, new SortPersonByCpf());
		//A pesquisa abaixo deve retornar -3, pois a posicao de inserção é 2 e pela formula -(2)-1 = -3
		System.out.println(Collections.binarySearch(pessoas, new Person(4, "Pessoa 04", "0104"), new SortPersonByCpf())); 
		//Quando encontra o item, retorna o indice atual do item.
		System.out.println(Collections.binarySearch(pessoas, new Person(9, "Pessoa 09", "0209"), new SortPersonByCpf()));

		/************************** Convertendo Arrays para ArrayList e vice versa **************************/ 
		//Podemos converter um ArrayList em um Array facilmente
		Person[] arrayDePersons = new Person[pessoas2.size()];
		pessoas2.toArray(arrayDePersons);
		System.out.println(Arrays.toString(arrayDePersons));

		List<Integer> numeros = new ArrayList<>();
		numeros.add(0);
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);

		Integer[] arrayNumeros = new Integer[numeros.size()];
		numeros.toArray(arrayNumeros);

		//Entretanto a conversão de um Array para um ArrayList tem uma pegadinha
		List<Integer> destinoVinculado = Arrays.asList(arrayNumeros);
		//Se alterarmos o valor de um item de destinoVinculado, esse valor será alterado em arrayNumeros
		System.out.println(Arrays.toString(arrayNumeros)); //[0, 1, 2, 3]
		destinoVinculado.set(0, 10);
		System.out.println(Arrays.toString(arrayNumeros)); //[10, 1, 2, 3]
		
		//A forma de converter um Array sem vincular ele ao ArrayList é a seguinte:
		List<Integer> destinoNaoVinculado = new ArrayList<>();
		destinoNaoVinculado.addAll(Arrays.asList(arrayNumeros));
		System.out.println(Arrays.toString(arrayNumeros)); //[10, 1, 2, 3]
		destinoNaoVinculado.set(0, 0);
		System.out.println(Arrays.toString(arrayNumeros)); //[10, 1, 2, 3]
		System.out.println(destinoNaoVinculado);
		
		//Para remover itens da list com base em uma condição podemos utilizar o iterator
		Iterator<Person> personIterator = pessoas.iterator();
		System.out.println("Antes do iterator = " + pessoas.size());
		while(personIterator.hasNext()) {
			if (personIterator.next().getId() > 10) {
				personIterator.remove();
			}
		}
		System.out.println("Depois do iterator = " + pessoas.size());
	}

}
