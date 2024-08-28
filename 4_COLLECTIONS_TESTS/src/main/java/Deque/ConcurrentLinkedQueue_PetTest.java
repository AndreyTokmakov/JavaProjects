package Deque;

import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentLinkedQueue_PetTest
{
    public static void main(String[] args) throws InterruptedException
    {
        final ConcurrentLinkedDeque<Integer> concurrentDeque = new ConcurrentLinkedDeque<>();
        final int eventCount = 20_000_000;

        final Thread producer = new Thread(() -> {
            for (int i = 0; i < eventCount; ++i)
                concurrentDeque.add(i);
        });

        final Thread consumer = new Thread(() -> {
            int eventsRead = 0;
            while (eventCount > eventsRead)
            {
                if (null != concurrentDeque.peekFirst())
                    ++eventsRead;
            }
        });

        final long start = System.nanoTime();

        producer.start();
        consumer.start();

        consumer.join();
        producer.join();

        final long nsDuration = System.nanoTime() - start;
        System.out.println("Nano: " + nsDuration + ", Seconds: " + (double)nsDuration / 1_000_000_000);
    }
}
