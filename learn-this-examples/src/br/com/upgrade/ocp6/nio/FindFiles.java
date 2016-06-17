package br.com.upgrade.ocp6.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindFiles {
	
	private static final Path fixed = Paths.get(System.getProperty("user.home"), "Downloads");
	
	public static void main(String[] args) throws IOException {
		doPathMatcher();
		//find();
//		System.out.println(System.lineSeparator());
		lines();
		System.out.println(System.lineSeparator());
		walk();
	}
	
	private static void doPathMatcher() {
		
		Path html = fixed.resolve("tutorial.html");
		Path hasNumber = fixed.resolve("ocp8.java");
		
		validate(fixed);
		validate(html);
		validate(hasNumber);
		
	}
	
	/**
	 * PathMatcher é uma interface funcional que aceita uma regex ou glob  como parâmetro e então
	 * através do método matches valida se o Path informado atende a expressão regex ou glob informada
	 * @param path
	 */
	private static void validate(Path path) {
		
		//PathMatcher aceita sintaxe Glob ou regex. O Glob abaixo procura por arquivos que terminam
		//com .html, ou contém um número no nome. Para saber mais sobre expressões Glob, acessar o 
		//link a seguir e procurar pela explicação de Glob e os patterns do mesmo:
		//https://docs.oracle.com/javase/tutorial/essential/io/fileOps.html#glob
		PathMatcher pathMatcher1 = FileSystems.getDefault().getPathMatcher("glob:*.html");
		PathMatcher pathMatcher2 = FileSystems.getDefault().getPathMatcher("glob:*[0-9]*");
		
		Path name = path.getFileName();

		if(name != null) {
	 
			System.out.println("Path: " + path);
			System.out.println("File name: " + name);
			System.out.println("The path is a html file? " + pathMatcher1.matches(name));
			System.out.println("The path contains a number in it's name? " + pathMatcher2.matches(name));
			System.out.println(System.lineSeparator());
			
		}
		
	}
	
	private static void find() throws IOException {
	    
		Path start = fixed.resolve("ocp8.java");
	    int maxDepth = 5;
	    
	    if(Files.exists(start)) {
	    	
	    	try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) -> String.valueOf(path).endsWith(".java"))) {
		        String joined = stream
		                .sorted()
		                .map(String::valueOf)
		                .collect(Collectors.joining("; "));
		        System.out.println("Found: " + joined);
		    }
	    	
	    } else {
	    	System.out.println("The file " + start + " does not exists!");
	    }
	    
	}
	
	/**
	 * Lê um arquivo com ajuda do método Files.lines(path) e imprime as mesmas
	 * @throws IOException
	 */
	private static void lines() throws IOException {
	    
		Path start = fixed.resolve("ocp8.java");
	    
	    if(Files.exists(start)) {
	    	
	    	//try-with-resrouces nao exige as cláusulas catch e/ou finally
	    	try (Stream<String> lines = Files.lines(start)) {
		        lines.forEach(p -> System.out.println(p));
		    }
	    	
	    	//também podemos ler todas as linhas de uma só vez e armazenar em um array
	    	List<String> list = Files.readAllLines(start);
	        list.stream().forEach(System.out::println);
	    	
	    	
	    } else {
	    	System.out.println("The file " + start + " does not exists!");
	    }
	    
	}
	
	/**
	 * Percorre os arquivos de acordo com a profundidade informada
	 * @throws IOException
	 */
	public static void walk() throws IOException {
	    
		Path start = fixed;//Paths.get(System.getProperty("user.home"), "Documents");
	    int maxDepth = 1;
	    
	    try (Stream<Path> stream = Files.walk(start, maxDepth)) {
	        stream.forEach(System.out::println);
	    }
	    
	    System.out.println(System.lineSeparator());
	    maxDepth++;
	    
	    try (Stream<Path> stream = Files.walk(start, maxDepth)) {
	        stream.forEach(System.out::println);
	    }
	    
	    System.out.println(System.lineSeparator());
	    maxDepth++;
	    
	    try (Stream<Path> stream = Files.walk(start, maxDepth)) {
	        stream.forEach(System.out::println);
	    }
	}

}
