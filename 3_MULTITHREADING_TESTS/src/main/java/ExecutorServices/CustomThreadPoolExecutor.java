/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* CustomThreadPoolExecutor.java class
*
* @name    : CustomThreadPoolExecutor.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Feb 15, 2021
****************************************************************************/

package ExecutorServices;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class DemoTask implements Runnable {
	private String name = null;
 
	public DemoTask(String name) {
		this.name = name;
	}
 
	public String getName() {
		return this.name;
	}
 
	@Override
	public void run(){
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Executing : " + name);
	}
}


class MyCustomThreadPoolExecutor extends ThreadPoolExecutor
{
	public MyCustomThreadPoolExecutor(int corePoolSize, 
		   							  int maximumPoolSize,
		   							  long keepAliveTime, 
                                      TimeUnit unit, 
                                      BlockingQueue<Runnable> workQueue)
	{
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
 
	@Override
	protected void beforeExecute(Thread thread, Runnable runnable) {
		super.beforeExecute(thread, runnable);
	}
 
	@Override
	protected void afterExecute(Runnable runnable, Throwable throwable) {
		super.afterExecute(runnable, throwable);
		if (null != throwable) {
			throwable.printStackTrace();
		}
	}
}

public class CustomThreadPoolExecutor {
	
	public static void main(String[] args)
	{
		Integer threadCounter = 0;
		BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);
		MyCustomThreadPoolExecutor executor = new MyCustomThreadPoolExecutor(10, 20, 5000, TimeUnit.MILLISECONDS, blockingQueue);
		
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
			{
				System.out.println("DemoTask Rejected : " + ((DemoTask) r).getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Lets add another time : " + ((DemoTask) r).getName());
				executor.execute(r);
			}
		});
		
		// Let start all core threads initially
		executor.prestartAllCoreThreads();
		while (true) {
			threadCounter++;
			// Adding threads one by one
	        //System.out.println("Adding DemoTask : " + threadCounter);
			executor.execute(new DemoTask(threadCounter.toString()));
			if (threadCounter == 1000)
				break;
	      }
	   }
}
