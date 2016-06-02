package br.com.upgrade.ocp6.collections;

import java.util.List;
import java.util.Optional;

public class SearchData {
	
	private static final List<Person> list = Person.load();
	
	/**
	 * Tópico: Search for data by using methods, such as findFirst(), findAny(), anyMatch(), 
	 * allMatch(), and noneMatch()
	 */
	public static void main(String[] args) {
		findFirst(); 	//primeira ocorrÊncia
		findAny(); 		//alguma ocorrência
		anyMatch(); 	//qualquer ocorrência
		allMatch(); 	//todas as ocorrências
		noneMatch(); 	//nenhuma ocorrência
	}
	
	/**
	 * Filtra e entao imprime o primeiro elemento encontrado, se for encontrado
	 */
	private static void findFirst() {
	
		Optional<Person> findFirst = list.stream()
			.filter(p -> p.getAge() < 25) //Predicate<? super Class> executa um teste e retorna true ou false
			.findFirst();
		
		//imprime somente se for encontrado
		findFirst.ifPresent(p -> System.out.println("First finded: " + p.getFirstName()));
	}
	
	/**
	 * Retorna um Optional de uma ocorrência aleatória da coleção ou um Optional vazio. 
	 */
	private static void findAny() {
		
		list.stream().findAny().ifPresent(p -> System.out.println("findAny: " + p.getFirstName()));

		//findAny return an Optional
		Optional<Person> p = list.stream().filter(p2 -> p2.getAge() < 28).findAny();
		System.out.println("findAny again: " + p.get().getFirstName());
		
		list.stream()
				.filter(p2 -> p2.getAge() > 28 )
				.findAny()
				.ifPresent(p3 -> System.out.println("One more time: " +p3.getFirstName()));
		
	}
	
	/**
	 * Valida a existencia de algum elemento na coleção que atenda ao predicado e
	 * retorna true ou false
	 */
	private static void anyMatch() {
		
		Boolean moreThen31 = list.stream().anyMatch(p -> p.getAge() > 31);
		System.out.println("Someone here have more then 31 age? " + moreThen31);
		
	}
	
	/**
	 * Valida se todos os elementos da coleção atendem ao predicado e então
	 * retorna true ou false
	 */
	private static void allMatch() {
		
		boolean moreThen23 = list.stream().allMatch(p -> p.getAge() > 23);
		System.out.println("All players have more then 23 age? " + moreThen23);
		
	}

	/**
	 * noneMatch retorna true se os elementos da coleção não atenderem ao predicado.  
	 */
	private static void noneMatch() {
		System.out.println("Filter 'ares', age < 25 and 'Jr': " +
				list.stream()
					.filter(p -> !p.getLastName().contains("ares") && !(p.getAge() < 25))
					.noneMatch(p2 -> p2.getLastName().contains("Jr")));
	}
	
}
