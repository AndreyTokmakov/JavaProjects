package Virtual_Threads;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class VirtualThreads
{
    private static void sleepMs(int mSeconds)
    {
        try {
            TimeUnit.MILLISECONDS.sleep(mSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        Thread thread1 = Thread.ofVirtual().start(() -> {
            System.out.println("Thread 1 started   at " + LocalDateTime.now());
            sleepMs(500);
            System.out.println("Thread 1 completed at " + LocalDateTime.now());
        });
        Thread thread2 = Thread.ofVirtual().start(() -> {
            sleepMs(1);
            System.out.println("Thread 2 started   at " + LocalDateTime.now());
            sleepMs(600);
            System.out.println("Thread 2 completed at " + LocalDateTime.now());
        });

        thread1.join();
        thread2.join();
    }
}
