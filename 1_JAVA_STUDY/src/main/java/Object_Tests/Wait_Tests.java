package Object_Tests;

class Store {
	
	private int product = 0;

	public synchronized void get() {
		while (1 > product) {
			try {
				System.out.println("Store.get() before wait()");
				this.wait();
				System.out.println("Store.get() after wait()");
			} catch (final InterruptedException exc) {
				exc.printStackTrace();
			}
		}
		product--;
		System.out.println("Buyer bought 1 item");
		System.out.println("Products in stock:" + product);
		notify();
	}
	
	public synchronized void put() {
		while (product>=2) {
			try {
				System.out.println("Store.put() before wait()");
				this.wait();
				System.out.println("Store.get() after wait()");
			} catch (final InterruptedException exc) {
				exc.printStackTrace();
			}
		}

		product++;
	    System.out.println("Manufacturer added 1 product");
	    System.out.println("Products in stock:" + product);
		notify();
	}
}

class Producer implements Runnable{
	Store store;
	
	Producer(final Store store) {
		this.store = store; 
	}
	
	public void run() {
		for (int i = 1; i < 4; i++) {
			store.put();
		}
	}
}

class Consumer implements Runnable {
	Store store;
	
	Consumer(final Store store){
		this.store = store; 
	}
	public void run(){
		for (int i = 1; i < 4; i++) {
			store.get();
		}
	}
}

public class Wait_Tests {
	public static void main(String[] args) {
        Store store=new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
        
	}
}
