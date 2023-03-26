package Threads;

import java.util.Date;
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
	
	protected void SimpleTest() throws InterruptedException, ExecutionException {
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
        
        /*
		final Runnable task1 = () -> {
			final String threadName = Thread.currentThread().getName();
			while (true) {
				System.out.println("[" + threadName + "] Stated at " + new Date());
			    try {
			        TimeUnit.MILLISECONDS.sleep(200);
			        longValue.set(new Date().getTime());

			    }  catch (final InterruptedException exc) {
			    	exc.printStackTrace();
			    }
			    System.out.println("[" + threadName + "] Completed at " + new Date());
			}
		};
        */
        
        Function<Long, Date> dateConverter = (longvalue) -> new Date(longvalue);
        Consumer<Date> printer = date -> {
            System.out.println(date);
            System.out.flush();
        };
        
        // CompletableFuture computation
        CompletableFuture.runAsync(task).thenApply((v) -> longValue.get())
        							    .thenApply(dateConverter).thenAccept(printer);
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
		// tests.Future_ComplexTest();
		// tests.SimpleTest();
		
		// tests.CompletalbeFuture_SimpleTest();
		// tests.CompletalbeFuture_SupplyAsync();
		// tests.CompletalbeFuture_SupplyAsync_Lambda();
		// tests.CompletalbeFuture_SupplyAsync_ThenApply();
		// tests.CompletalbeFuture_SupplyAsync_ThenApply_2();
		// tests.CompletalbeFuture_SupplyAsync_ThenAccept();
		tests.CompletalbeFuture_SupplyAsync_ThenRun();
	}
}