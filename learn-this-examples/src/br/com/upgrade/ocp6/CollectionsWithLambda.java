package br.com.upgrade.ocp6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CollectionsWithLambda {

	private static int sumOfAges;
	
	private static final Comparator<Person> BY_NAME = (p1, p2) -> {
		return p1.getFirstName().compareTo(p2.getFirstName());
	};

	public static void main(String[] args) {
		iterates();
		filter();
		sort();
		findFirst();
	}
	
	/**
	 * Percorre a lista de jogadores e então soma a idade de todos eles 
	 */
	private static void iterates() {
		
		List<Person> players = load();
		
		players.stream()
			.forEach(p -> sumOfAges += p.getAge());
		System.out.println("Sum of ages: "+ sumOfAges);
		System.out.println("");
	}
	
	/**
	 * Filtra a coleção e exibe o nome dos jogadores com mais de 25 anos
	 */
	private static void filter() {
		
		List<Person> players = load();
		
		players.stream() //retorna um Stream da coleção  
			.filter(p -> p.getAge() > 25) //filtra os jogadores com idade maior que 25 anos
			.forEach(p -> System.out.println(p.getFirstName())); //percorre cada elemento filtrado e escreve no console
		System.out.println("");
		
	}
	
	/**
	 * Ordena e então imprime
	 */
	private static void sort() {
		
		List<Person> players = load();

		//ordena pela idade através do método sort em collections, que recebe um Comparator<? super Person> utilizando lambda
		System.out.println("Order by age: ");
		players.sort((p1, p2) -> p1.getAge().compareTo(p2.getAge()));
		players.stream().forEach(p -> System.out.println(p.getFirstName() + " " +p.getLastName()
				+" " +p.getAge()));
		System.out.println("");
		
		//outra maneira de ordenar, informando o comparator implementado com lambda no inicio desta classe
		System.out.println("Order by first name: ");
		Collections.sort(players, BY_NAME);
		players.stream().forEach(p -> System.out.println(p.getFirstName() + " " +p.getLastName()
				+" " +p.getAge()));
		System.out.println("");
		
		//ordenar a coleção por inferência de método
		System.out.println("Order by last name: ");
		Collections.sort(players, CollectionsWithLambda::orderByLastName);
		players.stream().forEach(p -> System.out.println(p.getFirstName() + " " +p.getLastName()
			+" " +p.getAge()));
		System.out.println("");
		
	}
	
	/**
	 * Filtra e entao imprime o primeiro elemento encontrado, se for encontrado
	 */
	private static void findFirst() {
	
		List<Person> list = load();
		
		Optional<Person> findFirst = list.stream()
			.filter(p -> p.age < 25) //Predicate<? super Class> executa um teste e retorna true ou false
			.findFirst();
		
		//imprime somente se for encontrado
		findFirst.ifPresent(p -> System.out.println("First finded: " + p.getFirstName()));
		System.out.println("");
	}
	
	private static void findAny() {
		
	}
	
	private static void anyMatch() {
		
	}
	
	private static void allMatch() {
		
	}
	
	private static void noneMatch() {
		
	}
	
	private class Person {
		
		private String firstName;
		private String lastName;
		private Integer age;
		
		public Person(String firstName, String lastName, Integer age) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.age = age;
		}
		
		public Integer getAge() {
			return age;
		}
		
		public String getFirstName() {
			return firstName;
		}
		
		public String getLastName() {
			return lastName;
		}
		
	}
	
	private static List<Person> load() {
		 
		CollectionsWithLambda c = new CollectionsWithLambda();
		List<Person> starsOfFootball = new ArrayList<>();
		
		starsOfFootball.add(c.new Person("Lionel", "Messi", 28));
		starsOfFootball.add(c.new Person("Luis", "Suares", 29));
		starsOfFootball.add(c.new Person("Neymar", "Jr", 24));
		starsOfFootball.add(c.new Person("Cristiano", "Ronaldo", 31));
		starsOfFootball.add(c.new Person("James", "Rodrigues", 24));
		starsOfFootball.add(c.new Person("Karim", "Benzema", 28));
		
		return starsOfFootball;

	}
	
	private static final int orderByLastName(Person p1, Person p2) {
		return p1.getLastName().compareTo(p2.getLastName());
	}
	
}
