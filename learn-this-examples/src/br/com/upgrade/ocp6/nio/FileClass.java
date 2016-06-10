package br
.com.upgrade.ocp6.nio;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileClass {

	private static final Path fixed = Paths.get(System.getProperty("user.home"), "Downloads");
	
	public static void main(String[] args) {
		
		System.out.println("****** CHECK ******");
		check();
		System.out.println(System.lineSeparator());
		
		System.out.println("****** DELETE ******");
		doDelete();
		System.out.println(System.lineSeparator());
		
		System.out.println("****** COPY ******");
		doCopy();
		System.out.println(System.lineSeparator());
		
	}

	/**
	 * Na API de NIO.2 do Java 7, podemos a partir da classe abstrata Files, verificar:
	 * - Se um arquivo ou diretório existe, utilizando o método exists;
	 * - Se um arquivo ou diretório não existe, utilizando o método notExists;
	 * Observação: mesmo que a operação de validação da existência dos arquivos resulte em false
	 * não podemos afirmar que o arquivo/diretório não existe, pois a validação pode resultar em false
	 * caso o programa por algum motivo de permissão de acesso.
	 */
	private static void check() {

		//verifica se um path existe
		System.out.println("The path " + fixed + " exists? " + Files.exists(fixed));
		System.out.println("The path " + fixed + " not exists? " + Files.notExists(fixed));
		
		Path notExists = fixed.resolve("OCP10");
		System.out.println("The path " + notExists + " exists? " + Files.exists(notExists));
		System.out.println("The path " + notExists + " not exists? " + Files.notExists(notExists));
		
		System.out.println("Is the method Files.notExists(fixed) the same that !Files.exists(fixed)?"
				+"\nFiles.notExists(fixed) results in " + Files.notExists(fixed)
				+"\n!Files.exists(fixed) results in " + !Files.exists(fixed));
	}
	
	private static void doDelete() {
		
		//cria um novo arquivo (ocp8.txt) a partir do path fixed e deleta o arquivo
		Path fileToDelete = fixed.resolve(Paths.get("ocp8.txt"));
		createFile(fileToDelete);
		
		delete(fileToDelete);
		//se deletar denovo o mesmo path, resulta na exceção NoSuchFileException
		delete(fileToDelete);
		
		//deleta somente se existir, senao segue o flxuo normal
		deleteIfExists(fileToDelete);
		
		//cria um novo diretório a partir do path fixed e então deleta
		Path dirToDelete = fixed.resolve(Paths.get("OCP8"));
		createDirectory(dirToDelete);
		
		deleteIfExists(dirToDelete);
		deleteIfExists(dirToDelete);
		//lança exceção porquê o diretório não existe mais
		delete(dirToDelete);
		
		createDirectory(dirToDelete);
		Path notEmptyDir = dirToDelete.resolve("example.txt");
		createFile(notEmptyDir);
		
		//ambos lançam a exceção DirectoryNotEmptyException
		delete(dirToDelete);
		deleteIfExists(dirToDelete);

		//apagar os arquivos e diretórios criados
		deleteIfExists(notEmptyDir);
		deleteIfExists(dirToDelete);
	}

	/**
	 * Na API de NIO 2, podemos facilmente deletar arquivos com ajuda dos métodos delete(Path).
	 * - Se estivermos deletando um arquivo e o mesmo não existir, irá lançar um NoSuchFileException;
	 * - Se estivermos deletando um diretório e o mesmo não estiver vazio, irá lançar 
	 * DirectoryNotEmptyException;
	 * @param pathToDelete 
	 */
	private static void delete(Path pathToDelete) {
		
		try {
			System.out.println("Delete file: " + pathToDelete);
			Files.delete(pathToDelete);
		} catch(NoSuchFileException e) {
			System.out.println("(NoSuchFileException) File/directory no found exception: " + e.getMessage());
		} catch(DirectoryNotEmptyException f) {
			System.out.println("(DirectoryNotEmptyException) Directory not empty: " + f.getMessage());
		} catch(IOException g) {
			System.out.println("(IOException) Something went wrong: " + g.getMessage());
		}
		
	}
	
	/**
	 * Deleta o diretório ou arquivo se o mesmo existir e, se não existir, a execução do método segue
	 * sem interrupção/exceção (diferente do método delete).
	 * @param pathToDelete
	 */
	private static void deleteIfExists(Path pathToDelete) {
		
		try {
			System.out.println("Delete if existis: " + pathToDelete);
			Files.deleteIfExists(pathToDelete);
		} catch(DirectoryNotEmptyException e) {
			System.out.println("(DirectoryNotEmptyException) Directory not empty: " + e.getMessage());
		} catch (IOException f) {
			System.out.println("(IOException) Something went wrong: ");
			f.printStackTrace();
		}
		
	}
	
	/**
	 * Copia um arquivo de um path para o outro. Se o path em questão for um diretório, os arquivos
	 * não são copiados e será criado um diretório vazio ao menos 
	 */
	private static void doCopy() {
		
		Path source = fixed.resolve("ocp8.txt");
		createFile(source);
		
		Path destination = fixed.resolve("OCP8");
		createDirectory(destination);
		
		//copia do source para o destination
		copy(source, destination);
		Path file = destination.resolve(source);
		System.out.println("New path: " + file);
		System.out.println("Is copied? " + Files.exists(file));
		
	}
	
//	/**
//	 * Copia o arquivo source para o target
//	 * @param source
//	 * @param target
//	 */
//	private static void copy(Path source, Path target, StandardCopyOption option) {
//		
//		try {
//			Files.copy(source, target, option);
//		} catch (IOException e) {
//			System.out.println("(IOException) Something went wrong: ");
//			e.printStackTrace();
//		}
//		
//	}
	
	private static void copy(Path source, Path destination) {
		
		try {
			Files.copy(source, destination);
		} catch (IOException e) {
			System.out.println("(IOException) Something went wrong: ");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Extra pois não está no conteúdo da prova
	 * @param pathToCreate
	 */
	private static void createFile(Path pathToCreate) {
		
		try {
			System.out.println("Create file " + pathToCreate);
			Files.createFile(pathToCreate);
		} catch(FileAlreadyExistsException e) {
			System.out.println("(FileAlreadyExistsException) This file already exists: " + e.getMessage());
		} catch(IOException e) {
			System.out.println("(IOException) Error on create file: ");
		}
		
	}
	
	/**
	 * Extra pois não está no conteúdo da prova
	 * @param pathToCreate
	 */
	private static void createDirectory(Path pathToCreate) {
		
		try {
			System.out.println("Create directory " + pathToCreate);
			Files.createDirectory(pathToCreate);
		}catch(FileAlreadyExistsException e) {
			System.out.println("(FileAlreadyExistsException) This directory already exists: " + e.getMessage());
		} catch(IOException e) {
			System.out.println("(IOException) Error on create directory: ");
			e.printStackTrace();
		}
		
	}
	
}
