package ExecutorServices;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorService_Tests {
	/** Test class: **/
	private final static ThreadScheduledExecutor_Local_TESTER tests = 
			new ThreadScheduledExecutor_Local_TESTER();
	
	/** Test class: **/
	private final static ThreadScheduledExecutor_Local_Performance perf_tests = 
			new ThreadScheduledExecutor_Local_Performance();
	
	
	public static void main(String[] args)
	{
		// tests.Single_Thread_Executor();
		// tests.ExecutorService_SingleThreadScheduledExecutor();
		// tests.ScheduledThreadPool_FixedRate();
		// tests.TestPool_Scheduled_Delay();
		// tests.TestPool_Scheduled_WithFixedDelay();
		
		perf_tests.CreateThreads();
	}
}

class ThreadScheduledExecutor_Local_Performance {
	/** Main thread name: **/
	private final static String mainThreadName = Thread.currentThread().getName();
	
	public void CreateThreads() 
	{
        System.out.println("Thread [" + mainThreadName +"] started. ");
        
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        final Runnable task = () -> {
        	final String threadName = Thread.currentThread().getName();
        	
            System.out.println("Executing the task thread [" + threadName + "] at " + new Date());
        };

        // FIXED RATE !!!
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
        System.out.println("Thread [" + mainThreadName +"] finished");
    }
}

class ThreadScheduledExecutor_Local_TESTER {
	/** Main thread name: **/
	private final static String mainThreadName = Thread.currentThread().getName();
	
	public void Single_Thread_Executor() 
	{
        System.out.println("Thread [" + mainThreadName +"] started. ");
        
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        final Runnable task = () -> {
        	final String threadName = Thread.currentThread().getName();
            System.out.println("Executing the task thread [" + threadName + "] at " + new Date());
        };

        // FIXED RATE !!!
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
        System.out.println("Thread [" + mainThreadName +"] finished");
    }
	

	public void ExecutorService_SingleThreadScheduledExecutor() {	
        System.out.println("Thread [" + mainThreadName +"] started. ");
        
		final ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
		final Runnable task = new Runnable() {
	        public void run() {
	        	final String threadName = Thread.currentThread().getName();
	            System.out.println("[" + threadName + "] Started at " + new Date());
	            ThreadPools_ExecutorServices.Sleep(3000);
	            System.out.println("[" + threadName + "] Completed at " + new Date());
	        }
	    };
	    
        System.out.println("Thread [" + mainThreadName +"] Staring scheduled executor. ");
	    pool.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
	}
	
	public void ScheduledThreadPool_FixedRate()
	{	
        System.out.println("[" + mainThreadName +"] Stated at " + new Date());
		final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
        final Runnable task = () -> {
        	final String threadName = Thread.currentThread().getName();
            System.out.println("[" + threadName + "] Stated at " + new Date());
        };
		 
		int initialDelay = 0;
		int period = 1;
		executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (final InterruptedException exc) {
			exc.printStackTrace();
		}
		
		try {
			System.out.println("Thread [" + mainThreadName +"] attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (final InterruptedException exc) {
			System.err.println("Thread [" + mainThreadName +"] tasks interrupted");
		} finally {
		    if (false == executor.isTerminated()) {
		    	System.err.println("Thread [" + mainThreadName +"] cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.err.println("Thread [" + mainThreadName +"] shutdown finished");
		}
	}
	
	public void TestPool_Scheduled_Delay() {	
		final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        final Runnable task = () -> {
        	final String threadName = Thread.currentThread().getName();
            System.out.println("[" + threadName + "] Stated at " + new Date());
        };
		ScheduledFuture<?> future = executor.schedule(task, 7, TimeUnit.SECONDS);

		for (int i = 0; i < 6; i++) {
			System.out.printf("Thread [" + mainThreadName +"] Remaining Delay: %sms\n", future.getDelay(TimeUnit.MILLISECONDS));
			try { 
				TimeUnit.SECONDS.sleep(1);
			} catch (final InterruptedException exc) {
				exc.printStackTrace();
			}
		}
		
		try {
			System.out.println("Thread [" + mainThreadName +"] attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (final InterruptedException exc) {
			System.err.println("Thread [" + mainThreadName +"] tasks interrupted");
		} finally {
		    if (false == executor.isTerminated()) {
		    	System.err.println("Thread [" + mainThreadName +"] cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.err.println("Thread [" + mainThreadName +"] shutdown finished");
		}
	}
	
	public void TestPool_Scheduled_WithFixedDelay()
	{	
		final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		final Runnable task = () -> {
		    try {
		    	final String threadName = Thread.currentThread().getName();
		        TimeUnit.SECONDS.sleep(2);
	            System.out.println("[" + threadName + "] Stated at " + new Date());
		    } catch (InterruptedException e) {
		        System.err.println("task interrupted");
		    }
		};
		 
		executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
		
		try {
			TimeUnit.SECONDS.sleep(13);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Thread [" + mainThreadName +"] attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("Thread [" + mainThreadName +"] tasks interrupted");
		} finally {
		    if (!executor.isTerminated()) {
		    	System.err.println("Thread [" + mainThreadName +"] cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.err.println("Thread [" + mainThreadName +"] shutdown finished");
		}
	}
}