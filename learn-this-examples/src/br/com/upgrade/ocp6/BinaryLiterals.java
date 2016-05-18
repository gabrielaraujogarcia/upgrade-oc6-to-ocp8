package br.com.upgrade.ocp6;

public class BinaryLiterals {

	public static void main(String[] args) {
		
		testYourself();
		examplesHexadecimal();
		examplesOctal();
		valid();
		invalid();
		
	}
	
	/**
	 * Tente responder antes de executar
	 */
	private static void testYourself() {
		
		int x = 1;
		boolean equals = false;
		
		if(x == 0b00_1) {
			equals = true;
		}
		
		System.out.println("Are x = 1 and 0b00_1 (1) equals? " + equals);
		equals = false;
		
		if(0b001 == 0x1) {
			equals = true;
		}
		
		System.out.println("Are 0b001 (1) and 0x1 (1) equals? " +  equals);
		equals = false;
		
		System.out.println("\n");
		
	}
	
	/**
	 * Exemplo de representações em hexadecimal
	 */
	private static void examplesHexadecimal() {
		
		int hex1 = 0x159;
		int hex2 = 0x1F82;
		int hex3 = -0x0AD9;
		
		System.out.println("Examples of hexadecimal:");
		System.out.println("(int) 0x159 = " + hex1);
		System.out.println("(int) 0x1F82 = " + hex2);
		System.out.println("(int) -0x0AD9 = " + hex3 + "\n");
		
	}
	
	/**
	 * Exemplo de representações em octal
	 */
	private static void examplesOctal() {
		
		int oct1 = 034;
		int oct2 = 001;
		int oct3 = -0777;
		
		System.out.println("Examples of octal:");
		System.out.println("(int) 034 = " + oct1);
		System.out.println("(int) 001 = " + oct2);
		System.out.println("(int) -0777 = " + oct3 + "\n");
		
	}
	
	/**
	 * Representações válidas no formato correto dos literais numéricos com underscore
	 */
	private static void valid() {
		
		int x = 0_77; 
		double y = 0_1_1;
		
		System.out.println("Examples of octal with underscore:");
		System.out.println("(int) 0_77 = "  + x);
		System.out.println("(double) 0_1_1 = "+ y + "\n");
		
		x = 0xFFF_FFF;
		y = 0X01_97_AD;
		
		System.out.println("Examples of hexadecimal with underscore:");
		System.out.println("(int) 0xFFF_FFF = "  + x);
		System.out.println("(double) 0X01_97_AD = "+ y + "\n");

		x = 1_123_987;
		y = 12.334_230;
		
		System.out.println("Examples of decimal with underscore:");
		System.out.println("(int) 1_123_987 = "  + x);
		System.out.println("(double) 12.334_230 = "+ y + "\n");
		
		x = 0b10100_1;
		y = 0B011_001;
		
		System.out.println("Examples of binary with underscore:");
		System.out.println("(int) 0b10100_1 = "  + x);
		System.out.println("(double) 0B011_001 = "+ y + "\n");
		
	}
	
	/**
	 * Exemlos de como não fazer.
	 */
	private static void invalid() {
	
		System.out.println("Invalid representations:");
		System.out.println("0b_1011 is invalid because the _ is before first number.");
		System.out.println("0x10FF_ is invalid because the _ is after last number.");
		System.out.println("0b1_0_2_0 is invalid because the 0b ins`t expressed in binary number sistem (0 or 1).");
		System.out.println("0_08 is invalid because octal number system contains only numbers 0 to 7.");
		System.out.println("\n");
		
	}
	
}

