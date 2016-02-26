package br.com.upgrade.ocp6;

public class SwitchWithString {

	public static void main(String[] args) {
		
		caseOfSuccess("Case 1");
		caseOfSuccess("CasE 2");
		caseOfNullPointerException();
		
	}
		
	/**
	 * Importante lembrar de validar a String que será validada para não acusar NullPointerException
	 * @param param
	 * @throws IllegalArgumentException
	 */
	private static void caseOfSuccess(String param) throws IllegalArgumentException {
		
		if(param == null) {
			throw new IllegalArgumentException("param can not be null");
		}
		
		switch (param) {
			
			case "Case 1":
				System.out.println("Case 1");
				break;

			case "Case 2":	
				System.out.println("Case 2");
				break;
			
			default:
				System.out.println("Switch with String is case sensitive too. Check your param.");	
				break;
				
		}
		
	}
	
	/**
	 * A execução do switch irá lançar uma exceção NullPointerException porquê a String
	 * check "não foi validada" 
	 */
	private static void caseOfNullPointerException() {
		
		String check = null;
		
		try {
			
			switch (check) {
			
				case "Somethink":
					break;
					
				default:
					break;
				
			}
			
		} catch (Exception e) {
			System.out.print("This exception is expected because the String check is null: ");
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * Este exemplo é para ilustrar que não podemos utilizar a mesma String em duas verificações
	 * (case) diferente. Se descomentar, não irá compilar.
	 * @param param
	 */
//	private static void repeatedCaseCondition(String param) {
//		
//		switch (param) {
//		
//			case "Case 1":
//				System.out.println("Case 1");
//				break;
//	
//			case "Case 1":	
//				System.out.println("Case 2");
//				break;
//			
//			default:
//				System.out.println("Nothink to do");	
//				break;
//			
//		}
//		
//	}
	
}
