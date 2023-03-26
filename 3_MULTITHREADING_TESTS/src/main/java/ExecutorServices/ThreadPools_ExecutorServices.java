/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ThreadPools and ExecutorServices Future tests 
*
* @name    : ThreadPools_ExecutorServices.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 05, 2020
****************************************************************************/ 

package ExecutorServices;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//============================================================================
//					WorkStealingPool_Tester
//============================================================================
class WorkStealingPool_Tester {
	/** ExecutorService **/
	final ExecutorService executor = Executors.newWorkStealingPool();
	
	/** MyTask class. **/
	class MyTask implements Callable<String> {
		private String result = "";
		private int sleepSeconds = 0;
		
		public MyTask(final String result,
					  int sleepSeconds){
			this.result = result;
			this.sleepSeconds = sleepSeconds;
		}
		
		@Override
		public String call() throws Exception {
			TimeUnit.SECONDS.sleep(sleepSeconds);
			return result;
		}
	} 

	public WorkStealingPool_Tester() {
		// TODO:
	}
	
	
	public void TestPool_InvokeAll_1() {	
		final List<Callable<String>> callables = Arrays.asList(() -> "task1",
															   () -> "task2",
															   () -> "task3");
		try {
			executor.invokeAll(callables).stream().map(future -> {
				try {
					return future.get();
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}).forEach(System.out::println);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void TestPool_InvokeAll_2()
	{	
		final List<Callable<String>> callables = Arrays.asList(() -> "task1",
														 () -> "task2",
														 () -> "task3");	
		try {
			this.executor.invokeAll(callables).stream().map(future -> {
				try {
					return future.get();
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}}).forEach(System.out::println);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		try {
		    System.out.println("attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		} finally {
		    if (!executor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.out.println("shutdown finished");
		}
	}
	
	public void TestPool_InvokeAll_3() {	
		final List<MyTask> callables = Arrays.asList(new MyTask("task1", 1),
		   		 								     new MyTask("task2", 1),
		   		 								     new MyTask("task3", 1));
		try {
			this.executor.invokeAll(callables).stream().map(future -> {
				try {
					return future.get();
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}}).forEach(System.out::println);
		} catch (final InterruptedException exc) {
			exc.printStackTrace();
		}

		try {
		    System.out.println("attempt to shutdown executor");
		    this.executor.shutdown();
		    this.executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (final InterruptedException exc) {
		    System.err.println("tasks interrupted");
		} finally {
		    if (false == this.executor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    this.executor.shutdownNow();
		    System.out.println("shutdown finished");
		}
	}
	
	public void TestPool_InvokeAny() {	
		final List<MyTask> callables = Arrays.asList(new MyTask("task1", 1),
											   		 new MyTask("task2", 3),
											   		 new MyTask("task3", 3));
		try {
			final String result = this.executor.invokeAny(callables);
			System.out.println(result);
		} catch (final InterruptedException | ExecutionException exc) {
			exc.printStackTrace();
		}
		
		try {
		    System.out.println("attempt to shutdown executor");
		    this.executor.shutdown();
		    this.executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (final InterruptedException exc) {
		    System.err.println("tasks interrupted");
		} finally {
		    if (false == this.executor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    this.executor.shutdownNow();
		    System.out.println("shutdown finished");
		}
	}
}

//============================================================================
//					FixedThreadExecutor_Tester
//============================================================================
class FixedThreadExecutor_Tester {
	
	private void sleep(int timeout) {
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (final InterruptedException exc) {
			exc.printStackTrace();
		}
	}
	
	class TestTask implements Runnable {
		public void run() {
			try {
				Long duration = (long) (Math.random() * 5);
	            System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep(duration);
	            System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
	         } catch (final InterruptedException exc) {
				exc.printStackTrace();
	         }
		}
	}
	
	public void SimpleTasks() throws ExecutionException, InterruptedException {
		final Callable<Integer> task = () -> {
			final String threadName = Thread.currentThread().getName();
			try {
				for (int i = 0; i < 5; i++) {
					TimeUnit.MILLISECONDS.sleep(250);
					System.out.println("[" + threadName + "]: " + "Task in progess " + i);
				}
				return 123;   
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};
		
		final ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 6; i++) {
			Future<Integer> result = service.submit(task);
			//System.out.println(result.get());
		}
		service.shutdown();
	}
	
	public void Add_More_Task_Than_PoolSize() throws ExecutionException, InterruptedException {
		final Callable<Integer> task = () -> {
			final String threadName = Thread.currentThread().getName();
			System.out.println("[" + threadName + "]: " + new Date() + ": Task started");
			sleep(5);
			System.out.println("[" + threadName + "]: " + new Date() + ": Task completed");
			return 0;
		};
		
		final ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 6; i++) {
			Future<Integer> result = service.submit(task);
			//System.out.println(result.get());
		}
		service.shutdown();
	}
	
	public void DefaultTests() throws InterruptedException {
	      ExecutorService executor = Executors.newFixedThreadPool(2);
	      // Cast the object to its class type
	      ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;

	      //Stats before tasks execution
	      System.out.println("Largest executions: " + pool.getLargestPoolSize());
	      System.out.println("Maximum allowed threads: " + pool.getMaximumPoolSize());
	      System.out.println("Current threads in pool: " + pool.getPoolSize());
	      System.out.println("Currently executing threads: " + pool.getActiveCount());
	      System.out.println("Total number of threads(ever scheduled): " + pool.getTaskCount());

	      executor.submit(new TestTask());
	      executor.submit(new TestTask());

	      //Stats after tasks execution
	      System.out.println("Core threads: " + pool.getCorePoolSize());
	      System.out.println("Largest executions: " + pool.getLargestPoolSize());
	      System.out.println("Maximum allowed threads: " + pool.getMaximumPoolSize());
	      System.out.println("Current threads in pool: " + pool.getPoolSize());
	      System.out.println("Currently executing threads: " + pool.getActiveCount());
	      System.out.println("Total number of threads(ever scheduled): " + pool.getTaskCount());

	      executor.shutdown();
	   }  

	public FixedThreadExecutor_Tester() {
		// TODO:
	}
	
	public void TestPool_CallableTest()
	{	
		final Callable<Integer> task = () -> {
			final String threadName = Thread.currentThread().getName();
			try {
				for (int i = 1; i < 9; i++) {
					TimeUnit.SECONDS.sleep(1);
					System.out.println("[" + threadName + "]: " + "Task in progess " + i);
				}
				return 123;   
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};

		final String threadName = Thread.currentThread().getName();
		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);
		
		System.out.println("[" + threadName + "]: " + "Future is done? (Attempt 1): " + future.isDone());
		try {
			System.out.println("[" + threadName + "]: " + "Waiting for future to be completed...");
			Integer result = future.get();
			System.out.println("[" + threadName + "]: " + "Future is done? (Attempt 2): " + future.isDone());
			System.out.println("[" + threadName + "]: " + "result: " + result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		try {
		    System.out.println("[" + threadName + "]: " + "attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		    System.err.println("[" + threadName + "]: " + "tasks interrupted");
		} finally {
		    if (!executor.isTerminated()) {
		        System.err.println("[" + threadName + "]: " + "cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.out.println("[" + threadName + "]: " + "shutdown finished");
		}
	}
	
	// TODO: Same as |TestPool_CallableTest| above???
	public void TestPool_Timeout()	{	
		final Callable<Integer> myTask = () -> {
			final String threadName = Thread.currentThread().getName();
			try {
				for (int i = 1; i < 9; i++) {
					TimeUnit.SECONDS.sleep(1);
					System.err.println("[" + threadName + "]: " + "Task in progess " + i);
				}
				return 123;
			} catch (final InterruptedException exc) {
				throw new IllegalStateException("[" + threadName + "]: " + "The task has been interrupted", exc);
			}
		};
		
		final String mainThreadName = Thread.currentThread().getName();
		final ExecutorService executor = Executors.newFixedThreadPool(1);
		final Future<Integer> future = executor.submit(myTask);
		
		System.out.println("[" + mainThreadName + "]: " + "Future is done? (Attempt 1): " + future.isDone());
		try {
			System.out.println("[" + mainThreadName + "]: " + "Waiting for future to be completed...");
			Integer result = future.get(3, TimeUnit.SECONDS);
			System.out.println("[" + mainThreadName + "]: " + "Future is done? (Attempt 2): " + future.isDone());
			System.out.println("[" + mainThreadName + "]: " + "result: " + result);
		} catch (final InterruptedException | ExecutionException | TimeoutException exc) {
			System.err.println("[" + mainThreadName + "]: Fututre get TimeoutException cauth. ");
			exc.printStackTrace();
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep(100);
			System.out.println("[" + mainThreadName + "]: " + "attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("[" + mainThreadName + "]: " + "tasks interrupted");
		} finally {
		    if (!executor.isTerminated()) {
		    	 System.err.println("[" + mainThreadName + "]: " + "cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.out.println("[" + mainThreadName + "]: " + "shutdown finished");
		}
	}
}


//============================================================================
//					SingleThreadExecutor_Tester
//============================================================================

class SingleThreadExecutor_Tester {
	/** SingleThreadExecutor pool instance. **/
	private ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
	
	public SingleThreadExecutor_Tester() {
		// TODO:
	}
	
	/** **/
	class LoopTask implements Runnable {
		// Thread name:
		private String loopTaskName;
		
		public LoopTask(String loopTaskName) {
			super();
			this.loopTaskName = loopTaskName;
		}
	 
		@Override
		public void run() {
			System.out.println("Starting "+loopTaskName);
			for (int i = 1; i <= 10; i++) {
				System.out.println("Executing "+ loopTaskName + " with " + Thread.currentThread().getName() + "====" + i);
			}
			System.out.println("Ending "+ loopTaskName);
		}
	}
	
	public void Run_Three_Task() {
		for (int i = 1; i <= 3; i++) {
			LoopTask loopTask = new LoopTask("LoopTask " + i);
			threadExecutor.submit(loopTask);
		}
		threadExecutor.shutdown();
	}
	
	public void SimpleTest() {	
		threadExecutor.submit(() -> {
	    	final String threadName = Thread.currentThread().getName();
		    System.out.println(new Date() + "[" + threadName + "]: " + "Hello");
		    ThreadPools_ExecutorServices.Sleep(5000);
		    System.out.println("[" + threadName + "]: " + "Done");
		});
		
		threadExecutor.shutdown();
	}
	
	public void TestPool_SipleTask() {	
	    Runnable task = new Runnable() {
	        public void run() {
	            System.out.println("Task started");
	            ThreadPools_ExecutorServices.Sleep(1000);
	            System.out.println(Thread.currentThread().getName());
	        }
	    };
	    
	    System.out.println("Staring the task.");
	    threadExecutor.execute(task);
	    
	    threadExecutor.shutdown();
	}
	
	/** 
	 *   Creates Future object from callable Task object.
	 *   wait until the task returns the result. 
	 **/
	public void TestPool_FutureFromTask() {
		Callable<Integer> task = new Callable<Integer>() {
			public Integer call() {
				System.out.println("Task started. Sleeping for 5 seconds");
				ThreadPools_ExecutorServices.Sleep(5000);
				return 12345;
			}
		};
	  
		System.out.println("Staring the task.");
		Future<Integer> result = threadExecutor.submit(task);
	  
		try {
			Integer returnValue = result.get();
			System.out.println("Return value = " + returnValue);
		} catch (final InterruptedException | ExecutionException exc) {
			exc.printStackTrace();
		}
		threadExecutor.shutdown();
	}
	
	public void TestPool_Shutdown_AwaitTermination_1() {	
		threadExecutor.submit(() -> {
		    String threadName = Thread.currentThread().getName();
		    System.out.println("Hello " + threadName);
		    try {
				TimeUnit.SECONDS.sleep(5);
			} catch (final InterruptedException exc) {
				exc.printStackTrace();
			}
		});
		
		try {
		    System.out.println("attempt to shutdown executor");
		    threadExecutor.shutdown();
		    threadExecutor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		} finally {
		    if (false == threadExecutor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    threadExecutor.shutdownNow();
		    System.out.println("shutdown finished");
		}
	}
	
	public void TestPool_Shutdown_AwaitTermination_2() {	
		/** Initialize the task to be run. **/
		final Callable<Integer> task = new Callable<Integer>() {
			public Integer call() {
				final String threadName = Thread.currentThread().getName();
			    try {
			    	TimeUnit.MICROSECONDS.sleep(250);
			    	System.out.println("[" + threadName + "]: Task is started. Sleeping for 7 seconds");
					TimeUnit.SECONDS.sleep(7);
				} catch (final InterruptedException exc) {
					System.err.println("[" + threadName + "]: InterruptedException: " + exc.getMessage());
					// exc.printStackTrace();
				}
				return 12345;
			}
		};
		
		System.out.println("Staring the task.");
		Future<Integer> future = threadExecutor.submit(task);
		
	    try {
			TimeUnit.SECONDS.sleep(1);
		} catch (final InterruptedException exc) {
			exc.printStackTrace();
		}
		
		try {
		    System.out.println("Attempting to shutdown executor..");
		    threadExecutor.shutdown();
		    System.out.println("Awaiting for termination for 5 seconds.");
		    boolean waitResult = threadExecutor.awaitTermination(5, TimeUnit.SECONDS);
		    System.out.println("Wait result: " + waitResult);
		} catch (final InterruptedException exc) {
		    System.err.println("Tasks interrupted");
		} finally {
		    if (false == threadExecutor.isTerminated()) {
		        System.err.println("Canceling non-finished tasks...");
		    }
		    
		    System.out.println("Force shutdown!!!");
		    threadExecutor.shutdownNow();
		    System.out.println("Shutdown finished");
		}
	}
	
	public void Shutdown_InterruptedException() {
		threadExecutor.execute(() -> System.out.println("Print this."));
		threadExecutor.execute(() -> System.out.println("and this one to."));
		threadExecutor.shutdown();
		
		try {
			threadExecutor.awaitTermination(4, TimeUnit.SECONDS);
		} catch (final InterruptedException exc) {
		    System.err.println("Tasks interrupted");
		    System.err.println(exc.getMessage());
		}
		System.out.println("\n\n");
	}
}

//============================================================================
//                   CachedThreadPool_Tester
//============================================================================

class CachedThreadPool_Tester {
	
	/** CountDownClock class: This class represents a countdown clock. **/
	static class CountDownClock extends Thread {
		/** **/
		private String clockName;

		public CountDownClock(String clockName) {
			this.clockName = clockName;
		}

		public void run() {
			String threadName = Thread.currentThread().getName();
			for (int i = 5; i >= 0; i--) {
				System.out.printf("%s -> %s: %d\n", threadName, clockName, i);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (final InterruptedException exc) {
					exc.printStackTrace();
				}
			}
		}
	}

	/** Task class: **/
	static class Task implements Runnable {
		public void run() {
			try {
				Long duration = (long) (Math.random() * 5);
				System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep(duration);
				System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
			} catch (final InterruptedException exc) {
				exc.printStackTrace();
			}
		}
	}

	/** CallableThread class: **/
    class CallableThread implements Callable<Integer> {
        @Override
        public Integer call() {
            int count;
            for (count = 0; count < 5; count++) {
                System.out.println("call:" + count);
            }
            return count;
        }
    }

	/** RunnableThread class: **/
    class RunnableThread implements Runnable {
        @Override
        public void run() {
            int count = 0;
            for (count = 0; count < 5; count++) {
                System.out.println("run:" + count);
            }
        }
    }
	
    /////////////////////////////////////////////////////////
    
    private ExecutorService cachedPoolexecutor = Executors.newCachedThreadPool();
	
	public void TestPool_ChachedPoolExecutor() {
		cachedPoolexecutor.execute(new CountDownClock("A"));
		cachedPoolexecutor.execute(new CountDownClock("B"));
		cachedPoolexecutor.execute(new CountDownClock("C"));
		cachedPoolexecutor.execute(new CountDownClock("D"));
		cachedPoolexecutor.shutdown();
	}
	
	public void TestPool_ChachedPoolExecutor_1() {
		// Cast the object to its class type
		ThreadPoolExecutor executor = (ThreadPoolExecutor) cachedPoolexecutor;
		
		executor.submit(() -> {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " done.");
			return null;
		});
		executor.submit(() -> {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " done.");
			return null;
		});		
		executor.submit(() -> {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " done.");
			return null;
		});		
			 
		// assertEquals(3, executor.getPoolSize());
		// assertEquals(0, executor.getQueue().size());
		
		System.out.println("executor.getPoolSize() = " + executor.getPoolSize());
		System.out.println("executor.getQueue().size() = " + executor.getQueue().size());
	}
	
	
	public void TestPool_ChachedPoolExecutor_2() throws InterruptedException {
		// Cast the object to its class type
		ThreadPoolExecutor pool = (ThreadPoolExecutor) cachedPoolexecutor;

		//  Stats before tasks execution
		System.out.println("Largest executions: " + pool.getLargestPoolSize());
		System.out.println("Maximum allowed threads: " + pool.getMaximumPoolSize());
		System.out.println("Current threads in pool: " + pool.getPoolSize());
		System.out.println("Currently executing threads: " + pool.getActiveCount());
		System.out.println("Total number of threads(ever scheduled): " + pool.getTaskCount());

		cachedPoolexecutor.submit(new Task());
		cachedPoolexecutor.submit(new Task());

        //  Stats after tasks execution
		System.out.println("Core threads: " + pool.getCorePoolSize());
		System.out.println("Largest executions: " + pool.getLargestPoolSize());
		System.out.println("Maximum allowed threads: " + pool.getMaximumPoolSize());
		System.out.println("Current threads in pool: "+ pool.getPoolSize());
		System.out.println("Currently executing threads: " + pool.getActiveCount());
		System.out.println("Total number of threads(ever scheduled): " + pool.getTaskCount());

		cachedPoolexecutor.shutdown();
   }  
	
	public void TestPool_CallableAndRunnable() throws InterruptedException, ExecutionException {
		// Runnable thread start to execute.
		cachedPoolexecutor.execute(new RunnableThread());
        
        // Callable thread starts to execute
        Future<Integer> future = cachedPoolexecutor.submit(new CallableThread());
        
        //gets value of callable thread
        int val = future.get();
        System.out.println(val);
        
        //checks for thread termination
        boolean isTerminated = cachedPoolexecutor.isTerminated();
        System.out.println(isTerminated);
        
        // waits for termination for 30 seconds only
        cachedPoolexecutor.awaitTermination(30,TimeUnit.SECONDS);
        cachedPoolexecutor.shutdownNow();
    }
}

//============================================================================
//                         SingleThreadScheduledExecutor_Tester
//============================================================================

class ThreadScheduledExecutor_Tester {
	
	public void SimpleSchedulerTest() {
		final String mainThreadName = Thread.currentThread().getName();
        System.out.println("Thread [" + mainThreadName +"] started. ");
        
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // Create a task
        final Runnable task1 = () -> {
        	final String threadName = Thread.currentThread().getName();
            System.out.println("Executing the task thread [" + threadName + "] at " + new Date());
        };

        scheduledExecutorService.scheduleAtFixedRate(task1, 0, 2, TimeUnit.SECONDS);
        System.out.println("Thread [" + mainThreadName +"] finished");
    }
	
	public void ExecutorService_SingleThreadScheduledExecutor() {	
		final String mainThreadName = Thread.currentThread().getName();
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
		final String mainThreadName = Thread.currentThread().getName();
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

		final String mainThreadName = Thread.currentThread().getName();
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
		 
		final String mainThreadName = Thread.currentThread().getName();
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
 
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                      MAIN                                                            //
//////////////////////////////////////////////////////////////////////////////////////////////////////////

/** @author tokmakov     **/
/** @class  ThreadPools_ExecutorServices **/
public class ThreadPools_ExecutorServices {

	/** ThreadTester class constructor : **/
	public ThreadPools_ExecutorServices() {
		// TODO Auto-generated constructor stub
	}
	
	public static void Sleep(int milliseconds) {
		try {
			Thread.currentThread();
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** MAIN : 
	 * @throws InterruptedException 
	 * @throws ExecutionException */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		// TestPool_Scheduled_Delay();
		// TestPool_Scheduled_WithFixedDelay();	
		
		CachedThreadPool_Tester cachedThreadPoolTests = new CachedThreadPool_Tester();
		// cachedThreadPoolTests.TestPool_ChachedPoolExecutor();
		// cachedThreadPoolTests.TestPool_ChachedPoolExecutor_1();
		// cachedThreadPoolTests.TestPool_ChachedPoolExecutor_2();
		// cachedThreadPoolTests.TestPool_CallableAndRunnable();
		
		SingleThreadExecutor_Tester singleThreadExecutorTests = new SingleThreadExecutor_Tester();
		// singleThreadExecutorTests.SimpleTest();
		// singleThreadExecutorTests.Run_Three_Task();
		// singleThreadExecutorTests.TestPool_SipleTask();
		// singleThreadExecutorTests.TestPool_FutureFromTask();
		// singleThreadExecutorTests.TestPool_Shutdown_AwaitTermination_1();
		// singleThreadExecutorTests.TestPool_Shutdown_AwaitTermination_2();
		// singleThreadExecutorTests.Shutdown_InterruptedException();
		
		FixedThreadExecutor_Tester fixedThreadExecutorTests = new FixedThreadExecutor_Tester();
		// fixedThreadExecutorTests.DefaultTests();
		// fixedThreadExecutorTests.SimpleTasks();
		fixedThreadExecutorTests.Add_More_Task_Than_PoolSize();
		// fixedThreadExecutorTests.TestPool_CallableTest();
		// fixedThreadExecutorTests.TestPool_Timeout();
		
		WorkStealingPool_Tester workStealingPool = new WorkStealingPool_Tester();
		// workStealingPool.TestPool_InvokeAll_1();
		// workStealingPool.TestPool_InvokeAll_2();
		// workStealingPool.TestPool_InvokeAll_3();
		// workStealingPool.TestPool_InvokeAny();
		
		ThreadScheduledExecutor_Tester singleThreadScheduled = new ThreadScheduledExecutor_Tester();
		// singleThreadScheduled.SimpleSchedulerTest();
		// singleThreadScheduled.ExecutorService_SingleThreadScheduledExecutor();
		// singleThreadScheduled.ScheduledThreadPool_FixedRate();
		// singleThreadScheduled.TestPool_Scheduled_Delay();
		// singleThreadScheduled.TestPool_Scheduled_WithFixedDelay();
	}
}