package br.com.upgrade.ocp6;

import java.util.ArrayList;
import java.util.List;

public class CollectionsWithLambda {

	public static void main(String[] args) {
		
	}
	
	private static void iterates() {
		
	}
	
	private static void filter() {
		
	}
	
	private static void sort() {
		
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
	
}
