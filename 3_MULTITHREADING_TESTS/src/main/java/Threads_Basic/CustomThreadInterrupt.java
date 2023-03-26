/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Custom thread interrupt tests
*
* @name      : CustomThreadInterrupt.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 19, 2020
* 
****************************************************************************/ 

package Threads_Basic;

import java.util.Date;
import Utilities.Utilities;

public class CustomThreadInterrupt extends Thread {
	/** Default timeout: **/
	private static long DEFAULT_TIMEOUT_SECONDS = 60;
	
	/** Sleep timeout: **/
	private static long SLEEP_TIMEOUT = 100;
	
	/** **/
	private static long DELTA = (1000 / CustomThreadInterrupt.SLEEP_TIMEOUT);
	
	/** Timeout: **/
	private volatile long timeout = CustomThreadInterrupt.DEFAULT_TIMEOUT_SECONDS;
	
	/** **/
	public CustomThreadInterrupt() {
		super("<ThreadName>");
	}
	
	/** Run :**/
    public void run()	  {
        System.out.println("[" + new Date() + "] " + Thread.currentThread().getName() + ": Thread started.");
        int count = 0;
        while (count++ < (this.timeout * DELTA) && false == this.isInterrupted()) {
        	Utilities.sleep(CustomThreadInterrupt.SLEEP_TIMEOUT);
        }
        System.out.println("[" + new Date() + "] " + Thread.currentThread().getName() + ": Thread stopped.");
    }
    
    public void setTimeout(long timeout) {
    	this.timeout = timeout;
    }
    
    public long getTimeout() {
    	return this.timeout;
    }
    
    public void Stop() {
    	this.interrupt();
    }  
}
