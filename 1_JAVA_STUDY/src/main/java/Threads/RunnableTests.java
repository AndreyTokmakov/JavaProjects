package Threads;

import java.util.concurrent.TimeUnit;


/** Worker class : **/
class Worker implements Runnable {
	/** **/
	private final static long SLEEP_TIMEOUT = 1000;
	/** **/
	private volatile boolean run = false;

	/** Worker class constructor: **/
	public Worker() {
	
	}

	/** Do something: **/
	@Override
	public void run() {
		this.run = true;
		while (true == run) {
			try {
				TimeUnit.MILLISECONDS.sleep(Worker.SLEEP_TIMEOUT);
				System.out.println("Working....");
			} catch (final InterruptedException exc) {
				// exc.printStackTrace();
				System.out.println(exc.getMessage());
			}
		}
		System.out.println("Thread stoped.");
	}
	
	public void stop()  {
		System.out.println("Stoping thread...");
		this.run = false;
	}
}


/////////////////////////////////// TESTS ////////////////////////////////////////////

public class RunnableTests {

	private void Simple_Test() throws InterruptedException {
		/** Creating the Worker thread. **/
		Worker worker = new Worker();
		Thread threadHandle = new Thread(worker);
		threadHandle.start();
		
		Utilities.sleep(5000);
		worker.stop();
		threadHandle.join();
		
	}

	/* @throws InterruptedException */
	public static void main(String[] args) throws InterruptedException {
		RunnableTests tests = new RunnableTests();
		tests.Simple_Test();
	}
}
