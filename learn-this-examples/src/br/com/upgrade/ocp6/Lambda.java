package br.com.upgrade.ocp6;

import java.util.ArrayList;
import java.util.List;

public class Lambda {
	
	/**
	 * Classe que representa uma pessoa
	 * @author Gabriel
	 *
	 */
	private static class Person {
		
		public enum Sex {
			MALE, FEMALE;
		}
		
		private String name;
		private Integer age;
		private Sex gender;
		
		public Person(String name, Integer age, Sex sex) {
			this.name = name;
			this.age = age;
			this.gender = sex;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public Sex getGender() {
			return gender;
		}

		public void setGender(Sex gender) {
			this.gender = gender;
		}
		
	}
	
	/**
	 * Interface funcional para filtrar as pessoas
	 * @author Gabriel
	 *
	 */
	@FunctionalInterface
	private interface CheckPerson {
		boolean check(Person p);
	}
	
	/**
	 * Interface funcional com método abstrato sem parâmetros
	 * @author Gabriel
	 *
	 */
	@FunctionalInterface
	private interface DoSomeThing {
		void doSomeThing();
	}
	
	/**
	 * Método que executa o teste a ser realizado
	 * @param roster
	 * @param tester
	 */
	private static void print(List<Person> roster, CheckPerson tester) {
		
		for(Person p : roster) {
			if(tester.check(p)) {
				System.out.println(p.getName());
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		List<Person> roster = new ArrayList<>();
		roster.add(new Person("Renata", 27, Person.Sex.FEMALE));
		roster.add(new Person("Gabriel", 28, Person.Sex.MALE));
		roster.add(new Person("Maria Emília", 59, Person.Sex.FEMALE));
		roster.add(new Person("Ronaldo", 59, Person.Sex.MALE));
		roster.add(new Person("Leandro", 29, Person.Sex.MALE));
		roster.add(new Person("Cláudia", 55, Person.Sex.FEMALE));
		roster.add(new Person("Moacir", 55, Person.Sex.MALE));
		roster.add(new Person("Karla", 26, Person.Sex.FEMALE));
		
		//antes do lambda, era comum utilizar classes anônimas para implementar uma tarefa de uma interface funcional
		print(roster, new CheckPerson() {
			
			@Override
			public boolean check(Person p) {
				return p.getName().startsWith("R");
			}
			
		});
		
		//após a adição do lambda na linguagem, podemos simplificar para esta expressão
		System.out.print("\n");
		print(roster, (Person p) -> p.getAge() < 30); //ou print(roster, (p) -> p.getAge() < 30);
		
		//podemos omitir o parênteses do parâmetro quando o método abstrato da interface possui apenas um parâmetro
		System.out.print("\n");
		print(roster, p -> Person.Sex.FEMALE.equals(p.getGender()));
		
		//quando o método abstrato da interface funcional não possui parâmetros, devemos adicionar os parênteses vazios
		DoSomeThing d = () -> System.out.println("I did!");
		d.doSomeThing();
		
	}
}
