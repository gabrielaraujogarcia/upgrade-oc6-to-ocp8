package br.com.upgrade.ocp6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiamondDeclaration {

	public static void main(String[] args) {
		
		//antes do Java 7
		List<Map<String, List<Integer>>> listaAntesJava7 = new ArrayList<Map<String, List<Integer>>>();
		
		//depois do Java 7
		List<Map<String, List<Integer>>> listDepoisJava7_1 = new ArrayList<>();
		
		//"raw" type: se nao especificar o tipo da instancia compila mas apresenta aviso
		List<Map<String, List<Integer>>> listDepoisJava7_2 = new ArrayList();
		
	}
	
	
	
}
