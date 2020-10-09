package collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class performanceTest {

	public static void main(String[] args) {
		final int NUMBER_ELEMENTS = 100000;
		long start, end;
		
        Collection<Integer> setOfNumbers = new HashSet<Integer>();

        start = System.currentTimeMillis();

        for (int i = 1; i <= NUMBER_ELEMENTS; i++) {
            setOfNumbers.add(i);
        }

        end = System.currentTimeMillis();

        System.out.println("Set - Adicionar elementos - Tempo gasto: " + (end - start));

        start = System.currentTimeMillis();

        for (Integer number : setOfNumbers) {
            setOfNumbers.contains(number);
        }

        end = System.currentTimeMillis();

        System.out.println("Set - Pesquisa - Tempo gasto: " + (end - start));

        Collection<Integer> arrayOfNumbers = new ArrayList<Integer>();

        start = System.currentTimeMillis();

        for (int i = 1; i <= NUMBER_ELEMENTS; i++) {
            arrayOfNumbers.add(i);
        }

        end = System.currentTimeMillis();

        System.out.println("Array - Adicionar elementos - Tempo gasto: " + (end - start));

        start = System.currentTimeMillis();
        
        for (Integer number : arrayOfNumbers) {
            arrayOfNumbers.contains(number);
        }

        end = System.currentTimeMillis();
        
        System.out.println("Array - Pesquisa - Tempo gasto: " + (end - start));
        
	}

}
