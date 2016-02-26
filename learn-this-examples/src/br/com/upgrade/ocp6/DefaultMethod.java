package br.com.upgrade.ocp6;

public class DefaultMethod {
	
	/**
	 * A partir do Java 8, as interfaces podem declarar metodos default que não precisam ser implementados pela classe 
	 * concreta, mas podem ser sobrescritos normalmente. Podemos também declarar metodos estáticos, ideal para definir 
	 * códigos utilitários como validar se um objeto é nulo. Mesmo sendo públicos, métodos estáticos em interfaces só
	 * podem ser acessados na própria interface ou através da mesma, como se fosse uma classe utilitária.
	 * @author Gabriel
	 *
	 */
	private interface ISomeThing {
		
		public default void doSomeThing() {
			System.out.println("ISomeThing says: I can do some thing or i can be overridden...");
		}
		
		public default void doAnotherThing() {
			System.out.println("ISomeThing says: You can call this method directly from any instance of a concreted class "
					+ "that implement me!");
		}
		
		public static void doSomeThingUtil() {
			System.out.println("You call the method doSomeThingUtil implemented at ISomeThing.java by using "
					+ " ISomeThing.doSomeThingUtil. Use this method to do some thing util!");
		}
	}
	
	private interface IAnotherThing {
	
		public default void doSomeThing() {
			System.out.println("IAnotherThing says: I can do another thing or i can be overridden...");
		}
		
	}
	
	/**
	 * Perceba que não foi necessário implementar o método doSomeThing que deixou de ser abstratos quando foi 
	 * assinado como método default. Vale ressaltar que o método doSomeThingUtil não pode ser implementado por 
	 * ser um método estático da interface e que o mesmo é visível apenas na interface ou através da mesma.
	 * @author Gabriel
	 *
	 */
	private static class FirstClass implements ISomeThing {
		
		public FirstClass() {
			//para acessar os métodos estáticos da interface, como se fosse uma classe utilitária
			ISomeThing.doSomeThingUtil();
		}
		
	}
	
	/**
	 * Quando implementamos duas interfaces diferentes mas que possuem metodos com assinaturas iguais, precisamos
	 * obrigatoriamente sobrescrever estes métodos que possuem mesma assinatura pois o compilador não consegue identificar  
	 * qual método ele deve executar.  Se apagar o método doSomeThing() sobrescrito, o código não compila. não se esqueça
	 * de estudar também a sintaxe para chamar o método da interface que deseja executar, conforme o exemplo abaixo no
	 * método sobrescrito doSomeThing()
	 * @author Gabriel
	 *
	 */
	private static class SecondClass implements ISomeThing, IAnotherThing {
		
		@Override
		public void doSomeThing() {
			
			//sintaxe para acessar o método default da interface ISomeThing
			ISomeThing.super.doSomeThing();

			//sintaxe para acessar o método default da interface ISomeThing
			IAnotherThing.super.doSomeThing();
			
			//regra geral: [Interface].super.[método]();
		}
		
	}
	
	/**
	 * Podemos sobrescrever normalmente um método default
	 * @author Gabriel
	 *
	 */
	private static class ThirdClass implements ISomeThing {
		
		@Override
		public void doSomeThing() {
			System.out.println("ThirdClass overridden the method doSomeThing with this code. Now it will call the method "
					+ "doSomeThing from ISomeThing.java");
			ISomeThing.super.doSomeThing();
		}
		
	}
	
	/**
	 * Esta classe é uma classe filha de ThirdClass, que sobrescreve o método doSomeThing() da interface ISomeThing.
	 * Esta classe também implementa a interface IAnotherThing. A questão a ser respondida aqui é: qual implementação
	 * do método doSomeThing será executada? A resposta é que, uma superclasse sempre tem precedência sobre as interfaces. 
	 * @author Gabriel
	 *
	 */
	private static class FourthClass extends ThirdClass implements IAnotherThing {
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("FirstClass implements ISomeThing and call method ISomeThing.super.doSomeThingUtil() on your "
				+ "constructor:");
		FirstClass fc = new FirstClass();
		fc.doSomeThing();
		fc.doAnotherThing();
		System.out.println("\n");
		
		System.out.println("SecondClass implements ISomeThing and IAnotherThing. It's need to overridden the method "
				+ "doSomeThing() to code compile with no errors:");
		SecondClass sc = new SecondClass();
		sc.doSomeThing();
		System.out.println("\n");
		
		System.out.println("ThirdClass implements ISomeThing and overriden the method doSomeThing, "
				+ "but it's not mandatory to do:");
		ThirdClass tc = new ThirdClass();
		tc.doSomeThing();
		System.out.println("\n");
		
		System.out.println("FourthClass extends ThirClass and implements IAnotherThing interface. Which implementation of "
				+ "doSomeThing will be executed?\nThe implementation of IAnotherThing interface or the implementation of "
				+ "super class? Let's see:");
		FourthClass foc = new FourthClass();
		foc.doSomeThing();
		
	}
	
	
	
}
