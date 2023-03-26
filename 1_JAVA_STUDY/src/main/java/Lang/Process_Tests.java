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

class Process_Tests_Worker {
	
	public void CreateProcess()   { 
        try  {  
            ProcessBuilder builder = new ProcessBuilder("notepad.exe"); 
            Process process = builder.start(); 
            ProcessHandle.Info info = process.info();
            
            System.out.println("Process properties:");
            System.out.println("   pid = " + process.pid());
            System.out.println("   isAlive = " + process.isAlive());
            System.out.println("   arguments = " + info.arguments());
            System.out.println("   commandLine = " + info.commandLine());
            System.out.println("   totalCpuDuration = " + info.totalCpuDuration().get());
      
            // wait 2 seconds 
            System.out.println("\nWaiting"); 
            Thread.sleep(2000); 
      
            // kill the process 
            process.destroy(); 
            System.out.println("Process destroyed"); 
            
            // checking the exit value of subprocess 
            System.out.println("exit value: " + process.exitValue()); 
      
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        } 
    }
	
	public void WaitForEnd()   { 
        try  {  
            ProcessBuilder builder = new ProcessBuilder("notepad.exe"); 
            Process process = builder.start(); 
            process.waitFor();
            System.out.println("Done"); 
      
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        } 
    }
}

public class Process_Tests {
	protected static final Process_Tests_Worker tests = new Process_Tests_Worker();
	
	public static void main(String[] args) 
	{
		// tests.CreateProcess();
		
		tests.WaitForEnd();
	}
}
