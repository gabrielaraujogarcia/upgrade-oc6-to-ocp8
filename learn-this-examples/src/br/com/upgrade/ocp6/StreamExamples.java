package br.com.upgrade.ocp6;

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
	
	
}
