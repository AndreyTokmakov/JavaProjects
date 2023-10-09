/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* JAVA Future tests 
*
* @name    : Future.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 05, 2020
****************************************************************************/ 

package Future;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

class AsynchCalculator {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
     
    public Future<Integer> calculate(Integer input) {        
        return executor.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return input * input;
        });
    }
}

/*
class MyRunnable implements Runnable { 
    private final long waitTime; 
  
    public MyRunnable(int timeInMillis)  { 
        this.waitTime = timeInMillis; 
    } 
  
    @Override
    public void run()  { 
        try { 
            // sleep for user given millisecond before checking again 
            Thread.sleep(waitTime); 
            // return current thread name 
            System.out.println(Thread.currentThread() .getName()); 
        } catch (InterruptedException ex) { 
            Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex); 
        } 
    } 
} 
*/

public class Future_Tests {

	class DemoTask implements Callable<String> {
		private String text;
		
		public DemoTask(String text) {
			this.text = text;
		}
		
		@Override
		public String call() {
			System.out.println("Task started.");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			System.out.println("Task done.");
			return this.text;
		}
	}
	
	private void sleep(int msSeconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(msSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void SimpleTest_0() throws InterruptedException, ExecutionException {
		ExecutorService executor  = Executors.newSingleThreadExecutor();
		Future<String> future = executor.submit(new DemoTask("Expected text result"));
		String result = future.get();
		System.out.println(result);
		executor.shutdown();
	}
	
	protected void SimpleTest_Lambda_Executor() throws InterruptedException, ExecutionException {
		ExecutorService executor  = Executors.newSingleThreadExecutor();
		Future<String> future = executor.submit(() -> {
			System.out.println("Task stated");
			sleep(2000);
			System.out.println("Task completed ");
			return "";
		});
		System.out.println(future.get());
		executor.shutdown();
	}
	
	public void SimpleTest_Lambda_Executor_2() throws InterruptedException, ExecutionException 
	{
		ExecutorService executor  = Executors.newFixedThreadPool(1);
		
		executor.submit(() -> {
			sleep(10);
			System.out.println(new Date() + ": Task1 stated.");
			sleep(2000);
			System.out.println(new Date() + ": Task1 completed.");
		});
		
		executor.submit(() -> {
			sleep(10);
			System.out.println(new Date() + ": Task2 stated.");
			sleep(2000);
			System.out.println(new Date() + ": Task2 completed.");
		});
		
		System.out.println(new Date() + "*** We are here ***");
	}
	
	public void SimpleTest_Lambda_2() throws InterruptedException, ExecutionException {
		String someText = "Some_Texs";
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> { 
			System.out.println(new Date() + ": Task stated. Local variable value = " + someText);
			sleep(2000);
			System.out.println(new Date() + ": Task completed. Local variable value = " + someText);
		});
		future.get();
	}
	
	protected void SimpleTest_FutureTask() throws InterruptedException, ExecutionException {
		final Runnable taskHandler = () -> {
		    try {
		    	System.out.println("Thread started.");
		        TimeUnit.SECONDS.sleep(5);
		    	System.out.println("Thread done.");
		    }  catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		};
		
		FutureTask<String> task = new FutureTask<String>(taskHandler, "Future Task is complete"); 
        ExecutorService executor = Executors.newFixedThreadPool(1); 
        executor.submit(task); 
        
        System.out.println("Task done: " + task.isDone());
        // task.wait();
        System.out.println("Result: " + task.get());
        System.out.println("Task done: " + task.isDone());
        executor.shutdown();
        
	}
	
	protected void Future_ComplexTest() throws InterruptedException, ExecutionException {
		Future<Integer> future = new AsynchCalculator().calculate(10);
		 
		while (false == future.isDone()) {
		    System.out.println("Calculating...");
		    TimeUnit.MILLISECONDS.sleep(500);
		}
		 
		Integer result = future.get();
		System.out.println("Done. Result: " + result);
	}
	
	protected void CompletalbeFutureTest() {
        AtomicLong longValue = new AtomicLong(0);
        final Runnable task = () -> {
        	longValue.set(new Date().getTime());
        };
        
        Function<Long, Date> dateConverter = (longvalue) -> new Date(longvalue);
        Consumer<Date> printer = date -> {
            System.out.println(date);
            System.out.flush();
        };
        
        // CompletableFuture computation
        CompletableFuture.runAsync(task).thenApply((v) -> longValue.get())
        							    .thenApply(dateConverter).thenAccept(printer);
	}
	
	protected void RunSynch_Task() throws InterruptedException, ExecutionException {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			System.out.println(new Date() + " [" + Thread.currentThread().getName() + "]: Async task started");
			sleep(13_000);
			System.out.println(new Date() + " [" + Thread.currentThread().getName() + "]: Async task completed");
		});
		
		for (int i = 0; i < 10; ++i) {
			System.out.println(new Date() + " [" + Thread.currentThread().getName() + "]: Doing something.");
			sleep(1_000);
		}
		
		future.get();
	}
	
	protected void CompletalbeFuture_SimpleTest() throws InterruptedException, ExecutionException {
		System.out.println("[" + Thread.currentThread().getName() + "]: Starting thread");
		final CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
		    @Override
		    public void run() {
		        try { 
		        	// Do somethig....
		            TimeUnit.SECONDS.sleep(3);
		        } catch (InterruptedException e) {
		            throw new IllegalStateException(e);
		        }
		        System.out.println("[" + Thread.currentThread().getName() + "]: External thread");
		    }
		});
		future.get();
		System.out.println("[" + Thread.currentThread().getName() + "]: Thread done");
	}
	
	
	
	protected void SimpleTest_ThenAccept() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<Void> future = completableFuture.thenAccept(s -> System.out.println("Computation returned: " + s));
		System.out.println(future.get());
	}
	
	protected void SimpleTest_ThenRun() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<Void> future = completableFuture.thenRun(() -> System.out.println("Computation finished."));
		System.out.println(future.get());
	}
	
	protected void SimpleTest_ThenApply() throws InterruptedException, ExecutionException  {
		CompletableFuture<String> completableFuture  = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future = completableFuture .thenApply(s -> s + " World");
		System.out.println(future.get()); // OUT: "Hello World"
	}
	
	protected void SimpleTest_ThenCompose() throws InterruptedException, ExecutionException  {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
			.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
		System.out.println(completableFuture.get()); // OUT: "Hello World"
	}
	
	protected void SimpleTest_ThenCompose_CheckThreads() throws InterruptedException, ExecutionException  {
		final Supplier<String> task1 = () -> {
			System.out.println("Thread " + Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + " Task started.");
			return "Hello";
		};
		

		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(task1)
			.thenCompose(value-> {
				System.out.println("Thread " + Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + " Task started.");
				return CompletableFuture.completedFuture(value + " World!!!");
			});
		System.out.println(completableFuture.get()); // OUT: "Hello World!!!"
	}
	
	protected void CompletalbeFuture_SupplyAsync() throws InterruptedException, ExecutionException {
		System.out.println("[" + Thread.currentThread().getName() + "]: Starting thread");
		CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
		    @Override
		    public String get() {
		        try {
		            TimeUnit.SECONDS.sleep(1);
		        } catch (InterruptedException e) {
		            throw new IllegalStateException(e);
		        }
		        return "Some_Result_Maybe";
		    }
		});
		String result = future.get();
		System.out.println("[" + Thread.currentThread().getName() + "]: Thread done");
		System.out.println("Result: " + result);
	}
	
	protected void CompletalbeFuture_SupplyAsync_Lambda() throws InterruptedException, ExecutionException {
		System.out.println("[" + Thread.currentThread().getName() + "]: Starting thread");
		final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (final InterruptedException exc) {
		        throw new IllegalStateException(exc);
	        }
	        return "Some_Result_Maybe";
		});
		String result = future.get();
		System.out.println("[" + Thread.currentThread().getName() + "]: Thread done");
		System.out.println("Result: " + result);
	}
	
	
	protected void CompletalbeFuture_SupplyAsync_ThenApply() throws InterruptedException, ExecutionException {
		System.out.println("[" + Thread.currentThread().getName() + "]: Starting thread");
		final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		    try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (final InterruptedException exc) {
		        throw new IllegalStateException(exc);
	        }
	        return "Some_Result_Maybe";
		});
		
		
		final CompletableFuture<String> newFuture = future.thenApply(name -> {
		    return "UPDATED: " + name;
		});
		
		String result = newFuture.get();
		System.out.println("[" + Thread.currentThread().getName() + "]: Thread done");
		System.out.println("Result: " + result);
	}
	
	
	protected void CompletalbeFuture_SupplyAsync_ThenApply_2() throws InterruptedException, ExecutionException {
		System.out.println("[" + Thread.currentThread().getName() + "]: Starting thread");
		final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		    try {
		    	System.out.println("[" + Thread.currentThread().getName() + "]: External thread 1");
		        TimeUnit.SECONDS.sleep(1);
		    } catch (final InterruptedException exc) {
		        throw new IllegalStateException(exc);
	        }
	        return "Some_Result_Maybe";
		}).thenApply(name -> {
			System.out.println("[" + Thread.currentThread().getName() + "]: External thread 2");
		    return "UPDATED1: " + name;
		}).thenApply(name -> {
			System.out.println("[" + Thread.currentThread().getName() + "]: External thread 3");
		    return "UPDATED2: " + name;
		});
		
		String result = future.get();
		System.out.println("[" + Thread.currentThread().getName() + "]: Thread done");
		System.out.println("Result: " + result);
	}
	
	protected void CompletalbeFuture_SupplyAsync_ThenAccept() throws InterruptedException, ExecutionException {
		System.out.println("[" + Thread.currentThread().getName() + "]: Starting thread");
		final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		    try {
		    	System.out.println("[" + Thread.currentThread().getName() + "]: External thread 1");
		        TimeUnit.SECONDS.sleep(1);
		    } catch (final InterruptedException exc) {
		        throw new IllegalStateException(exc);
	        }
	        return "Some_Result_Maybe";
		});
		
		
		final CompletableFuture<Void> newFuture = future.thenAccept(result_of_future -> {
			System.out.println("[" + Thread.currentThread().getName() + "]: We've got result: " + result_of_future);
		});
		
		newFuture.get();
		System.out.println("[" + Thread.currentThread().getName() + "]: Thread done");
	}
	
	protected void CompletalbeFuture_SupplyAsync_ThenRun() throws InterruptedException, ExecutionException {
		System.out.println("[" + Thread.currentThread().getName() + "]: Starting thread");
		final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		    try {
		    	System.out.println("[" + Thread.currentThread().getName() + "]: External thread 1");
		        TimeUnit.SECONDS.sleep(1);
		    } catch (final InterruptedException exc) {
		        throw new IllegalStateException(exc);
	        }
	        return "Some_Result_Maybe";
		});
		
		
		final CompletableFuture<Void> newFuture = future.thenRun(() -> {
			System.out.println("[" + Thread.currentThread().getName() + "]: The first Future has completed.");
		});
		
		newFuture.get();
		System.out.println("[" + Thread.currentThread().getName() + "]: Thread done");
	}
	
	
	/////////////////////// MAIN ///////////////////////////
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Future_Tests tests = new Future_Tests();
		
		// tests.CompletalbeFutureTest();
		// tests.RunSynch_Task();
		// tests.Future_ComplexTest();
		// tests.SimpleTest_0();
		// tests.SimpleTest_Lambda_Executor();
		// tests.SimpleTest_Lambda_Executor_2();
		// tests.SimpleTest_Lambda_2();
		
		// tests.SimpleTest_FutureTask();
		
		// tests.SimpleTest_ThenAccept();
		// tests.SimpleTest_ThenRun();
		// tests.SimpleTest_ThenApply();
		// tests.SimpleTest_ThenCompose();
		// tests.SimpleTest_ThenCompose_CheckThreads();
		
		tests.CompletalbeFuture_SimpleTest();
		// tests.CompletalbeFuture_SupplyAsync();
		// tests.CompletalbeFuture_SupplyAsync_Lambda();
		// tests.CompletalbeFuture_SupplyAsync_ThenApply();
		// tests.CompletalbeFuture_SupplyAsync_ThenApply_2();
		// tests.CompletalbeFuture_SupplyAsync_ThenAccept();
		// tests.CompletalbeFuture_SupplyAsync_ThenRun();
	}
}