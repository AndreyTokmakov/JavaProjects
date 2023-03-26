/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* SimpleTest class
*
* @name    : SimpleTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 27, 2020
****************************************************************************/ 

package Exchanger_Tests;

import java.util.concurrent.Exchanger;

public class SimpleTest {
	public static void main(String[] args) 
	{
		Exchanger<String> exchanger = new Exchanger<>();
		Runnable task = () -> {
			try {
				Thread thread = Thread.currentThread();
				String withThreadName = exchanger.exchange(thread.getName());
				System.out.println(thread.getName() + " exchanged with " + withThreadName);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		new Thread(task).start();
		new Thread(task).start();
	}
}
