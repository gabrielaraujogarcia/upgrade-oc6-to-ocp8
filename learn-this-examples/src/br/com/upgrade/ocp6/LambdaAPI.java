package br.com.upgrade.ocp6;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaAPI {
	
	public static void main(String[] args) {
		
		exampleOfFunction();
		exampleOfConsumer();
	}
	
	/**
	 * Uso da interface funcional java.util.consumer.Function<T, R>
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
	 */
	public static void exampleOfConsumer() {
		
		//é o mesmo que Consumer<String> consumer = param -> print(param);
		Consumer<String> consumer = LambdaAPI::print; 		
		consumer.accept("Consumer will do something with this text");
		
	}
	
	/**
	 * Escreve no terminal
	 * @param s
	 */
	private static void print(String s) {
		System.out.println(s);
	}
	
	
}
