package br.com.upgrade.ocp6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectionsWithLambda {
	
	private static final List<Person> list = load();
	private static int sumOfAges;
	
	private static final Comparator<Person> BY_NAME = (p1, p2) -> {
		return p1.getFirstName().compareTo(p2.getFirstName());
	};

	public static void main(String[] args) {
		iteratesFilterAndSort();
		searchData();
		doCalculations();
	}
	
	/**
	 * Develop code that iterates a collection, filters a collection, and sorts a 
	 * collection by using lambda expressions
	 */
	private static void iteratesFilterAndSort() {
		iterates();
		filter();
		sort();
		System.out.println("");
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
		Collections.sort(list, CollectionsWithLambda::orderByLastName);
		list.stream().forEach(p -> System.out.println(p.getFirstName() + " " +p.getLastName()
			+" " +p.getAge()));
		
	}
	
	/**
	 * Tópico: Search for data by using methods, such as findFirst(), findAny(), anyMatch(), 
	 * allMatch(), and noneMatch()
	 */
	private static void searchData() {
		findFirst(); 	//primeira ocorrÊncia
		findAny(); 		//alguma ocorrência
		anyMatch(); 	//qualquer ocorrência
		allMatch(); 	//todas as ocorrências
		noneMatch(); 	//nenhuma ocorrência
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
	
	/*
	 * 
	 */
	
	/**
	 * Tópico: Perform calculations on Java Streams by using count, max, min, average and sum  
	 * methods and save results to a collection by using the collect method and Collector class,
	 * including the averagingDouble, groupingBy, joining, partitioningBy methods 
	 */
	private static void doCalculations() {
		count();
		max();
		min();
		average();
		sum();
		reduce(); //bonus
		System.out.println("");
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
