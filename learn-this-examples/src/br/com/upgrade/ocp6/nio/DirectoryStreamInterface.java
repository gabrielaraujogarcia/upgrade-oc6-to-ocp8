package br.com.upgrade.ocp6.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryStreamInterface {

	private static final Path fixed = Paths.get(System.getProperty("user.home"), "Downloads");
	
	public static void main(String[] args) {
		iteratesOverDirectory();
		System.out.println(System.lineSeparator());
		iteratesWithFilter();
	}
	
	/**
	 * Podemos percorrer todos os arquivos de um diretório utilizando a interface DirectoryStream<T>
	 * junto com o método newDirectoryStream(path) da classe Files. DirectoryStream precisa ser fechado
	 * após a operação e por isto foi utilizado um try-with-resources, já que o mesmo implementa a
	 * interface Closable ou AutoClosable.
	 */
	private static void iteratesOverDirectory() {
		
		try (DirectoryStream<Path> itens = Files.newDirectoryStream(fixed)) {
			itens.forEach(path -> System.out.println(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Podemos filtrar o conteúdo que queremos recuperar
	 */
	private static void iteratesWithFilter() {
		
		try (DirectoryStream<Path> itens = Files.newDirectoryStream(fixed, "*.txt")) {
			itens.forEach(path -> System.out.println(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
 	
}
