package br.com.upgrade.ocp6;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TryWithResources {
	
	public static void main(String[] args) {
		
		writeFile("tryWithResources.txt", "Somethink should be written here.");
		writeFile("tryWithResources.txt", "Tell me more about somethink that should be written here.");
		
		testMyResource();
		checkWhatsFirst();
		
	}
	
	/**
	 * Escreve um arquivo no diretório de documentos do usuário logado no computador e finaliza automaticamente
	 * os recursos Java de escrita do arquivo.
	 * @param fileWithExtension
	 * @param text
	 */
	private static void writeFile(String fileWithExtension, String text) {
		
		String path = System.getProperty("user.home") + File.separator + "Documents";
		File file = new File(path + File.separator + fileWithExtension);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		
		//try-with-resources que fecha automaticamente os recursos fw e bw após a execução do bloco
		try (FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);) {
			
			if(!file.exists()) {
				file.createNewFile();
			} 
			
			text = "(" + sdf.format(new Date()) + ") " + text;
			
			bw.newLine();
			bw.write(text);
			
			System.out.println("File written!");
			
		} catch (IOException e) {
			//throw new IOException("File can not be created.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Realiza o teste do recurso que criamos log abaixo, apresentando a mensagem do método close() assim que o
	 * bloco try finaliza seu processamento e então o MyResource é finalizado.
	 */
	private static void testMyResource() {
		
		System.out.println("\n");
		
		/* Perceba que será escrito no console "It`s over!" apenas uma vez e que não será lançada uma exceção 
		 * de NullPointerException, pois recursos inicializados com null não são finalizados. Os recursos são
		 * instânciados da esquerda para direita e finalizados da direita para a esquerda
		 */
		try (MyResource mr = new MyResource(); MyResource nullResource = null) {
			System.out.println("try-with-resour try to do somethink..");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Os recursos são instânciados da esquerda para direita e finalizado da dirita para a esquerda, conforme descrições
	 * no console.
	 */
	private static void checkWhatsFirst() {
		
		System.out.println("\n");
		
		// Os recursos são instânciados da esquerda para direita e finalizados da direita para a esquerda
		System.out.println("Create resources instance:");
		
		try (MyResource mr = new MyResource(); AnotherResource ar = new AnotherResource()) {
			System.out.println("Finish the instances:");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Este método te mostra o que você não pode fazer, estude com atenção
	 */
	private static void dontDoThis() {
		
		//recursos declarados dentro do try-with-resources são implicitamente final, portanto nao podemos
		//atribuir outros valores para os mesmos
		try (MyResource mr = new MyResource(); AnotherResource ar = new AnotherResource()) {
			System.out.println("Resources declared at try-with-resources are final, then next line can not be compiled.");
			//remova o comentario da linha abaixo e veja se irá compilar
			//mr = new MyResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Podemos criar a nossa propria class de recurso apenas implementando a interface java.lang.AutoClosable ou
	 * java.io.Closeable
	 * @author ggarcia
	 *
	 */
	private static class MyResource implements AutoCloseable {
		
		public MyResource() {
			System.out.println(" - MyResource: There we go!");
		}
		
		@Override
		public void close() throws Exception {
			System.out.println(" - MyResource: It`s over!");
		}
		
	}
	
	/**
	 * Recurso que implementa a interface Closeable
	 * @author ggarcia
	 *
	 */
	private static class AnotherResource implements Closeable {
		
		public AnotherResource() {
			System.out.println(" - AnotherResource: My turn!");
		}
		
		@Override
		public void close() {
			System.out.println(" - AnotherResource: Ok i`m done...");
		}
		
	}
	
}