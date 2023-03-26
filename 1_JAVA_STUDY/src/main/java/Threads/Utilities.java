package Threads;

/** Utilities class. **/
public class Utilities {
	
	/** Sleep : **/
	public static void sleep(long milliseconds) {
		try {
			Thread.currentThread();
			Thread.sleep(milliseconds);
		} catch (final InterruptedException exc) {
			System.err.println(exc.getMessage());
		}	
	}
}
