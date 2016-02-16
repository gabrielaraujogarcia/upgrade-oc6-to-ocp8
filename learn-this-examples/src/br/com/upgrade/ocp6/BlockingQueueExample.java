package br.com.upgrade.ocp6;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
	
	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);
		
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		UnlimitedProducer unlimitedProducer = new UnlimitedProducer(queue);
		
		new Thread(producer).start();
		new Thread(consumer).start();
		new Thread(unlimitedProducer).start();
		
		Thread.sleep(4000);
	}
	
	/**
	 * Classe que implementa um Runnable e que quando for chamado o método start
	 * irá criar objetos e adicionar os mesmos na BlockingQueue para serem consumidos
	 * pelo Consumer.
	 * @author Gabriel
	 *
	 */
	private static class Producer implements Runnable {
		
		protected BlockingQueue<String> queue = null;
		
		public Producer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
		
		public void run() {
			
			try {
				System.out.println("Add 1");
				queue.put("1");
				//Thread.sleep(1000);
				System.out.println("Add 2");
				queue.put("2");
				//Thread.sleep(1000);
				System.out.println("Add 3");
				queue.put("3");
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * Classe que implementa um Runnable e que quando for chamado o método start
	 * irá consumir os objetos criados e adicionados na BlockingQueue pela classe
	 * Producer.
	 * @author Gabriel
	 *
	 */
	private static class Consumer implements Runnable {
		
		protected BlockingQueue<String> queue = null;
		
		public Consumer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
		
		public void run() {
			
			try {
				System.out.println("Consuming: " + queue.take());
				System.out.println("Consuming: " + queue.take());
				System.out.println("Consuming: " + queue.take());
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Runnable que adiciona elementos a BlockingQueue enquanto a capacidade máxima não for 
	 * atingida
	 * @author Gabriel
	 *
	 */
	private static class UnlimitedProducer implements Runnable {
		
		protected BlockingQueue<String> queue = null;
		
		public UnlimitedProducer(BlockingQueue queue) {
			this.queue = queue;
		}
		
		public void run() {
		
			int i = 0;
			boolean canOffer =  true;
			while(canOffer) {
				canOffer = queue.offer("Add obj " + i);
				System.out.println("Objects count: " + i + " Can i offer 1 more? " + canOffer);
				i++;
			}
			
			new Thread(new UnlimitedConsumer(queue)).start();
		}
		
	}
	
	/**
	 * Runnable que consome uma BlockingQueue enquanto houver elementos na mesma
	 * @author Gabriel
	 *
	 */
	private static class UnlimitedConsumer implements Runnable {
		
		protected BlockingQueue queue = null;
		
		public UnlimitedConsumer(BlockingQueue queue) {
			this.queue = queue;
		}
		
		public void run() {
			
			String obj = (String) queue.poll();
			System.out.println("Object " + obj);
			
			while(obj != null) {
				obj = (String) queue.poll();
				System.out.println("Object " + obj);
				
			}
			
			System.out.println(queue.peek());
			//new Thread(new UnlimitedProducer(queue)).start();
			
		}
	}
	
}
