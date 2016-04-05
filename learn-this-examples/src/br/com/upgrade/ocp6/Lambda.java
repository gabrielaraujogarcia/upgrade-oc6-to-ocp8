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
		
		public Person() {
			
		}
		
		public Person(String name, Integer age, Sex sex) {
			this.name = name;
			this.age = age;
			this.gender = sex;
		}
		
		public String getName() {
			return name;
		}

//		public void setName(String name) {
//			this.name = name;
//		}

		public Integer getAge() {
			return age;
		}

//		public void setAge(Integer age) {
//			this.age = age;
//		}

		public Sex getGender() {
			return gender;
		}

//		public void setGender(Sex gender) {
//			this.gender = gender;
//		}
		
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
	 * Interface funcional para realizar uma oepração com dois inteiros
	 * @author Gabriel
	 *
	 */
	@FunctionalInterface
	private interface DoThisOperation {
		int doIt(int x, int y);
	}
	
	/**
	 * Método que executa o teste a ser realizado na interface CheckPerson
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
		
		//exemplos de utilização de expressões lambda
		aboutLambda(roster);

		//o que não deve ser feito
		dontDoThis(roster);
		
		//exemplos que utilizam as interfaces funcionais padrão da API do Java 8
		
	}
	
	/**
	 * Exemplos básicos de expressões lambda
	 * @param roster
	 */
	private static void aboutLambda(List<Person> roster) {
	
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
	
	
	/**
	 * Exemplos do que não deve ser feito. Remova os comentários e estude os problemas
	 */
	private static void dontDoThis(List<Person> roster) {
		
		//a expressão lambda não pode declarar uma variável com mesmo nome de outra variável no mesmo escopo. 
		//se descomentar, apresenta erro de compilação
		Person duplicated = new Person("Dercy Gonsalves", 150, Person.Sex.FEMALE);
//		print(roster, duplicated -> {});
//		print(roster, (Person other) -> {
//			Person duplicated = new Person();
//			return other.getAge() > 40;
//		});
		
		//as variáveis declaradas e utilizadas nas expressões lambdas devem ser inicializadas
		//se descomentar, apresenta erro de compilação
		Person imNotIntilialized;
		print(roster, p -> {
			
//			if(imNotIntilialized != null) {;
//				return true;
//			}
			
			return false;
			
		});
		
		//as variáveis que serão utilizadas nas expressões lambdas devem ser final
		//se descomentar, apresenta erro de compilação
		int x = 5;
		int y = 10;
		
		DoThisOperation sumThis = (first, second) -> {
//			x += 1;
			return x + y;
		};
		
		System.out.println(sumThis.doIt(x, y));
		
	}
}
