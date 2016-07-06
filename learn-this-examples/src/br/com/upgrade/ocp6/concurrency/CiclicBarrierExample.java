package br.com.upgrade.ocp6.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CiclicBarrierExample {

	public static void main(String[] args) {
		
		//callback para quando o barril 1 estiver pronto para liberar as threads em espera
		Runnable barrier1Action = () -> System.out.println("BarrierAction 1 executed ");
		
		//callback para quando o barril 1 estiver pronto para liberar as threads em espera
		Runnable barrier2Action= () -> System.out.println("BarrierAction 2 executed ");

		//CyclicBarrier que aguarda 2 thrads antes de liberar as mesmas ou quando um evento de interrupção é lançado
		CyclicBarrier barrier1 = new CyclicBarrier(2, barrier1Action);
		CyclicBarrier barrier2 = new CyclicBarrier(2, barrier2Action);

		CyclicBarrierRunnable barrierRunnable1 = new CyclicBarrierRunnable(barrier1, barrier2);
		CyclicBarrierRunnable barrierRunnable2 = new CyclicBarrierRunnable(barrier1, barrier2);

		new Thread(barrierRunnable1).start();
		new Thread(barrierRunnable2).start();
		
	}
	
   /*
	* Condições de espera no barril:
	* The waiting threads waits at the CyclicBarrier until either:
	*  - The last thread arrives (calls await() )
	*  - The thread is interrupted by another thread (another thread calls its interrupt() method)
	*  - Another waiting thread is interrupted
	*  - Another waiting thread times out while waiting at the CyclicBarrier
	*  - The CyclicBarrier.reset() method is called by some external thread.
	*/
	private static class CyclicBarrierRunnable implements Runnable {

		CyclicBarrier barrier1 = null;
		CyclicBarrier barrier2 = null;

		public CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) {

			this.barrier1 = barrier1;
			this.barrier2 = barrier2;
		}

		public void run() {
			
			try {
				
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
				this.barrier1.await();

				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " waiting at barrier 2");
				this.barrier2.await();

				System.out.println(Thread.currentThread().getName() + " done!");

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}
