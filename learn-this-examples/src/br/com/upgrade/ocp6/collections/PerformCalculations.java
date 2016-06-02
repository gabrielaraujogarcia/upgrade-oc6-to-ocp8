package br.com.upgrade.ocp6.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PerformCalculations {
	
	private static final List<Person> list = Person.load();
	
	public static void main(String[] args) {
		count();
		max();
		min();
		average();
		sum();
		reduce(); //bonus
	}
	
	/**
	 * Conta quantos elementos possui no Stream da coleção
	 */
	private static void count() {
		System.out.println("Count a new ArrayList: " + new ArrayList<>().stream().count());
		System.out.println("Count a list of person: " + list.stream().count());
	}
	
	/**
	 * Mapeia o Stream de pessoas pela idade e então retorna a pessoa mais velha
	 */
	private static void max() {
		list.stream()
			.map(person -> person.getAge()) //ou Person::getAge
			.max((a1, a2) -> a1.compareTo(a2))
			.ifPresent(maxAge -> System.out.println("Older person: " + maxAge));
	}
	
	/**
	 * Mapeia o Stream de pessoas pelo ultimo nome e então retorna o menor nome da ordem
	 * crescente do alfabeto
	 */
	private static void min() {
		//Comparator<Person> comp = (p1, p2) -> p1.getLastName().compareTo(p2.getLastName());
		list.stream()
			.map(Person::getLastName)
			.min((p1, p2) -> p1.compareTo(p2)) //ou trocar por comp e remover a linha anterior
			.ifPresent(s -> System.out.println("Min last name: " + s));
	}
	
	/**
	 * Calcula a média das idades na lista
	 */
	private static void average() {
		list.stream()
			.mapToInt(Person::getAge)
			.average()
			.ifPresent(age -> System.out.println("Average: " + age));
	}
	
	/**
	 * Maneiras de realizar uma soma na coleção com Java 8 e lambda
	 */
	private static void sum() {
	
		//Soma a idade das pessoas utilizando o método Collectors.summingInt(Function<? super T>
		Integer sum2 = list.stream().collect(Collectors.summingInt(Person::getAge));
		System.out.println("Sum of ages with Collectors: " + sum2);
		
		//transforma o stream em um Stream de inteiros e então realiza a soma
		int other = list.stream().mapToInt(Person::getAge).sum();
		System.out.println("Sum of ages (better way): "+other);
		
	}
	
	/**
	 * Exemplos que utilizam a API reduce do Stream<T> 
	 */
	private static void reduce() {
		
		//reduz em um Stream<Integer>, somando cada elemento e retorna um Optional<Integer>
		Optional<Integer> sum = list.stream()
			.map(Person::getAge) 
			.reduce((a, b) -> a + b);
		System.out.println("Sum of ages: " + sum.get());
		
		//reduz o Stream<Integer>, somando cada elemento com o valor do primeiro parâmetro 
		//(neste caso 6)
		Integer sumPlus6 = list.stream()
			.map(Person::getAge) //retorna um Stream<Integer>
			.reduce(6, (a, b) -> a + b);
		System.out.println("Sum of ages + 6: " + sumPlus6);
		
	}
	
}
