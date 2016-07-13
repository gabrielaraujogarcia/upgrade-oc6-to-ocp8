package br.com.upgrade.ocp6.concurrency;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteTest {

	public static void main(String[] args) throws InterruptedException {

		final CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

		// new thread to concurrently modify the list
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// sleep a little so that for loop below can print part of the list
					Thread.sleep(250);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				numbers.add(10);
				System.out.println("numbers: " + numbers);
			}
		}).start();

		for (int i : numbers) {
			System.out.println(i);
			// sleep a little to let other thread finish adding an element before iteration is complete
			Thread.sleep(100);
		}
	}

}
