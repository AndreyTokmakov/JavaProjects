/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* CompletableFutureTests.java class
*
* @name    : CompletableFutureTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 23, 2021
****************************************************************************/

package Future_CompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class CompletableFutureTester {
	
	public void SimpleTest() throws InterruptedException, ExecutionException {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			try {
				System.out.println("Running asynchronous task in parallel");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("Done");
			} catch (InterruptedException ex) {
				throw new IllegalStateException(ex);
			}
		});
		
		future.get();
	}
	
	
	public void Collection_Future() throws InterruptedException, ExecutionException {
		final int taskCount = 10;
		List<CompletableFuture<Void>> jobs = new ArrayList<CompletableFuture<Void>>(taskCount);
		
		for (int i = 0; i < taskCount; ++i) {
			jobs.add(CompletableFuture.runAsync(() -> {
				try {
					System.out.println("Running asynchronous task in parallel");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Done");
				} catch (InterruptedException ex) {
					throw new IllegalStateException(ex);
				}
			}));
		}
		jobs.forEach(F -> F.join());
	}
}


public class CompletableFutureTests {
	private final static CompletableFutureTester tests = 
			new CompletableFutureTester();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// tests.SimpleTest();
		tests.Collection_Future();
	}
}
