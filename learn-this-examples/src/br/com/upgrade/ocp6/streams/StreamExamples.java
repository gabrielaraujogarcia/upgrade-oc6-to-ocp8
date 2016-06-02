package br.com.upgrade.ocp6.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamExamples {

	public static void main(String[] args) {
		exampleIntStream();
		exampleArrayStream();
	}
	
	/**
	 * Exemplo do uso da interface IntStream
	 */
	public static void exampleIntStream() {
		
		//Cria um IntStream exclusivo contendo os inteiros de 0 a 5 e então imprime os mesmos
		//Same as: IntStream x = IntStream.range(0, 6); x.forEach(System.out::println);
		IntStream.range(0, 6).forEach(System.out::print); 
		
		System.out.println();
		
		//Cria um IntStream inclusivo contendo os inteiros de 0 a 6 e então imprime os mesmos
		IntStream.rangeClosed(0, 6).forEach(System.out::print);
		
	}
	
	/**
	 * Exemplo de uso da API de Stream com Array
	 */
	public static void exampleArrayStream() {
		
		List<String> myList = Arrays.asList("item 11", "item 12", "item 31", "item 35", "item 45");
		System.out.println("");
		
		myList.stream()
			.filter(s -> s.contains("5"))
			.map(String::toUpperCase)
			.forEach(s -> System.out.print(s +", "));
		
		System.out.println("");
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		
		//filtra uma lista de quadrados sem repetição
		numbers.stream()
			.map( i -> i*i)
			.distinct()
			.forEach(s -> System.out.print(s + ", "));
		
	}
	
	/**
	 * Recupera uma nova coleção a partir da coleção original com a classe Collectors
	 */
	public static void exampleCollect() {
		
		List<Person> list = new ArrayList<>();
			
		list.add(new Person(35, "P1"));
		list.add(new Person(28, "P2"));
		list.add(new Person(21, "P3"));
		list.add(new Person(51, "P4"));
		list.add(new Person(17, "P5"));
		
		//filtra a lista e retorna uma nova coleção
		List<Person> filtered = list.stream()
			.filter(p -> p.getAge() > 20 && p.getAge() < 30)
			.collect(Collectors.toList());
		
	}
	
	private static class Person {
		
		private int age;
		private String name;
		
		public Person(int age, String name) {
			this.age = age;
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public String getName() {
			return name;
		}
		
		
	}
	
}
