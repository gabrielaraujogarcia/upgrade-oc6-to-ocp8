package br.com.upgrade.ocp6.enthuware;

import java.io.IOException;

public class Test2 {
	
	public static void main(String[] args) throws Exception {
		exercise1();
		exercise2();
		exercise4();
	}
	
	/**
	 * São adicionadas as exceções suprimidas as exeções lançadas no momento em que o recurso está
	 * sendo fechado
	 */
	private static void exercise1() {
		
		try {
			m2();
		} catch(Exception e) {
			Throwable[] ta = e.getSuppressed();
			for(Throwable t : ta) {
				System.out.println(t.getMessage());
			}
		}
		
	}
	
	private static void m1()  throws Exception{
		throw new Exception("Exception from m1");
	}
	
	private static void m2() throws Exception {
		
		try {
			m1();
		} catch(Exception e) {
			throw e;
		} finally {
			throw new RuntimeException("Exception from finally");
		}
		
		
	}
	
	private static void exercise2() {
		House ci = new MyHouse();
		System.out.println(ci.getAddress());
	}
	
	interface House{
		public default String getAddress(){
			return"101 Main Str";
		}
	}

	interface Bungalow extends House{
		public default String getAddress(){
			return "101 Smart Str";
		}
	}

	/**
	 * Não apresenta erro de compilação pois Bungalow é um House. Se não fosse,
	 * a classe MyHouse deveria implementar o método getAddress() pois este
	 * é comum nas duas interfaces. 
	 * @author ggarcia
	 */
	static class MyHouse implements Bungalow, House{
	
	}
	
	/**
	 * Não compila porquê NoSuchFileException herda/é subclasse de IOException
	 * @throws Exception 
	 */
//	private static void exercice3() {
//		
//		try(BufferedReader br = new BufferedReader(new FileReader(
//				System.getProperty("user.home") + "file.txt"))) {
//			
//			String line = null;
//			
//			while((line = br.readLine()) != null) {
//				System.out.println(line);
//			}
//			
//		} catch(NoSuchFileException | IOException | AccessDeniedException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	/**
	 * Vai criar a instância d1 e, ao criar o d2, vai lançar a exceção. Quando a exceção do
	 * d2 for lançada, irá finalizar o recurso d1 criado com sucesso e então imprimir a exceção
	 * lançada ao criar o d2, seguido da exceção suprimida quando fechou o d1
	 * @throws Exception
	 */
	private static void exercise4() throws Exception {
		
		try (Device d1 = new Device("D1"); Device d2 = new Device("D2")) {
			throw new Exception("test");
		}
		
	}
	
	private static class Device implements AutoCloseable { 
		String header = null;

		public Device(String name) throws IOException{
			header = name;
			if("D2".equals(name)) throw new IOException("Unknown");
			System.out.println(header +" Opened");
		}

		public String read() throws IOException{
			return "";
		}

		public void close() {	
			System.out.println("Closing device "+header);
			throw new RuntimeException("RTE while closing "+header);
		}
		
	}
	
}
