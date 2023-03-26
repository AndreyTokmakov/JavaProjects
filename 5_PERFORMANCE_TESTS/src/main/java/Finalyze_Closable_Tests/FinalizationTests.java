/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Finalization tests class
*
* @name    : FrameworkDevelopment.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 01, 2020
****************************************************************************/ 

package Finalyze_Closable_Tests;

import java.util.concurrent.TimeUnit;

class SomeResource implements AutoCloseable {
	public SomeResource() {
		//System.out.println("Allocate resources");
	}
	
	public void DoWork() {
		// System.out.println("*** DoWork ***");
	}
	
    @Override
    public void close() {
    	// System.out.println("SomeResource::close()  Release alocated resources");
    }
}

class SomeResourceFinalizable implements AutoCloseable {
	public SomeResourceFinalizable() {
		//System.out.println("Allocate resources");
	}
	
	public void DoWork() {
		// System.out.println("*** DoWork ***");
	}
	
    @Override
    public void close() {
    	// System.out.println("SomeResource::close()  Release alocated resources");
    }
    
    @Override
    protected void finalize() throws Throwable {
    	System.out.println("LongFinalize finalizer");
    }
}



public class FinalizationTests {
	
	protected static void RunBenchmark() throws InterruptedException {
		long start = System.nanoTime();
		
		// TimeUnit.SECONDS.sleep(1);
		// test();
		testF();
		
		long end = System.nanoTime();
		
		long nsDuration = end - start;
		long mksDuration = TimeUnit.NANOSECONDS.toMicros(nsDuration);
		long msDuration = TimeUnit.NANOSECONDS.toMillis(nsDuration);
		
		System.out.println(String.format("Slept for %d nano seconds.", nsDuration)); 
		System.out.println(String.format("Slept for %d micro seconds.", mksDuration)); 
		System.out.println(String.format("Slept for %d milli seconds.", msDuration)); 
	}
	
	protected static void test() {
		for(long n = 0; n < 10000; ++n) {
			for (long i = 0; i < 1000000; ++i) {
				try (final SomeResource res = new SomeResource()) {
		        }
				catch (Exception exc) {
					// System.err.println(exc);
				}
				finally {
					// System.out.println("Finaly block");
				}
			}
		}
	}
	
	protected static void testF() {
		for(long n = 0; n < 10000; ++n) {
			for (long i = 0; i < 1000000; ++i) {
				try (final SomeResourceFinalizable res = new SomeResourceFinalizable()) {
		        }
				catch (Exception exc) {
					// System.err.println(exc);
				}
				finally {
					// System.out.println("Finaly block");
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunBenchmark();
	}
}
