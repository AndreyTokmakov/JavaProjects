/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* ArrayBlockingQueue_Tests.java class
*
* @name    : ArrayBlockingQueue_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Feb 15, 2021
****************************************************************************/

package Concurrent_Collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueue_Tests {

    public static void main(String[] args) throws InterruptedException 
    {
        ArrayBlockingQueue<Integer> priorityBlockingQueue = new ArrayBlockingQueue<>(5);
 
        // Producer thread
        new Thread(() ->  {
            int i = 0;
            try {
                while (true) {
                    priorityBlockingQueue.put(++i);
                    System.out.println("Added : " + i);
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
 
        }).start();
 
        // Consumer thread
        new Thread(() -> {
            try {
                while (true) {
                    Integer poll = priorityBlockingQueue.take();
                    System.out.println("Polled : " + poll);
                    Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
