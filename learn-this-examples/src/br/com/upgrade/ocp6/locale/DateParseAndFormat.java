package br.com.upgrade.ocp6.locale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateParseAndFormat {

	public static void main(String[] args) {
		
		parseDate();
		System.out.println(System.lineSeparator());
		
		formatDate();
		System.out.println(System.lineSeparator());

	}
	
	/**
	 * Cria uma data a partir de uma data no formato String e o pattern desta data
	 */
	private static void parseDate() {
		
		String in = "18/05/1988 21:59";
		String pattern = "dd/MM/yyyy HH:mm";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		
		try {
			
			LocalDate date = LocalDate.parse(in, formatter);
			System.out.println(date);
			
			//LocalDateTime também possui o método parse que recebe o formatador
			LocalDateTime dateTime = LocalDateTime.parse(in, formatter);
			System.out.println(dateTime);
			
			in = "1980-07-19";
			date = LocalDate.parse(in);
			System.out.println(date);
			
			in = "19590709";
			date = LocalDate.parse(in, DateTimeFormatter.BASIC_ISO_DATE);
			System.out.println(date);
			
			in = "2016-06-27";
			date = LocalDate.parse(in, formatter); //DateTimeParseException here
			
		} catch(DateTimeParseException e ){ 
			System.out.println("String date " + in + " and pattern " + pattern + "does not match!");
		}
		
	}
	
	/**
	 * Converte a data em uma String de acordo com o pattern desejado
	 */
	private static void formatDate() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss", new Locale("pt"));
		System.out.println(formatter.format(LocalDateTime.now()));
		
		formatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm:ss", new Locale("en"));
		System.out.println(formatter.format(LocalDateTime.now()));
		
		formatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm:ss");
		System.out.println(formatter.format(LocalDateTime.now()));

	}
	
	
	
	
	
	
}
