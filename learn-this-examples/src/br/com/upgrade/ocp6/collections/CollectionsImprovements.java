package br.com.upgrade.ocp6.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionsImprovements {
	
	private static final List<Person> list = Person.load();
	
	public static void main(String[] args) {
		removeIf();
		replaceAll();
		compute();
		computeIfAbsent();
		computeIfPresent();
	}
	
	/**
	 * Remove da coleção dado o predicado. Atenção pois este método é diferente do método
	 * filter, pois ele altera a coleção ao invés de retornar um Stream da mesma.
	 */
	private static void removeIf() {
		list.removeIf(p -> p.getAge() >= 28);
		list.forEach(p -> {
			System.out.println(p);
			System.out.print(System.lineSeparator());
		});
		System.out.print(System.lineSeparator());
	}
	
	/**
	 * Altera os elementos da coleção de acordo com a operação implementada (UnaryOperation<T>).
	 */
	private static void replaceAll() {
		
		//filtra
		List<String> names = list.stream()
			.map(p -> p.getFirstName() + " " + p.getLastName()) //retorna stream
			.collect(Collectors.toList()); //retorna uma lista
		
		//altera a coleção
		names.replaceAll(firstName -> firstName.toUpperCase());
		
		//imprime cada ocorrência
		names.forEach(System.out::println);
		System.out.print(System.lineSeparator());
		
	}
	
	/**
	 * Computa os valores do mapa e adiciona ou altera os mesmos de acordo com a BiFunction
	 * implementada.
	 */
	private static void compute() {
		
		Map<String, Integer> map = new HashMap<>();
		
		//map.compute(key, BiFunction<T, U, R> remappingFunction)
		map.compute("A", (key, value) -> value == null ? 1 : value + 1);
		map.compute("A", (key, value) -> value == null ? 0 : value + 2);
		map.compute("B", (key, value) -> value == null ? 100 : value--);

		System.out.println(map);
		System.out.print(System.lineSeparator());
		
	}
	
	/**
	 * Computa os valores do mapa e, se uma chave não estiver associada a um valor ou associada com
	 * um valor nulo, o valor é computado de acordo com a Function e adicionado ao mapa.
	 */
	private static void computeIfAbsent() {

		Map<Person, String> map = new HashMap<>();

		Function<Person, String> func = person -> {
			return person.getFirstName();
		};
		
		//repare que  o tipo da Function e do map são iguais
		Function<Person, String> func2 = person -> {
			return person.getLastName();
		};
		
		Person p = list.stream().findAny().get();
		
		//nao existe, imprime null
		System.out.println("Person is not mapped: " + map.get(p));
		
		//adiciona no mapa p como chave e o valor do retorno da func
		map.computeIfAbsent(p, func);
		
		//agora existe
		System.out.println(map.get(p));
		System.out.print(System.lineSeparator());
		
		//agora com um objeto mapeado com valor nulo
		Person p2 = list.stream().filter(p3 -> !p3.getFirstName().equals(p.getFirstName()))
				.findAny().get();
		
		map.put(p2, null);
		System.out.println("Person 2 is mapped to null: " + map.get(p2));
		
		/*
		 * Adiciona p2 ao mapa como chave e o valor do retorno da func normalmente, pois
		 * p2 estava mapeado com valor nulo
		 */
		map.computeIfAbsent(p2, func);
		
		System.out.println(map.get(p2));
		System.out.print(System.lineSeparator());
		
		/* 
		 * Agora se tentar computar ao mapa uma chave já associada a um valor diferente de
		 * nulo com o método computeIfAbsent, o valor desta chave não será alterado.
		 **/
		map.computeIfAbsent(p, func2);
		System.out.println(map.get(p));
		
	}
	
	/**
	 * Computa uma dada chave do mapa e, caso a mesma exista na coleção e seu valor
	 * seja diferente de nulo, o mesmo é computado e um novo valor é atribuido de
	 * acordo com o retorno da Function informada.
	 */
	private static void computeIfPresent() {
		
		Map<Person, String> map = new HashMap<>();
		
		/*
		 * Ao contrário do computeIfAbsent, computeIfPresent irá computar a
		 * chave e a Function caso a chave já esteja mapeada no mapa e com valor
		 * diferente de nulo.
		 */
		Person p = list.stream().findAny().get();
		
		//computando sem adicionar
		map.computeIfPresent(p, (person, lastName) -> person.getLastName());
		System.out.println("Before add: " + map.get(p));
		
		//adiciona
		map.put(p, p.getFirstName());
		System.out.println("Before computing: " + map.get(p));
		
		//computa o valor adicionado
		map.computeIfPresent(p, (person, lastName) -> person.getLastName());
		System.out.println("After computing: " + map.get(p));
		
	}
	
}
