package Sync;


class CustomLock {
	private boolean locked = false;

	public synchronized void lock() throws InterruptedException {
		this.locked = true;
		while (this.locked) {
			System.out.println("CustomLock 1");
			wait();
			System.out.println("CustomLock 2");
		}
	}

	public synchronized void unlock() {
		this.locked = false;
		notify();
	}
}


public class CustomLockTest {

}
