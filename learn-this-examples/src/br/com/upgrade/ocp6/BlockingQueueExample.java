package br.com.upgrade.ocp6;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
	
	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
		
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		UnlimitedProducer unlimitedProducer = new UnlimitedProducer(queue);
		UnlimitedConsumer unlimitedConsumer = new UnlimitedConsumer(queue);
		
		addElements();
		
		new Thread(producer).start();
		new Thread(consumer).start();
		new Thread(unlimitedProducer).start();
		new Thread(unlimitedConsumer).start();
		
		Thread.sleep(4000);
	}
	
	private static void addElements() {
		
		System.out.println("############ Examples of add, offer and put elements to queue #############\n");
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
		StringBuilder text = new StringBuilder();
		Iterator<String> it;
		
		try {
			
			queue.add("String 0");
			queue.offer("String 1");
			queue.put("String 2"); //pode lancar InterruptedException
			queue.add("String 3");
			queue.offer("String 4");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//adiciona quando possivel e, caso nao seja possivel, lanca uma excecao
		try{
			queue.add("The queue is full!");
		} catch(Exception e) {
			System.out.println("Throws an exception when try to add(e) to full queue");
		}
		
		//offer: adiciona caso existir espaco, se existir retorna true senao retorna false
		System.out.println("Return false when try to offer(e) to full queue:");
		System.out.println("\t - Can I put more elements to this queue? " + queue.offer("Queue is full"));
		
		/* 
		 * O metodo put(e) adiciona um elemento assim que for liberado espaco na fila. Toda implementacao apos o metodo
		 * put dentro da mesma thread nao sera executada ate que o put termine seu processamento. Em outras palavras,
		 * quando chamar queue.put(e) nada vai acontecer ate que o put(e) termine sua execucao. Por isto, tome bastante
		 * cuidado com esta implementacao. Neste exemplo, criamos uma nova Thread para exemplificar o uso do put(e)
		 */
		System.out.println("The text 'I'm the last!' will be put when the consumer consume an element.");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					queue.put("I'm the last!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
			
		it = queue.iterator();
		
		while(it.hasNext()) {
			text.append(" ").append(it.next());
		}
		
		System.out.println("Checking queue: " + text.toString());
		
		String head = queue.remove();
		System.out.println("Head element was removed: " + head);
		
		text = new StringBuilder();
		it = queue.iterator();
		
		while(it.hasNext()) {
			text.append(" ").append(it.next());
		}
		
		System.out.println("Lets check again: " + text.toString());
		
		System.out.println("\n############ Done examples of add, offer and put elements to queue #############\n");
	}
	
	/**
	 * Classe que implementa um Runnable e que quando for chamado o m�todo start
	 * ir� criar objetos e adicionar os mesmos na BlockingQueue para serem consumidos
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
			
			System.out.println("(Producer) running...");
			
			try {
				//para cada put(e) no Producer, sera executado um take no Consumer
				System.out.println("(Producer) add 1");
				queue.put("1");
				System.out.println("(Producer) add 2");
				queue.put("2");
				System.out.println("(Producer) add 3");
				queue.put("3");
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("(Producer) finish.");
		}
		
	}
	
	/**
	 * Classe que implementa um Runnable e que quando for chamado o m�todo start
	 * ir� consumir os objetos criados e adicionados na BlockingQueue pela classe
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
			
			System.out.println("(Consumer) running...");
			
			try {
				//para cada put(e) no Producer, sera executado um take no Consumer
				System.out.println("(Consumer) consuming " + queue.take());
				System.out.println("(Consumer) consuming " + queue.take());
				System.out.println("(Consumer) consuming " + queue.take());
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("(Consumer) finish.");
		}
	}
	
	/**
	 * Runnable que adiciona elementos a BlockingQueue enquanto a capacidade m�xima n�o for 
	 * atingida
	 * @author Gabriel
	 *
	 */
	private static class UnlimitedProducer implements Runnable {
		
		protected BlockingQueue<String> queue = null;
		
		public UnlimitedProducer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
		
		public void run() {
		
			System.out.println("(UnlimitedProducer) running...");
			
			int i = 1;
			boolean canOffer =  true;
			
			while(canOffer) {
				canOffer = queue.offer("Obj " + i);
				System.out.println("(UnlimitedProducer) count: " + i + " Can i offer 1 more? " + canOffer);
				i++;
			}
			
			System.out.println("(UnlimitedProducer) finish.");
			
		}
		
	}
	
	/**
	 * Runnable que consome uma BlockingQueue enquanto houver elementos na mesma
	 * @author Gabriel
	 *
	 */
	private static class UnlimitedConsumer implements Runnable {
		
		protected BlockingQueue<String> queue = null;
		
		public UnlimitedConsumer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
		
		public void run() {
			
			System.out.println("(UnlimitedConsumer) running...");
			String obj = (String) queue.poll();
			
			while(obj != null) {
				System.out.println("(UnlimitedConsumer) item " + obj);
				System.out.println("(UnlimitedConsumer) queue.peek() after poll: "+ queue.peek());
				obj = (String) queue.poll();
			}
			
			System.out.println("(UnlimitedConsumer) finish.");
			
		}
	}
	
}
