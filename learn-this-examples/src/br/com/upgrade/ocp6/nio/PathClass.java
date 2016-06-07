package br.com.upgrade.ocp6.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathClass {

	public static void main(String[] args) {
		getPath();
		System.out.println(System.lineSeparator());
		iterate();
		System.out.println(System.lineSeparator());
		subpath();
		System.out.println(System.lineSeparator());
		resolve();
		System.out.println(System.lineSeparator());
		compare();
	}
	
	/**
	 * Exemplos de com recuperar um caminho/diretório ou arquivo com aa classe Path da API de NIO.2 do Java 7 e a
	 * interface Paths
	 */
	private static void getPath() {
		
		//recupera o diretório informado
		Path path = Paths.get(System.getProperty("user.home"), "Downloads");
		System.out.println("Path: "+ path);

		//podemos informar quantos arquivos ou caminhos que quizermos, pois o parâmetor de caminhos/arquivos é varargs
		path = Paths.get(System.getProperty("user.home"), "Download", "UPGRADE_OCP_1Z0_813.txt");
		
		//recupera o caminho raiz de um caminho
		Path root = path.getRoot();
		System.out.println("Root: "+ root);
		
		//recupera o diretório anterior
		Path parent = path.getParent();
		System.out.println("Parent: "+ parent);
		
	}
	
	/**
	 * Exemplo de como recuperar a quantidade de diretórios ou arquivos que compõe o caminho criado, como iterar sobre
	 * os mesmos ou recuperar um elemento específico.
	 */
	private static void iterate() {
		
		Path path = Paths.get(System.getProperty("user.home"), "Volumes/Apple_HD_1TB", "OCP8", "test1.txt", "test2.txt");
		System.out.println("Path: "+ path);
		
		//retorna a quantidade de caminhos no path
		System.out.println("Name count: "+ path.getNameCount());

		//a classe Path implementa a interface Iterable, portanto podemos iterar sobre os elementos do path
		//o mesmo que for(Path p : path) {...}
		path.forEach(p -> System.out.print("Path: "+p+", "));
		
		//recupera o segundo diretório/arquivo do caminho. Observe que a contagem começa com o index 0
		System.out.println("\nSecond path (first index is 0): " + path.getName(2));
				
	}
	
	/**
	 * Extraii um subpath a partir do path principal, através da contagem de nomes do mesmo. Observe que
	 * O índice de contagem dos nomes inicia-se em 0.
	 */
	private static void subpath() {
		Path path = Paths.get(System.getProperty("user.home") +  "/Volumes/Apple_HD_1TB/OPC8/test1.txt");
		System.out.println("Path: " + path);
		System.out.println("Subpath: "+ path.subpath(2,  4));
	}
	
	/**
	 * O método xxx permite que, a partir de um Path, adicionemos mais paths ao mesmo
	 */
	private static void resolve() {
		
		Path fixedPath = Paths.get(System.getProperty("user.home"), "Downloads");
		System.out.println("Fixed path: " + fixedPath);
		
		Path file1 = fixedPath.resolve("file1.txt");
		System.out.println("File 1: " + file1);
		
		Path path2 = fixedPath.resolve("Others");
		System.out.println("Path 2: " + path2);
		
		Path path3 = fixedPath.resolve(path2).resolve("path3.txt");  
		System.out.println("Path 3: " + path3);
		
		/*
		 * Path.resolveSibling(String s) converte uma string em um caminho, utilizando o caminho original.
		 * Exemplo: se temos um Path p com "/Users/[my user]/Downloads":
		 *  - Path p1 = p.resolveSibling("file2.txt");
		 *  - p1 vai ser "/Users/[my user]/file2.txt"
		 */
		Path file2 = fixedPath.resolveSibling("file2.txt");
		System.out.println("File 2: " + file2);
	}
	
	/**
	 * Utiliza a interface Files para comparar dois arquivos e então valida se os mesmos são iguais com a ajuda do 
	 * método Files.isSameFile(Path path1, Path path2) 
	 */
	private static void compare() {
		
		Path fixed = Paths.get(System.getProperty("user.home"), "Documents");
		
		Path file1 = fixed.resolve("same.txt");
		Path file2 = fixed.resolve("same.txt");
		
		try {
			//compara se os arquivos são iguais
			System.out.println("File 1 is the same then File 2? " + Files.isSameFile(file1, file2));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
