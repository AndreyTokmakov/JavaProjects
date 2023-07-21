package Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueue_Tests
{
    public static void putTest() throws InterruptedException
    {
        final BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);

        queue.put("1");
        queue.put("2");

        System.out.println("Trying to put 3 to BlockingQueue");
        queue.put("3"); // Blocking call

        System.out.println("This line will not be reached if queue size == 2");
    }

    public static void addTest() throws InterruptedException
    {
        final BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);

        queue.add("1");
        queue.add("2");

        System.out.println("Trying to add 3 to BlockingQueue");
        queue.add("3"); // Exception

        System.out.println("This line will not be reached if queue size == 2");
    }

    public static void main(String[] args) throws InterruptedException
    {
        // putTest();
        addTest();
    }
}
