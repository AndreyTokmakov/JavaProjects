package Threads;


// Properly synchronized cooperative thread termination
class StoppableThread extends Thread {
	private boolean stopRequested = false;
	
	public void run() {
		boolean done = false;
		while (false == stopRequested() && false == done) {
			// do what needs to be done.
		}
	}
	
	public synchronized void requestStop() {
		stopRequested = true;
	}
	
	private synchronized boolean stopRequested() {
		return stopRequested;
	}
}

// BAD !!!!!
// The double-check idiom for lazy initialization - broken!
class FooSingleton {
	private static FooSingleton foo = null;


	public static FooSingleton getFoo() {
		if (foo == null) {
			synchronized (FooSingleton.class) {
			if (foo == null)
				foo = new FooSingleton();
			}
		}
		return foo;
	}
}

// HOW TO FIX: Don use lazy initialization!!! Next line is GOOD:
class FooSingleton_Good {
	private static final FooSingleton_Good foo = new FooSingleton_Good();
	
	public static FooSingleton_Good getFoo() {
		return foo;
	}
}

// OR good lazy way
// Properly synchronized lazy initialization

class LazySingleton_Good {
	private static LazySingleton_Good foo = null;
	
	public static synchronized LazySingleton_Good getFoo() {
		if (foo == null)
			foo = new LazySingleton_Good();
		return foo;
	}
}



public class SynchronizeAccess_SharedData {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
