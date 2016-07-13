package br.com.upgrade.ocp6.concurrency;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Referência e exemplos retirados deste tutorial:
 * http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/ 
 * @author ggarcia
 *
 */
public class ScheduledExecutorExample {
	
	public static void main(String[] args) {
		executeAfterAFewSecconds();
		schedule();
		scheduleAndWait();
	}
	
	/**
	 * Executa a thread após o período de tempo informado
	 */
	private static void executeAfterAFewSecconds() {
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable task = () -> System.out.println(" Scheduling: " + System.nanoTime());
		ScheduledFuture<?> future = executor.schedule(task, 5, TimeUnit.SECONDS);

		try {
			TimeUnit.MILLISECONDS.sleep(1337);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
		System.out.printf("Remaining Delay: %sms ", remainingDelay);
		
	}
	
	/**
	 * Executa a thread periodicamente de acordo com o período informado
	 */
	private static void schedule() {
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable task = () -> {
			
			//condição para finalizar
	    	if(LocalTime.now().getSecond() > 50) {
	    		executor.shutdown();
	    		System.out.println("Scheduling done!");
	    	}
	    	
			System.out.println("Scheduling: " + LocalDateTime.now());
		};

		int initialDelay = 5;
		int period = 5;
		executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
	}
	
	/**
	 * Executa a thread periodicamente com um intervalo de tempo definido para cada execução. Observe que,
	 * a execução do Runnable leva em torno de 2 segundos para executar e o executor é executado com intervalo 
	 * de 5 segundos após a execução do Runnable
	 */
	private static void scheduleAndWait() {
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
		    try {
		    	
		    	//condição para finalizar
		    	if(LocalTime.now().getSecond() > 50) {
		    		executor.shutdown();
		    		System.out.println("Scheduling and wait done!");
		    	}
		    	
		        TimeUnit.SECONDS.sleep(2);
		        System.out.println("Scheduling and wait: " + LocalTime.now());
		        
		    } catch (InterruptedException e) {
		        System.err.println("task interrupted");
		    }
		};

		executor.scheduleWithFixedDelay(task, 0, 5, TimeUnit.SECONDS);
	}
	
}
