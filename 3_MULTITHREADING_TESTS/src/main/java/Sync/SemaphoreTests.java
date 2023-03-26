package Sync;

import java.util.concurrent.Semaphore;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class CommonResource {
    public int x = 0;  
}
 
class CountThread implements Runnable{
    protected CommonResource res;
    protected Semaphore sem;
    protected String name;
    
    CountThread(final CommonResource res, 
    			Semaphore sem,
    			final String name){
        this.res = res;
        this.sem = sem;
        this.name = name;
    }
      
    public void run() {
    	try {
    		System.out.println(name + " waiting for right state");
            sem.acquire();
            res.x=1;
            for (int i = 1; i <= 5; i++){
                System.out.println(this.name + ": " + res.x);
                res.x++;
                Thread.sleep(100);
            }
        } catch (final InterruptedException exc) {
        	System.out.println(exc.getMessage());
        }
    	
    	System.out.println(name + " setting state");
        sem.release();
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Shared {
	static int count = 0;
}

abstract class TestThread implements Runnable {
    protected String name;
    protected Semaphore semaphore;

    public TestThread(final Semaphore s, final String n) {
        this.semaphore = s;
        name = n;
        new Thread(this).start();
    }

    public void run() {
        System.out.println("  " + name);
        try {  
            System.out.println(" " + name + "  ");
            semaphore.acquire();
            System.out.println(" " + name + "  ");

            for (int i = 0; i < 5; i++) {
            	handler();
                System.out.println(name + ": " + Shared.count);
                Thread.sleep(10);
            }
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        System.out.println(" " + name + "  ");
        semaphore.release();
    }
    
    public void handler() {
    	// TODO
    }
}


class IncThread extends TestThread {
	public IncThread(final Semaphore s, final String n) {
        super(s, n);
    }
	@Override
    public void handler() {
    	Shared.count++;
    }
}

class DecThread extends TestThread {
	public DecThread(final Semaphore s, final String n) {
        super(s, n);
    }
	@Override
    public void handler() {
    	Shared.count--;
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class SemaphoreTests {
	
	public static void Test1() {
	    Semaphore sem = new Semaphore(1);
        CommonResource res = new CommonResource();
        new Thread(new CountThread(res, sem, "CountThread 1")).start();
        new Thread(new CountThread(res, sem, "CountThread 2")).start();
        new Thread(new CountThread(res, sem, "CountThread 3")).start();

	}
	
	public static void Test2() {
		 Semaphore sem = new Semaphore(1);
		 new IncThread(sem, "A");
		 new DecThread(sem, "B");
	}
	
	public static void main(String[] args) {
		// Test1();
		Test2();
	}
}
