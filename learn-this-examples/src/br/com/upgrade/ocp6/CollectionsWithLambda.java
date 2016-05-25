package br.com.upgrade.ocp6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CollectionsWithLambda {
	
	private static final List<Person> list = load();
	private static int sumOfAges;
	
	private static final Comparator<Person> BY_NAME = (p1, p2) -> {
		return p1.getFirstName().compareTo(p2.getFirstName());
	};

	public static void main(String[] args) {
		iterates();
		filter();
		sort();
		findFirst();
		findAny();
		anyMatch();
		allMatch();
		noneMatch();
		sum();
		
//		Runnable runnable = new Runnable() {
//			
//			@Override
//			public void run() {
//				findAny();
//			}
//			
//		};
		
//		int i = 0;
//		while(i < 5) {
//			new Thread(runnable).start();
//			new Thread(runnable).start();
//			new Thread(runnable).start();
//			i++;
//		}
		
	}
	
	/**
	 * Percorre a lista de jogadores e então soma a idade de todos eles 
	 */
	private static void iterates() {
		
		list.stream()
			.forEach(p -> sumOfAges += p.getAge());
		System.out.println("Sum of ages: "+ sumOfAges);
		System.out.println("");
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
		Collections.sort(list, CollectionsWithLambda::orderByLastName);
		list.stream().forEach(p -> System.out.println(p.getFirstName() + " " +p.getLastName()
			+" " +p.getAge()));
		System.out.println("");
		
	}
	
	/**
	 * Filtra e entao imprime o primeiro elemento encontrado, se for encontrado
	 */
	private static void findFirst() {
	
		Optional<Person> findFirst = list.stream()
			.filter(p -> p.age < 25) //Predicate<? super Class> executa um teste e retorna true ou false
			.findFirst();
		
		//imprime somente se for encontrado
		findFirst.ifPresent(p -> System.out.println("First finded: " + p.getFirstName()));
		System.out.println("");
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
		System.out.println("");
		
	}
	
	/**
	 * Valida a existencia de algum elemento na coleção que atenda ao predicado e
	 * retorna true ou false
	 */
	private static void anyMatch() {
		
		Boolean moreThen31 = list.stream().anyMatch(p -> p.getAge() > 31);
		System.out.println("Someone here have more then 31 age? " + moreThen31);
		System.out.println("");
		
	}
	
	/**
	 * Valida se todos os elementos da coleção atendem ao predicado e então
	 * retorna true ou false
	 */
	private static void allMatch() {
		
		boolean moreThen23 = list.stream().allMatch(p -> p.getAge() > 23);
		System.out.println("All players have more then 23 age? " + moreThen23);
		System.out.println("");
		
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
	
	/*
	 * Perform calculations on Java Streams by using count, max, min, average, and sum methods 
	 * and save results to a collection by using the collect method and Collector class, including 
	 * the averagingDouble, groupingBy, joining, partitioningBy methods
	 */
	
	/**
	 */
	private static void sum() {
		
		//reduz o Stream<Integer>, somando cada elemento e retorna um Optional<Integer>
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
		
		//transforma o stream em um Stream de inteiros e então realiza a soma
		int betterWay = list.stream()
			.mapToInt(Person::getAge)
			.sum();
		System.out.println("Sum of ages (better way): "+betterWay);
		
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
