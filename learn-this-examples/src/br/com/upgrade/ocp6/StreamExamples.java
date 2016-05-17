package br.com.upgrade.ocp6;

import java.util.stream.IntStream;

public class StreamExamples {

	public static void main(String[] args) {
		exampleIntStream();
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
	
	
	
}
