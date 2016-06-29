package br.com.upgrade.ocp6.locale;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateAndTimeFormat {
	
	public static void main(String[] args) {
		
		dateFormat();
		System.out.println(System.lineSeparator());
		
		timeFormat();
		System.out.println(System.lineSeparator());
		
		dateTimeFormat();
		
	}
	
	/**
	 * Formata uma data utilizando o DateFormat.getDateInstance(). 
	 * Os possíveis estilos de formatação (DateFormat.[ESTIL]) são:
	 * DEFAULT
	 * SHORT
	 * MEDIUM
	 * LONG
	 * FULL
	 */
	private static void dateFormat() {
		
		Date now = new Date();
		Locale locale = Locale.US;
		
		//sem style e sem locale
		System.out.println(DateFormat.getDateInstance().format(now));
		
		//com style e sem locale
		System.out.println(DateFormat.getDateInstance(DateFormat.LONG).format(now));
		
		//com style e com locale
		System.out.println(DateFormat.getDateInstance(DateFormat.FULL, locale).format(now));
		
	}
	
	/**
	 * Formata uma data utilizando o DateFormat.getTimeInstance(). 
	 * Os possíveis estilos de formatação (DateFormat.[ESTIL]) são:
	 * DEFAULT
	 * SHORT
	 * MEDIUM
	 * LONG
	 * FULL
	 */
	private static void timeFormat() {
		
		Date now = new Date();
		Locale locale = Locale.US;
		
		//sem style e sem locale
		System.out.println(DateFormat.getTimeInstance().format(now));
		
		//com style e sem locale
		System.out.println(DateFormat.getTimeInstance(DateFormat.LONG).format(now));
		
		//com style e com locale
		System.out.println(DateFormat.getTimeInstance(DateFormat.MEDIUM, locale).format(now));
		
	}
	
	/**
	 * Formata uma data utilizando o DateFormat.getDateTimeInstance(). 
	 * Os possíveis estilos de formatação (DateFormat.[ESTIL]) são:
	 * DEFAULT
	 * SHORT
	 * MEDIUM
	 * LONG
	 * FULL
	 */
	private static void dateTimeFormat() {
		
		Date now = new Date();
		Locale locale = Locale.US;
		
		//sem style e sem locale
		System.out.println(DateFormat.getDateTimeInstance().format(now));
		
		//com style (da data e da hora) e sem locale
		System.out.println(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM)
				.format(now));
		
		//com style (da data e da hora) e com locale
		System.out.println(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.FULL, locale)
				.format(now));
		
	}
	
}
