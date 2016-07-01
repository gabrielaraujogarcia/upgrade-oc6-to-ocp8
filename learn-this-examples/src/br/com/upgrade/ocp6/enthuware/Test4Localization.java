package br.com.upgrade.ocp6.enthuware;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

public class Test4Localization {

	public static void main(String[] args) {
		dateFormat();
		addToLocalDate();
		checkLocalTime();
		duration();
		doOperationsWithDateAndTime();
	}
	
	/**
	 * Apesar de compilar, este exercício é utilizado para demonstrar que devemos ficar atentos ao tipo de dados que 
	 * estamos formatando e qual o formatador que estamos utilizando, pois como apresentado neste exemplo, um LocalDate
	 * é utilizado para representar apenas uma data e o formatador trabalha com data e hora, o que irá resultar em uma
	 * exceção.
	 */
	private static void dateFormat() {	
		
		try {
			//vai lançar uma exception pois LocalDate só possui a representação da data e o formatador trata data e hora
			System.out.println(LocalDate.of(2016, Month.JANUARY, 31).format(DateTimeFormatter.ISO_DATE_TIME));
		} catch(UnsupportedTemporalTypeException e) {
			System.out.println("LocalDate can`t be formatted by DateTimeFormatter.ISO_DATE_TIME");
		}
		
	}
	
	/**
	 * Realiza uma operação de adição de dia e/ou mês e/ou ano a um LocalDate com o método plus e com auxílio
	 * da classe (e método) Period.of(ano, mes, dia). A classe Period não pode ser instânciada e deve ser utilizada
	 * a partir de seus métodos fábricas e métodos estáticos. 
	 * 
	 */
	private static void addToLocalDate() {
		
		//data agora
		LocalDate date = LocalDate.now();
		System.out.println("Date before: " + date);
		
		//Period.of(ano, mês, dias)
		date = date.plus(Period.of(0, 1, 5));
		System.out.println("Date after: " + date);
		
		//outra forma de realizar esta operação (atenção as operações e valores)
		System.out.println(LocalDate.parse("2016-07-01").minusMonths(-2).minusDays(1).plusYears(-1));
		
	}
	
	/**
	 * Operações entre duas unidades de tempo. 
	 */
	private static void checkLocalTime() {
		
		LocalTime now = LocalTime.now();
		LocalTime start = LocalTime.of(10, 15);
		
		long timeConsumed = 0;
		long timeToStart = 0;
		
		if(now.isAfter(start)) {
			//calcula a quantidade de horas entre os dois horários
			timeConsumed = start.until(now, ChronoUnit.HOURS);
		} else {
			timeToStart = now.until(start, ChronoUnit.HOURS);
		}
		
		System.out.println("Time to start: " + timeToStart);
		System.out.println("Time consumed: " + timeConsumed);
	}
	
	/**
	 * TODO
	 */
	private static void duration() {
		
		Duration d = Duration.ofDays(1);
		System.out.println(d);
		
		d = Duration.ofMinutes(0);
		System.out.println(d);
		
		Period p = Period.ofMonths(0);
		System.out.println(p);
		
	}
	
	private static void doOperationsWithDateAndTime() {
		
		//Instant representa um período no espaço de tempo
		Instant start = Instant.parse("2015-06-25T16:13:30.00z");
		start.plus(10, ChronoUnit.HOURS);
		//vai imprimir o mesmo que no start porquê não foi atribuido o resultado da soma em uma variável (start.plus)
		System.out.println(start);
		
		//Duration representa uma medida de tempo (hora, minutos e/ou segundos)
		Duration timeToCook = Duration.ofHours(1);
		Instant readyTime = start.plus(timeToCook);
		System.out.println(readyTime);
		
		//LocalDateTime representa a data e a hora
		LocalDateTime ldt = LocalDateTime.ofInstant(readyTime, ZoneId.of("GMT+2"));
		System.out.println(ldt);
		
	}
	
}
