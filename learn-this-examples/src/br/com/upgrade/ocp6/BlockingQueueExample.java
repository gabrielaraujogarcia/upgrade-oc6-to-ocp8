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
		removeElements();
		retrievesElements();
		
		new Thread(producer).start();
		new Thread(consumer).start();
		new Thread(unlimitedProducer).start();
		new Thread(unlimitedConsumer).start();
		
		Thread.sleep(4000);
	}
	
	private static void addElements() {
		
		System.out.println("############ Examples of add, offer and put elements to queue #############\n");
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
		
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
			
		checkQueue(queue, null);
		String head = queue.remove();
		System.out.println("Head element was removed: " + head);
		checkQueue(queue, null);
		
		System.out.println("\n############ Done examples of add, offer and put elements to queue #############\n");
	}
	
	private static void removeElements() throws InterruptedException {
		
		System.out.println("############ Examples of remove, poll and take elements to queue #############\n");
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
		
		try {
			queue.remove();
		} catch(Exception e) {
			System.out.println("The method remove(e) will throw an exception when it's can't possible to remove an element");
		}
		
		/*
		 * Esta thread vai remover o primeiro elemento (head) da fila assim que o mesmo for adicionado. Se este
		 * método não for executado em uma nova thread, o mesmo irá "travar" a execução dos demais eventos até 
		 * que ele consiga remover um elemento da lista.    
		 */
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					checkQueue(queue, "Checking queue before queue.take(): ");
					String item = queue.take();
					System.out.println("Removing the head element '"+ item + "' after add element to queue.");
					checkQueue(queue, "Checking queue after queue.take(): ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		
		System.out.println("Add elements to queue");
		
		queue.offer("I will be removed!");
		queue.offer("String 0");
		//altere a posição teste sleep e veja o que acontece no log do console
		Thread.sleep(1000);
		queue.offer("String 1");
		queue.offer("String 2");
		queue.offer("String 3");
		queue.offer("String 4");
		
		checkQueue(queue, "Checking queue after add elements: ");
		
		while(queue.size() > 0) {
			System.out.println("Removing "+ queue.poll() +" element with poll()");
		}
		
		System.out.println("\n############ Done of remove, poll and take elements to queue #############\n");
		
	}
	
	private static void retrievesElements() {
		
		System.out.println("############ Examples of element and peek on queue #############\n");
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
		
		try {
			//lanca uma exception
			String e = queue.element();
		} catch(Exception e) {
			System.out.println("The method element() will throw an exception when it's can't retrieves an element!");
		}
		
		//retorna null
		System.out.println("The method peek() will return " + queue.peek() + " when it's can't retrieves an element");
		
		queue.offer("String 0");
		queue.offer("String 1");
		queue.offer("String 2");
		checkQueue(queue, "Added this itens to queue: ");
		
		//exibe o head com element()
		System.out.println("Using element(): " + queue.element());
		checkQueue(queue, "Tip: element() don't removes an element from queue");
		
		//remove um item e exibe o head com peek()
		queue.remove();
		System.out.println("Using remove() to remove an item then using peek() to show the head: " + queue.element());
		checkQueue(queue, "Tip: peek() don't removes an elemento from queue");

		System.out.println("\n############ Done examples of element and peek on queue #############\n");
		
	}
	
	private static void checkQueue(BlockingQueue<String> queue, String prefix) {
		
		Iterator<String> it = queue.iterator();
		StringBuilder text = new StringBuilder();
		
		while(it.hasNext()) {
			text.append(" ").append(it.next());
		}
		
		if(prefix == null) {
			prefix = "Checking queue: ";
		}
		
		System.out.println(prefix + text.toString());
	}
	
	/**
	 * Classe que implementa um Runnable e que quando for chamado o mï¿½todo start
	 * irï¿½ criar objetos e adicionar os mesmos na BlockingQueue para serem consumidos
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
	 * Classe que implementa um Runnable e que quando for chamado o mï¿½todo start
	 * irï¿½ consumir os objetos criados e adicionados na BlockingQueue pela classe
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
	 * Runnable que adiciona elementos a BlockingQueue enquanto a capacidade mï¿½xima nï¿½o for 
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
