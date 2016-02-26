package br.com.upgrade.ocp6;

public class CatchMultipleExcetions {

	public static void main(String[] args) {
		multiCatch();
	}
	
	private static void multiCatch() {
		
		try {
			System.out.println(10/0);
		} catch (ArithmeticException | IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		
	}

//	/**
//	 * TODO: DESCOMENTAR PARA VERIFICAR O QUE ACONTECE!
//	 * Não podemos tratar uma exceção filha de uma exceção pai já tratada na cláusula catch. Em outras palavras,
//	 * ChildException extends ExceptionA e a ExceptionA já está sendo tratada e com isto todas as excetions filhas
//	 * de ExceptionA são implicitamente tratadas. 
//	 */
//	private static void dontDoThis() {
//		
//		try {
//
//			System.out.println("Do not compile!");
//			throw new ExcetionA();
//			
//		} catch (ExcetionA | ChildException ex) {
//			ex.printStackTrace();
//		}
//		
//	}
//	
//	private static class ExcetionA extends Exception {
//		
//	}
//	
//	private class ChildException extends ExcetionA {
//		
//	}
}
