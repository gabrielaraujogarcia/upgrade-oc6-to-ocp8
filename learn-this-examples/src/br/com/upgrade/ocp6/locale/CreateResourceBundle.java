package br.com.upgrade.ocp6.locale;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Exemplos de como criar o nosso próprio ResourceBundle e suas variações de acordo
 * com a lingua.
 */
public class CreateResourceBundle {
	
	 // default (English language, United States)
//	 public class MyResources extends ResourceBundle {
//	     
//		 public Object handleGetObject(String key) {
//	         if (key.equals("okKey")) return "Ok";
//	         if (key.equals("cancelKey")) return "Cancel";
//	         return null;
//	     }
//
//	     public Enumeration<String> getKeys() {
//	         return Collections.enumeration(keySet());
//	     }
//
//	     // Overrides handleKeySet() so that the getKeys() implementation
//	     // can rely on the keySet() value.
//	     protected Set<String> handleKeySet() {
//	         return new HashSet<String>(Arrays.asList("okKey", "cancelKey"));
//	     }
//	 }

//	 // German language
//	 public class MyResources_de extends MyResources {
//	    
//		 public Object handleGetObject(String key) {
//	         // don't need okKey, since parent level handles it.
//	         if (key.equals("cancelKey")) return "Abbrechen";
//	         return null;
//	     }
//
//	     protected Set<String> handleKeySet() {
//	         return new HashSet<String>(Arrays.asList("cancelKey"));
//	     }
//	 }
	 
	 /**
	  * O ResourceBundle deve estar na raiz dos arquivos de código fonte para este exemplo
	  * funcionar. Pendente: descobrir como configurar a aplicação para ler os arquivos de
	  * recursos a partir de outros diretórios/pacotes
	  * @param args
	  */
	 public static void main(String[] args) {
		 
		 Locale locale = Locale.US;
		 ResourceBundle bundle = ResourceBundle.getBundle("MyResources", locale);
		 //também funciona, ele irá procurar pelo locale e depois pelo default (sem linguagem)
//		 ResourceBundle bundle = ResourceBundle.getBundle("MyResources"); 
		 System.out.println(bundle.getString("cancelKey"));
		 
		 //outra forma de criar o Locale. Existem mais variações mas todas a partir de métodos Fábrica
		 bundle = ResourceBundle.getBundle("MyResources_de", new Locale("de"));
		 System.out.println(bundle.getString("cancelKey"));
		
		 try {
			 bundle.getString("notExists");
		 } catch(MissingResourceException e) {
			 System.out.println("This resource does not exists: " + e.getMessage());
		 }
		 
	 }
	
}
