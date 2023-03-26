/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Exchanger_UnitTest class
*
* @name    : Exchanger_UnitTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 27, 2020
****************************************************************************/ 

package Exchanger_Tests;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Exchanger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Exchanger_UnitTest {
	@Test
	public void givenThreads_whenMessageExchanged_thenCorrect() {
	    Exchanger<String> exchanger = new Exchanger<>();
	 
	    Runnable taskA = () -> {
	        try {
	            String message = exchanger.exchange("from A");
	            assertEquals("from B", message);
	        } catch (InterruptedException e) {
	        	Thread.currentThread().interrupt();
	            throw new RuntimeException(e);
	        }
	    };
	 
	    Runnable taskB = () -> {
	        try {
	            String message = exchanger.exchange("from B");
	            assertEquals("from A", message);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            throw new RuntimeException(e);
	        }
	    };
	    CompletableFuture.allOf(CompletableFuture.runAsync(taskA), 
	    		                CompletableFuture.runAsync(taskB)).join();
	}
	
	@Test
	public void givenThread_WhenExchangedMessage_thenCorrect() 
			throws InterruptedException {
	    Exchanger<String> exchanger = new Exchanger<>();
	 
	    Runnable runner = () -> {
	        try {
	            String message = exchanger.exchange("from runner");
	            assertEquals("to runner", message);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            throw new RuntimeException(e);
	        }
	    };
	    
	    CompletableFuture<Void> result  = CompletableFuture.runAsync(runner);
	    String msg = exchanger.exchange("to runner");
	    assertEquals("from runner", msg);
	    result.join();
	}
}
