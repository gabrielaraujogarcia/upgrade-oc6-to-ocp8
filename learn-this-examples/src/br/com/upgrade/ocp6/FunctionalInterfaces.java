package br.com.upgrade.ocp6;

public class FunctionalInterfaces {

	public static void main(String[] args) {
		
		MyClass clazz = new MyClass();
		clazz.doSomeThing();
		clazz.doAnotherThing();
		
	}
 	
	/**
	 * Uma interface funcional possui apenas um método abstrato, desde que não seja um método sobrescrito
	 * da classe java.lang.Object pois os mesmos não de implementção obrigatória na classe que implementar
	 * esta interface. 
	 */
	@FunctionalInterface
	private interface MyFunctionalInterface {
		
		abstract void doSomeThing();
		
		//podemos adicionar métodos da classe Object sem alterar a condição de ser uma interface funcional
		@Override
		abstract String toString();
		
		@Override
		abstract boolean equals(Object obj);
		
		//também podemos adicionar métodos default e estáticos
		default void doAnotherThing() {
			System.out.println("All fine here");
		}
		
		static void doSomeThingUtil() {
			System.out.println("Implement some thing useful here...");
		}
		
	}
	
	/**
	 * Este código não vai compilar pois uma interface anotada com @FunctionalInterface não pode apresentar
	 * mais de um método abstrato. Por uma questão de compatibilidade com implementações anteriores ao Java 8,
	 * esta anotação não é obrigatória, mas lembre que uma interface com mais de um método abstrato não é uma 
	 * interface funcional. Esta é a regra e a regra é clara!!! Descomente e veja que o código não compila.
	 * @author ggarcia
	 *
	 */
	@FunctionalInterface
	private interface FunctionalInterfaceCompileError {
		
		abstract void doSomeThing();
		//abstract void doAnotherThing();
		
	}
	
	private static class MyClass implements MyFunctionalInterface {
		
		@Override
		public void doSomeThing() {
			System.out.println("I`m ready to do the Oracle 1Z0-813 OCP 8 upgrade exam");
			MyFunctionalInterface.doSomeThingUtil();
			MyFunctionalInterface.super.doAnotherThing();
		}
		
		
	}
	
}
