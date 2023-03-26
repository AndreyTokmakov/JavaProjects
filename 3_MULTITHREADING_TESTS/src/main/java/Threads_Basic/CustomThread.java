/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Custom thread interrupt tests
*
* @name    : CustomThreadInterrupt.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 19, 2020
****************************************************************************/ 

package Threads_Basic;

import java.util.Date;
import Utilities.Utilities;

public class CustomThread extends Thread {
	/** Default timeout: **/
	private static long DEFAULT_TIMEOUT_SECONDS = 60;
	
	/** Sleep timeout: **/
	private static long SLEEP_TIMEOUT = 100;
	
	/** **/
	private static long DELTA = (1000 / CustomThread.SLEEP_TIMEOUT);
	
	/** Run/stop switch. **/
	private volatile boolean run = false;
	
	/** Timeout: **/
	private volatile long timeout = CustomThread.DEFAULT_TIMEOUT_SECONDS;
	
	/** **/
	public CustomThread() {
		super("<ThreadName>");
	}

	/** Run :**/
    public void run()	  {
        System.out.println("[" + new Date() + "] " + Thread.currentThread().getName() + ": Thread started.");
        int count = 0;
        this.run = true;
        while (count++ < (this.timeout * DELTA) && true == this.run) {
        	Utilities.sleep(CustomThread.SLEEP_TIMEOUT);
        }
        this.run = false;
        System.out.println("[" + new Date() + "] " + Thread.currentThread().getName() + ": Thread stopped.");
    }
    
    public void setTimeout(long timeout) {
    	this.timeout = timeout;
    }
    
    public long getTimeout() {
    	return this.timeout;
    }
    
    public void Stop() {
    	run = false;
    }  
}
