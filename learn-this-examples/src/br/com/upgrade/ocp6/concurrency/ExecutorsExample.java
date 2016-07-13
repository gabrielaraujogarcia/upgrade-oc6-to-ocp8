package br.com.upgrade.ocp6.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Referência e exemplos retirados deste tutorial:
 * http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/ 
 * @author ggarcia
 *
 */
public class ExecutorsExample {
	
	public static void main(String[] args) {
		doExecutor();
	}
	
	/**
	 * ExecutorService é uma forma de trabalhar com Threads em Java com mais facilidade.
	 */
	private static void doExecutor() {
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
		    String threadName = Thread.currentThread().getName();
		    System.out.println("Hello " + threadName);
		});
		
		try {
		    System.out.println("attempt to shutdown executor");
		    //shutdown tenta parar o executor após o processamento da thread atual
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		} finally {
		    if (!executor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    //shutdown para instantaneamente a execução da thread
		    executor.shutdownNow();
		    System.out.println("shutdown finished");
		}
		
	}
	
}
