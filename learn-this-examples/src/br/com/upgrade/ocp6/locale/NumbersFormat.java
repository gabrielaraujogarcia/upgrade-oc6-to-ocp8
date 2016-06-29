package br.com.upgrade.ocp6.locale;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class NumbersFormat {
		
	public static void main(String[] args) {
		
		displayNumber();
		System.out.println(System.lineSeparator());
		
		displayCurrency();
		System.out.println(System.lineSeparator());
		
		currencyMethods();
		System.out.println(System.lineSeparator());
		
		displayPercent();
		System.out.println(System.lineSeparator());
		
	}
	
	/**
	 * Formata um valor numérico de acordo com o Locale definido.
	 * Os métodos para esta operação são o NumberFormat.getCurrencyInstance(Locale) para quando 
	 * for um ponto flutuante ou o NumberFormat.getInstance(Locale) para quando for um inteiro
	 */
	static private void displayNumber() {

		Integer quantity = new Integer(123456);
	    Double amount = new Double(345987.246);

	    Locale locale = new Locale("pt");
		NumberFormat numberFormatter =  NumberFormat.getInstance(locale);
		String quantityOut = numberFormatter.format(quantity);
	    System.out.println(quantityOut + "   " + locale.toString());
	    
	    locale = Locale.FRANCE;
	    numberFormatter = NumberFormat.getCurrencyInstance(locale);
	    String amountOut = numberFormatter.format(amount);
	    System.out.println(amountOut + "   " + locale.toString());
	    
	}
	
	/**
	 * Formata um valor numérico para uma determianda moeda definida com o Locale.
	 * O método para esta operação é o NumberFormat.getCurrencyInstance(Currency)
	 */
	static private void displayCurrency() {

		Locale currentLocale = Locale.getDefault();
	    Double currencyAmount = new Double(9876543.21);
	    
	    Currency currentCurrency = Currency.getInstance(currentLocale);
	    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);

	    System.out.println(
	        currentLocale.getDisplayName() + ", " +
	        currentCurrency.getDisplayName() + ": " +
	        currencyFormatter.format(currencyAmount));
	}
	
	/**
	 * Métodos utilitários na classe Currency
	 */
	private static void currencyMethods() {
		
		Currency.getAvailableCurrencies()
			.stream()
			.forEach(currency ->{
				System.out.println("Name: "+ currency.getDisplayName() + 
						"\nSymbol: " + currency.getSymbol() +  
						"\nCode: " + currency.getCurrencyCode() +
						"\n");	
			});
		
	}
	
	/**
	 * Formata um valor numérico para o valor correspondente em porcentagem no formato do Locale.
	 * O método para esta operação é o NumberFormat.getPercentInstance(Locale)
	 */
	static public void displayPercent() {

		Locale currentLocale = Locale.getDefault();
	    Double percent = new Double(0.75);
	    NumberFormat percentFormatter;
	    String percentOut;

	    percentFormatter = NumberFormat.getPercentInstance(currentLocale);
	    percentOut = percentFormatter.format(percent);
	    System.out.print(percentOut + "   " + currentLocale.toString());
	    
	}
	
}
