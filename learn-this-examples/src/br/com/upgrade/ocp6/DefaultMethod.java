package br.com.upgrade.ocp6;

public class DefaultMethod {
	
	/**
	 * A partir do Java 8, as interfaces podem declarar metodos default que n�o precisam ser implementados pela classe 
	 * concreta, mas podem ser sobrescritos normalmente. Podemos tamb�m declarar metodos estaticos, ideal para definir 
	 * c�digos utilit�rios como validar se um objeto � nulo. Mesmo sendo p�blicos, m�todos est�ticos em interfaces s�
	 * podem ser acessados na pr�pria interface ou atrav�s da mesma, como se fosse uma classe utilit�ria.
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
			System.out.println("You call the method doSomeThingUtil implemented at ISomeThing.java. Use this method to do "
					+ "some thing util!");
		}
	}
	
	private interface IAnotherThing {
	
		public default void doSomeThing() {
			System.out.println("IAnotherThing says: I can do another thing or i can be overridden...");
		}
		
	}
	
	/**
	 * Perceba que n�o foi necess�rio implementar o m�todo doAnotherThing que deixou de ser abstratos quando foi 
	 * assinado como m�todo default. Vale ressaltar que o m�todo doSomeThingUtil n�o pode ser implementado por 
	 * ser um m�todo est�tico da interface e que o mesmo � vis�vel apenas na interface ou atrav�s da mesma.
	 * @author Gabriel
	 *
	 */
	private static class FirstClass implements ISomeThing {
		
		public FirstClass() {
			//para acessar os m�todos est�ticos da interface, como se fosse uma classe utilit�ria
			ISomeThing.doSomeThingUtil();
		}
		
	}
	
	/**
	 * Quando implementamos duas interfaces diferentes mas que possuem m�todos com assinaturas iguais, precisamos
	 * obrigat�riamente sobrescrever estes m�todos que possuem mesma assinatura pois o compilador n�o consegue identificar  
	 * qual m�todo ele deve executar.  Se apagar o m�todo doSomeThing() sobrescrito, o c�digo n�o compila. N�o se esque�a
	 * de estudar tamb�m a sintaxe para chamar o m�todo da interface que deseja executar, conforme o exemplo abaixo no
	 * m�todo sobrescrito doSomeThing()
	 * @author Gabriel
	 *
	 */
	private static class SecondClass implements ISomeThing, IAnotherThing {
		
		@Override
		public void doSomeThing() {
			
			//sintaxe para acessar o m�todo default da interface ISomeThing
			ISomeThing.super.doSomeThing();

			//sintaxe para acessar o m�todo default da interface ISomeThing
			IAnotherThing.super.doSomeThing();
			
			//regra geral: [Interface].super.[M�todo]();
		}
		
	}
	
	/**
	 * Podemos sobrescrever normalmente um m�todo default
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
	 * Esta classe � uma classe filha de ThirdClass, que sobrescreve o m�todo doSomeThing() da interface ISomeThing.
	 * Esta classe tamb�m implementa a interface IAnotherThing. A quest�o a ser respondida aqui �: qual implementa��o
	 * do m�todo doSomeThing ser� executada? A resposta � que, uma superclasse sempre tem preced�ncia sobre as interfaces. 
	 * @author Gabriel
	 *
	 */
	private static class FourthClass extends ThirdClass implements IAnotherThing {
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("FirstClass implements ISomeThing and call method ISomeThing.super.doSomeThingUtil() on your"
				+ "constructor:");
		FirstClass fc = new FirstClass();
		fc.doSomeThing();
		fc.doAnotherThing();
		System.out.println("\n");
		
		System.out.println("SecondClass implements ISomeThing and IAnotherThing. It's need to overridden the method"
				+ " doSomeThing() to code compile with no errors:");
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
