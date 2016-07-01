package br.com.upgrade.ocp6.enthuware;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Test3 {

	public static void main(String[] args) {
	 	currencyFormat();
	 	packages();
	}
	
	private static void currencyFormat() {
		
		double amount = 53000.35;
		Locale locale = new Locale("jp", "JP");
		
		//tipos de formatadores possiveis para formatar o valor como Currency
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		System.out.println(formatter.format(amount));
		
		formatter = DecimalFormat.getCurrencyInstance(locale);
		System.out.println(formatter.format(amount));
		
	}
	
	/**
	 * Apenas para ilustrar 2 dos métodos utilizados no exercicio de ResourceBundle.
	 * Observação: o ResourceBundle deve ficar no classpath da aplicação
	 */
	@SuppressWarnings("unused")
	private static void resourceBundle() {
		
		ResourceBundle bundle = ResourceBundle.getBundle("");
		
		//Um ResourceBundle pode retornar um objeto a partir da chave
		Object obj = bundle.getObject("key1");
		
		//E também pode retornar um Array de Strings
		String[] strings = bundle.getStringArray("key2");
		
	}
	
	/**
	 * Atenção nos pacotes das classes que serão avaliadas na prova, pois questões que questionam
	 * os importes corretos das classes são comuns e consideradas muito fácil, portanto não podemos
	 * errar! Exemplos:
	 */
	private static void packages() {
		
		java.util.Date date = new java.util.Date();
		java.text.DateFormat df = java.text.DateFormat.getInstance();
		
	}
	
}
