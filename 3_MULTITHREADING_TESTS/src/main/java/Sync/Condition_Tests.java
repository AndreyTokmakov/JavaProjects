/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Condition_Tests.java class
*
* @name    : Condition_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Mar 7, 2021
****************************************************************************/

package Sync;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Condition_Tests {
	
	public static void main(String[] args) 
	{
		Message msg = new Message();
		Thread messageProducer = new Thread(new MessageProducer(msg));
		Thread messageViewer   = new Thread(new MessageConsumer(msg));
		messageProducer.start();
		messageViewer.start();
	}
	
	protected static void sleep(int timeout) {
		try {
			TimeUnit.MILLISECONDS.sleep(timeout);
		} catch (InterruptedException exc) {
			System.err.println(exc.getMessage());
		}
	}
}


final class MessageProducer implements Runnable {
	private Message message;
	private final List<String> msgs = new ArrayList<String>();
	private final static int PUSH_MESSAGE_DELAY = 1000;

	public MessageProducer(Message msg) {
		message = msg;
		
		/** Init messages list: **/
		msgs.add("Message_1");
		msgs.add("Message_2");
		msgs.add("Message_3");
	}
	
	@Override
	public void run() {
		pusblishMessages();
	}
	
	private void pusblishMessages() {
		msgs.forEach(msg -> {
			message.publishMessage(msg);
			Condition_Tests.sleep(PUSH_MESSAGE_DELAY);
		});

		message.publishMessage("bye");
		message.setEndIt(true);
	}
}

final class MessageConsumer implements Runnable{
	private Message message;

	public MessageConsumer(Message msg) {
		message = msg;
	}

	@Override
	public void run() {
		while(!message.isEndIt())
			message.viewMessage();
	}
}


final class Message {
	final private Lock lock = new ReentrantLock();
	final private Condition producedMsg  = lock.newCondition(); 
	final private Condition consumedMsg = lock.newCondition(); 

	private String message;
	
	private boolean hasMessages ;
	private boolean endIt;

	public void viewMessage() {
		lock.lock();
		try {
			while (false == hasMessages) { // --> no new message wait for new message
				System.out.println("[" + new Date() + "]: Waitig for new messages...");
				producedMsg.await();
			}

			System.out.println("[" + new Date() + "]: Message consumed : " + message);
			hasMessages = false;
			consumedMsg.signal(); // --> message consumed, notify waiting thread

		} catch (final InterruptedException exc) {
			System.err.println("Thread interrupted - viewMessage");
		} finally {
			lock.unlock();
		}
	}
	
	public void publishMessage(String message) {
		lock.lock();
		try {
			while (true == hasMessages) { // --> last message not consumed, wait for it be consumed
				System.out.println("[" + new Date() + "]: Still has some messagess.");
				consumedMsg.await();
			}

			this.message = message;
			this.hasMessages = true;
			
			// Notify waiting thread
			producedMsg.signal(); 
			
			System.out.println("[" + new Date() + "]: New messages pushed.");

		} catch(final InterruptedException exc) {
			System.err.println("Thread interrupted - publishMessage");
		} finally {
			lock.unlock();
		}
	}
	
	public boolean isEndIt() {
		return endIt;
	}
	
	public void setEndIt(boolean endIt) {
		this.endIt = endIt;
	}
}