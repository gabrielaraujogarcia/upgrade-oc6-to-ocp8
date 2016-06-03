package br.com.upgrade.ocp6.collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class CollectionsJavaStream {

	private static final List<Person> list = Person.load();
	
	public static void main(String[] args) {
		merge();
		flatMap();
		map();
	}
	
	/**
	 * O método map.merge recebe 3 parâmetros, sendo eles:
	 * - A chave de um elemento no mapa;
	 *  - Um valor do tipo E;
	 *  - Uma BiFunction<T, U, R>;
	 *  Se a chave informada não está associada ao mapa ou associada a um valor nulo, a mesma é adicionada ao mapa e
	 *  o seu valor será o retorno da BiFunction.
	 *  Se a chave já existe no mapa, o seu valor é alterado de acordo com o valor retornado da BiFunction.
	 *  Se a BiFunction retornar null, a chave não é adicionada ao mapa e, caso esta chave já esteja contida no mapa,
	 *  a mesma é removida.
	 */
	private static void merge() {
		
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, null);
		
		//BiFunction<T, U, R> que recebe 2 parametros String (T e U) e retorna uma String (R) 
		BiFunction<String, String, String> bf = (s1, s2) -> {
			System.out.println("@param s1: " + s1);
			System.out.println("@param s2: " + s2);
			return s2 +  " " + s1;
		};
		
		//recupera o valor da posição 1 do map e então chama o método da interface funcional da BiFunction 
		//bf.apply(String, String) informando o valor recuperado em 1 e a palavra "Hi"
		map.merge(1, "Hello ", bf);

		//Como o valor da posição 1 era diferente de null e igual a A, o mesmo é alterado de acordo com
		//o retorno da BiFunction bf
		System.out.println(map.get(1));
		
		//podemos declarar também a BiFunction diretamente conforme este exemplo. Repare que os dois primeiros
		//parâmetros do método merge (neste caso, o valor da posição do no mapa 2 e a palavra"Hello"), são os 
		//parâmetros de entrada para o método apply da BiFunction (bf.apply("B", "Hello);)
		String x = map.merge(2, "Hello", (s1, s2) -> {
			System.out.println("@param s1: " + s1);
			System.out.println("@param s2: " + s2);
			return s2 + " " + s1;
		});
		
		//observe que o valor do map.get(2) foi alterado para  "Hello B"
		System.out.println(map.get(2));
		
		//o método merge também retorna o valor de retorno da BiFunction, neste caso String
		System.out.println(x);
		
		//se o valor da posição era null, ele é substituído pelo valor de retorno da BiFunction
		map.merge(3, "I'm not null for now", bf);
		System.out.println(map.get(3));
		
		//se a posição não existe no mapa, a mesma é adicionada ao mapa com o valor de retorno da BiFunction
		map.merge(4, "New value", bf);
		System.out.println(map.get(4));
		
		//se a BiFunction retornar null, o valor da posição é removido
		map.merge(3, "Removes the 3th element", (s1, s2) -> null);
		System.out.println(map.get(3));
		System.out.print(System.lineSeparator());
		
	}
	
	/**
	 * Recorrendo ao stackoverflow: Stream.flatMap, as it can be guessed by its name, is the combination of a map and a 
	 * flat operation. That means that you first apply a function to your elements, and then flatten it. Stream.map only 
	 * applies a function to the stream without flattening the stream. To understand what flattening a stream consists in,
	 * consider a structure like [ [1,2,3],[4,5,6],[7,8,9] ] which has "two levels". Flattening this means transforming it
	 * in a "one level" structure : [ 1,2,3,4,5,6,7,8,9 ]
	 */
	private static void flatMap() {

		List<String> names1 = Arrays.asList("Maria", "João");
		List<String> names2 = Arrays.asList("Pedro", "Laura");
		Stream<List<String>> s = Stream.of(names1, names2);
		s.flatMap(names -> names.stream()).forEach(System.out::println);
		System.out.print(System.lineSeparator());
		
	}
	
	/**
	 * Retorna um Stream<T> do objeto T mapeado.
	 */
	private static void map() {
		Stream<String> streamOfTeam = list.stream().map(Person::getLastName);
		streamOfTeam.forEach(System.out::println);
	}
	
	
}
