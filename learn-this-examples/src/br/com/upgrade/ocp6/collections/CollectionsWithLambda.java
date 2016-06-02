package br.com.upgrade.ocp6.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsWithLambda {
	
	private static final List<Person> list = Person.load();
	private static int sumOfAges;
	
	private static final Comparator<Person> BY_NAME = (p1, p2) -> {
		return p1.getFirstName().compareTo(p2.getFirstName());
	};

	/**
	 * Develop code that iterates a collection, filters a collection, and sorts a 
	 * collection by using lambda expressions
	 */
	public static void main(String[] args) {
		iterates();
		filter();
		sort();
	}
	
	/**
	 * Percorre a lista de jogadores e então soma a idade de todos eles 
	 */
	private static void iterates() {
		list.stream()
			.forEach(p -> sumOfAges += p.getAge());
		System.out.println("Sum of ages: "+ sumOfAges);
	}
	
	/**
	 * Filtra a coleção e exibe o nome dos jogadores com mais de 25 anos
	 */
	private static void filter() {
		list.stream() //retorna um Stream da coleção  
			.filter(p -> p.getAge() > 25) //filtra os jogadores com idade maior que 25 anos
			.forEach(p -> System.out.println(p.getFirstName())); //percorre cada elemento filtrado e escreve no console
		System.out.println("");
	}
	
	/**
	 * Ordena e então imprime
	 */
	private static void sort() {
		
		//ordena pela idade através do método sort em collections, que recebe um Comparator<? super Person> utilizando lambda
		System.out.println("Order by age: ");
		list.sort((p1, p2) -> p1.getAge().compareTo(p2.getAge()));
		list.stream().forEach(p -> System.out.println(p.getFirstName() + " " +p.getLastName()
				+" " +p.getAge()));
		System.out.println("");
		
		//outra maneira de ordenar, informando o comparator implementado com lambda no inicio desta classe
		System.out.println("Order by first name: ");
		Collections.sort(list, BY_NAME);
		list.stream().forEach(p -> System.out.println(p.getFirstName() + " " +p.getLastName()
				+" " +p.getAge()));
		System.out.println("");
		
		//ordenar a coleção por inferência de método
		System.out.println("Order by last name: ");
		Collections.sort(list, Person::orderByLastName);
		list.stream().forEach(p -> System.out.println(p.getFirstName() + " " +p.getLastName()
			+" " +p.getAge()));
		
	}
	
}
