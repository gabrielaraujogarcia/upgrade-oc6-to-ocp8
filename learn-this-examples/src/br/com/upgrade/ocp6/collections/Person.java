package br.com.upgrade.ocp6.collections;

import java.util.ArrayList;
import java.util.List;

public class Person {
		
	private String firstName;
	private String lastName;
	private String team;
	private Integer age;
	
	public Person(String firstName, String lastName, String team, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.team = team;
		this.age = age;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getTeam() {
		return this.team;
	}
	
	public static final List<Person> load() {
		 
		List<Person> starsOfFootball = new ArrayList<>();
		
		starsOfFootball.add(new Person("Lionel", "Messi", "Barcelona", 28));
		starsOfFootball.add(new Person("Luis", "Suares", "Barcelona", 29));
		starsOfFootball.add(new Person("Neymar", "Jr", "Barcelona", 24));
		starsOfFootball.add(new Person("Cristiano", "Ronaldo", "Real Madrid", 31));
		starsOfFootball.add(new Person("James", "Rodrigues", "Real Madrid", 24));
		starsOfFootball.add(new Person("Karim", "Benzema", "Real Madrid", 28));
		
		return starsOfFootball;

	}
	
	public static final int orderByLastName(Person p1, Person p2) {
		return p1.getLastName().compareTo(p2.getLastName());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Name: ")
				.append(firstName).append(" ").append(lastName)
				.append(System.lineSeparator())
				.append("Age: ").append(age)
				.append(System.lineSeparator())
				.append("Team: ").append(team);
		return sb.toString(); 
	}
		
}
	
