package br.com.upgrade.ocp6;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import com.sun.org.apache.xpath.internal.operations.UnaryOperation;

public class LambdaAPI {
	
	public static void main(String[] args) {
		
		exampleOfFunction();
		exampleOfConsumer();
		exampleOfSupplier();
		exampleOfPredicate();
		exampleOfUnaryOperator();
	}
	
	/**
	 * Uso da interface funcional java.util.consumer.Function<T, R>
	 * método principal: R apply(T t);
	 */
	public static void exampleOfFunction() {
		
		BigDecimal notFormatted = BigDecimal.valueOf(3.141592653);
		
		Function<BigDecimal, String> f = (BigDecimal param) -> { 
			param = param.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			return param.toString();
		};
		
		print(f.apply(notFormatted));
	}
	
	/**
	 * Uso da interface funcional java.util.function.Consumer<T>
	 * método principal: void accept(T t)
	 */
	public static void exampleOfConsumer() {
		
		//é o mesmo que Consumer<String> consumer = param -> print(param);
		Consumer<String> consumer = LambdaAPI::print; 		
		consumer.accept("Consumer will do something with this text");
		
	}

	/**
	 * Uso da interface funcional java.util.function.Supplier<T>
	 * Método principal: T get();
	 */
	public static void exampleOfSupplier() {

		//sintaxe simplificada chamando um método que executa uma operação e  
		//retorna um objeto T
		Supplier<Integer> supplier = LambdaAPI::sum;
		Integer result = supplier.get();
		print(result.toString());

		//sintaxe com código que executa uma operação em uma linha que retorna
		//ou resulta em um objeto T
		supplier = () -> 1;
		result += supplier.get();
		print(result.toString());
		
		//outra sintaxe para a mesma operação, agora com bloco de código 
		//e instrução de retorno
		supplier = () -> {
			int x = 1;
			return x;
		};
		
		result += supplier.get();
		print(result.toString());
		
		//Observe que o método T get() não recebe parâmetros, sendo assim, se utilizar 
		//um método com parâmetros ou informar algum parâmetro, o código não compila 
		//supplier = LambdaAPI::print;
		//supplier = (Integer param) -> 10 + 5;
		
		//se o retorno da operação get() na expressão lambda não for igual o retorno
		//esperado, o código não compila
		//supplier = () -> "" + 1;
	}
	
	private static void exampleOfPredicate() {
		
		Predicate<String> p = text -> text.equals("Some text");
		print("Predicate 1: " + p.test("Another text"));
		
		p = LambdaAPI::isThisTextGreaterThan10;
		System.out.println("Predicate 2: "+ p.test("ABCDEFGHIJKLMN"));
		
	}
	
	private static void exampleOfUnaryOperator() {
		
		UnaryOperator<Integer> uo = multiply -> multiply * multiply;
		Integer x = 10;
		
		x = uo.apply(x);
		print("UnaryOperator 1: " + x);
		
		uo = LambdaAPI::sum;
		x = uo.apply(x);
		print("UnaryOperator 2: " + x);
		
	}
	
	/**
	 * Escreve no terminal
	 * @param s
	 */
	private static void print(String s) {
		System.out.println(s);
	}
	
	/**
	 * Retorna uma soma qualquer
	 */
	private static Integer sum() {
		return 0 + 1;
	}
	
	/**
	 * Valida se o texto é maior que 10
	 * @param text
	 * @return
	 */
	private static boolean isThisTextGreaterThan10(String text) {
		return text.length() > 10;
	}
	
	/**
	 * Soma um inteiro com ele mesmo
	 * @param x
	 * @return
	 */
	private static Integer sum(Integer x) {
		return x + x;
	}
	
}
