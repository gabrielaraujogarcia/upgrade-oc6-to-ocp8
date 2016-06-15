package br.com.upgrade.ocp6.nio;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitorInterface {

	private static final Path fixed = Paths.get(System.getProperty("user.home"), "Downloads");
	
	public static void main(String[] args) {
		walk();
	}
	
	/**
	 * Percorre toda a árvore de diretórios e arquivos definidos no path de início e então executa as operações
	 * sobrescritas da super classe SimpleFileVisitor<Path> que por sua vez implementa a interface  FileVisitor<T>
	 */
	private static void walk() {
		
		try {
			Files.walkFileTree(fixed, new PrintFiles());
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * https://docs.oracle.com/javase/tutorial/essential/io/walk.html
	 * Classe que implementa a interface SimpleFileVisitor e que, para cada visita a um diretório/arquivo na árvore 
	 * de caminhos, intercepta as operações de pré-visita e pós-visita (preVisitDirectory,  postVisitDirectory, visitFile
	 * e visitFileFailed) ao diretório ou arquivo e executa o bloco de código implementado nestes métodos.  
	 * Todos os métodos sobrecarregados retornam um FileVisitResult, sendo este um enum com as seguintes opções:
	 *  - CONTINUE continua para os próximos diretórios
	 *  - TERMINATE interrompe o andamento das visitas
	 *  - SKIP_SUBTREE ignora os subdiretórios
	 *	- SKIP_SIBLINGS ignora os diretórios irmãos
	 */
	private static class PrintFiles extends SimpleFileVisitor<Path> {

		/**
		 * Executa quando visita uma arquivo
		 */
		@Override
	    public FileVisitResult visitFile(Path file,
	                                   BasicFileAttributes attr) {
	        if (attr.isSymbolicLink()) {
	            System.out.format("Symbolic link: %s ", file);
	        } else if (attr.isRegularFile()) {
	            System.out.format("Regular file: %s ", file);
	        } else {
	            System.out.format("Other: %s ", file);
	        }
	        
	        System.out.println("(" + attr.size() + "bytes)");
	        return CONTINUE;
	    }

		/**
		 * Executa quando está para visitar um diretório
		 */
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			System.out.println("Pre visit directory " + dir);
			return super.preVisitDirectory(dir, attrs);
		}
		
	   /**
	    * Executa após visitar um diretório
	    */
	    @Override
	    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
	        System.out.format("Directory: %s%n", dir);
	        return CONTINUE;
	    }

	    /**
	     * Executa quando ocorreou algum problema na visita do arquivo
	     */
	    @Override
	    public FileVisitResult visitFileFailed(Path file, IOException exc) {
	        System.err.println(exc);
	        return CONTINUE;
	    }
	}
	
}
