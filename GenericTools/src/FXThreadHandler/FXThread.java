/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXThreadHandler;

import java.util.concurrent.CountDownLatch;

public class FXThread implements Runnable  {
	/**
	 * Constructs a FXThread.
	 * @param task to be done
	 */
	public FXThread(Runnable task)  {
		this.task = task;
		count = null;
	}

	/**
	 * Constructs a FXThread.
	 *
	 * @param task to be done
	 * @param t_count task count
	 */
	public FXThread(Runnable task, int t_count)  {
		this(task);
		count = new CountDownLatch(t_count);
	}

	@Override
	public void run()  {
		try  {
			task.run();
		} finally  {
			if(count != null)  {
				countDown();
			}
		}
	}

	/**
	 * Counts down the thread.
	 */
	public void countDown()  {
		count.countDown();
	}

	/**
	 * Await for the count down.
	 *
	 * @throws InterruptedException if interrupted while waiting.
	 */
	public void await() throws InterruptedException  {
		count.await();
	}

	/**
	 * Count Down Latch for concurrency
	 */
	private CountDownLatch count;
	/**
	 * Task to be done
	 */
	private Runnable task;
}