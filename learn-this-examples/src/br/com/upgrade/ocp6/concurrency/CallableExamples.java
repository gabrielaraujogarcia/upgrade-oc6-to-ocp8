package br.com.upgrade.ocp6.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Referência e exemplos retirados deste tutorial:
 * http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/ 
 * @author ggarcia
 *
 */
public class CallableExamples {
	
	
	public static void main(String[] args) {
		doCallable();
		invokeAllCallables();
		invokeAnyCallable();
	}
	
	/**
	 * Callable é uma interface funcional que funciona de forma similar aos Runnable. O que diferencia
	 * é que a mesma define um retorno de retorno.
	 */
	private static void doCallable() {
		
		Callable<Integer> task = () -> {
		    try {
		        TimeUnit.SECONDS.sleep(1);
		        System.out.println("Executing..");
		        return 123;
		    }
		    catch (InterruptedException e) {
		        throw new IllegalStateException("task interrupted", e);
		    }
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//ExecutorService retorna um objeto Future<?>
		Future<Integer> future = executor.submit(task);

		try {
			executor.shutdown();
			System.out.println("future done? " + future.isDone());
			Integer result = future.get();
			System.out.println("future done? " + future.isDone());
			System.out.print("result: " + result);
			
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			if(executor.isTerminated()) {
				System.out.println("\nShutdown");
				executor.shutdown();
			} else {
				System.out.println("\nShutdown Now");
				executor.shutdownNow();
			}
		}

	}
	
	private static void invokeAllCallables() {
		
		ExecutorService executor = Executors.newWorkStealingPool();

		List<Callable<String>> callables = Arrays.asList(
		        () -> "task1",
		        () -> "task2",
		        () -> "task3");

		try {
			executor.invokeAll(callables)
				.stream()
			    .map(future -> {
			        try {
			            return future.get();
			        }
			        catch (Exception e) {
			            throw new IllegalStateException(e);
			        }
			    })
			    .forEach(System.out::println);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void invokeAnyCallable() {
		
		ExecutorService executor = Executors.newWorkStealingPool();

		List<Callable<String>> callables = Arrays.asList(
		    callable("task1", 2),
		    callable("task2", 1),
		    callable("task3", 3));
		
		try {
			String result = executor.invokeAny(callables);
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}
	
	private static Callable<String> callable(String result, long sleepSeconds) {
	    return () -> {
	        TimeUnit.SECONDS.sleep(sleepSeconds);
	        return result;
	    };
	}
}
