package br.com.upgrade.ocp6.enthuware;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Test1 {
	
	public static void main(String[] args) {
		relativize();
		localDate();
	}
	
	private static void relativize() {
		
		Path p1 = Paths.get("\\personal\\readme.txt");
		Path p2 = Paths.get("\\index.html"); 
		Path p3 = p1.relativize(p2);
		System.out.println(p3);
		
	}
	
	private static void localDate() {
		
		java.time.LocalDate dt = java.time.LocalDate
				.parse("2015-01-01")
				.minusMonths(1)
				.minusDays(1)
				.plusYears(1); 
		System.out.println(dt);
		
	}

}
