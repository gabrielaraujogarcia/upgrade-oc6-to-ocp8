package br.com.upgrade.ocp6.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SaveResultsToCollection {
	
	private static final List<Person> list = Person.load();
	private static int count = 0;
	
	public static void main(String[] args) {
		averaging();
		groupingBy();
		joining();
		partitioningBy();
	}
	
	/**
	 * Exemplos de como realizar o cálculo da média dos elementos em uma collection
	 * utilizando o método collect do Stream e os métodos da classe Collectors.
	 */
	@SuppressWarnings("serial")
	private static void averaging() {
		
		double average = new ArrayList<Integer>(){{add(1); add(1); add(1);}}
			.stream()
			.collect(Collectors.averagingInt(number -> number));
		System.out.println("Basic example of average: " + average);
		
		average = list.stream()
			.map(Person::getAge)
			.collect(Collectors.averagingDouble(age -> age));
		System.out.println("Average of ages: " + average);
		
		average = list.stream()
				.map(person -> person.getLastName())
				.collect(Collectors.averagingDouble(lastName -> {
					count++;
					return lastName.toString().length();
				}));
		
		System.out.println("Average of characteres on the last name of each person: "+ average);
		System.out.println("Note that this algorithm goes 2 times for each element in a "
				+ "collection. At moment I don't know why it happens. "+ count);
	}
	
	/**
	 * Exemplo de como agrupar os elementos na coleção utilizando o método collect do 
	 * da API de Stream e o método groupingBy da classe Collectors.
	 */
	private static void groupingBy() {
		
		Map<String, List<Person>> group = list.stream()
				.collect(Collectors.groupingBy(Person::getTeam));
		
		group.forEach((team, players) -> {
			System.out.println("Team: " + team);
			players.forEach(p -> System.out.println("\t" + p.getFirstName()+ 
					" " + p.getLastName()));
		});
		
		Map<String, Long> otherGroup = list.stream()
					.collect(Collectors.groupingBy(Person::getTeam, 
							Collectors.summingLong(Person::getAge)));
		
		otherGroup.forEach((team, ages) -> 
			System.out.println("Team: "+ team + " | Sum of ages: " + ages));
	}
	
	/**
	 * Concatena todas as Strings mapeadas a partir do Stream da coleção sem 
	 * separador, com separador e também com separator, prefixo e sufixo
	 */
	private static void joining() {
		
		String names = list.stream()
			.filter(p -> p.getAge() < 29)
			.map(Person::getFirstName)
			.collect(Collectors.joining());
		System.out.println("Joining without separator: "+ names);
		
		names = list.stream()
				.filter(p -> p.getTeam().contains("Real"))
				.map(Person::getLastName)
				.collect(Collectors.joining(", "));
		System.out.println("Joining with separator: "+ names);
		
		names = list.stream()
				.filter(p -> p.getLastName().contains("s"))
				.map(Person::getLastName)
				.collect(Collectors.joining(", ", "The last name of the players ", " contains \"s\"!"));
		System.out.println("Joining with separator, prefix and suffix: "+ names);
		
	}
	
	/**
	 * Realiza a separação da coleção a partir do método da classe Collectors.partitionBy
	 * que, realiza a validação do predicado e então divide os elementos em dois grupos,
	 * sendo eles, os que atendem a condição e os que não atendem a condição.
	 */
	private static void partitioningBy() {
		
		Map<Boolean, List<Person>> partitionByAge = list.stream()
				.collect(Collectors.partitioningBy(p -> p.getAge() > 28));
			
		partitionByAge.forEach((key, list) -> {
			System.out.println("That guys has more then 28? " + key + ". Who are they? \n");
			list.forEach(p -> System.out.println(p.toString()+ " \n"));
		});
		
	}
}	
