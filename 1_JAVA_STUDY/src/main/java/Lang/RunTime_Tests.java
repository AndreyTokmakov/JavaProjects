/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Java.lang.Process tests 
*
* @name    : Process_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 18, 2020
****************************************************************************/ 

package Lang;

import java.io.IOException;

class RunTimeWorker {
	/* get the current runtime assosiated with this process. */
	protected Runtime runtime = Runtime.getRuntime();
	
	protected void Run_Process() throws IOException, InterruptedException {
		Process process = this.runtime.exec("notepad.exe");
		Thread.sleep(2000); 
		process.destroy(); 
	}
	
	protected void GetMemory()  { 
        System.out.println("Total memory: " + this.runtime.totalMemory());
        System.out.println("Free memory : " + this.runtime.freeMemory()); 
        System.out.println("CPU available : " + this.runtime.availableProcessors()); 
        System.out.println("max memory : " + this.runtime.maxMemory());
    }
	
	protected void ShutdownHook() throws IOException, InterruptedException {
		class Message extends Thread { 
			public void run()  { 
				System.out.println("Program exiting"); 
		    } 
		}
		
		try { 
			// register Message as shutdown hook 
			this.runtime.addShutdownHook(new Thread(() -> {
				System.out.println("Hook message: Program exiting"); 
			})); 
	              
			// this.runtime.addShutdownHook(new Message()); // OR LIKE THAT
			
			System.out.println("Waiting.."); 
			Thread.sleep(1000); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		System.out.println("Done."); 
	}
	
	protected void GetVersion() {
		Runtime.Version version = this.runtime.version();
		
        System.out.println("feature: " + version.feature());
        System.out.println("interim: " + version.interim()); 
        System.out.println("major  : " + version.major());
        System.out.println("minor  : " + version.minor()); 
	}
}

public class RunTime_Tests {
	private final static RunTimeWorker tester = new RunTimeWorker();
	
	public static void main(String[] args) 
			throws IOException, InterruptedException 
	{
		// tester.Run_Process();
		 tester.GetMemory();
		// tester.ShutdownHook();
		// tester.GetVersion();
	}
}
