/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ExchangerDemo class
*
* @name    : ExchangerDemo.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 17, 2020
****************************************************************************/ 

package Exchanger_Tests;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) 
    {    
        Exchanger<String> ex = new Exchanger<String>();
        
        Thread producer = new Thread(new Producer(ex));
        producer.start();
        
        Thread consumer = new Thread(new Consumer(ex));
        consumer.start();
    }
}

class Producer implements Runnable {
    protected Exchanger<String> exchanger;
    protected String message;
  
    public Producer(Exchanger<String> ex){
        this.exchanger=ex;
        message = "Message from Producer";
    }
    
    public void run(){
    	try {
            message = exchanger.exchange(message);
            System.out.println("Producer has received: " + message);
        } catch(InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
} 

class Consumer implements Runnable {
	protected Exchanger<String> exchanger;
	protected String message;
  
	public Consumer(Exchanger<String> ex){
        this.exchanger = ex;
        message = "Message from Consumer";
    }
	
    public void run(){
        try {
            message = exchanger.exchange(message);
            System.out.println("Consumer has received: " + message);
        } catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
} 