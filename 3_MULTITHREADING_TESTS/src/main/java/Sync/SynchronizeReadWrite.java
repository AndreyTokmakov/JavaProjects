package Sync;

import lombok.Getter;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronizeReadWrite {
    private ReentrantReadWriteLock reentrantSharedLock = new ReentrantReadWriteLock();
    private SharedResource resource = new SharedResource();

    private static void sleep(int msSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(msSeconds);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Getter
    private static final class SharedResource {
        private long counter = 0;

        public void Increment() {
            this.counter++;
        }
    }

    public void readData(String threadName)
    {
        final ReentrantReadWriteLock.ReadLock lock = reentrantSharedLock.readLock();
        lock.lock();
        try {
            sleep(100);
            System.out.println(new Date() + " " + threadName + "is Reading resource");
        }
        finally {
            lock.unlock();
        }
    }

    public void writeData(String threadName)
    {
        final ReentrantReadWriteLock.WriteLock lock = reentrantSharedLock.writeLock();
        lock.lock();
        try {
            sleep(500);
            System.out.println(new Date() + " " + threadName + "is Writing resource");
        }
        finally {
            lock.unlock();
        }
    }

    /**
     ReentrantReadWriteLock class

     The ReentrantReadWriteLock class is used to organize shared access to resources with read and write separation.
     It allows you to efficiently organize simultaneous access to data for reading by multiple
     threads and exclusive write access by individual threads.

     Simply put, many threads can enter the read method at the same time, but only one thread can be in write at a given time.
     This allows you to efficiently organize read and write access
     **/

    public static void main(String[] args) throws InterruptedException
    {
        final SynchronizeReadWrite obj = new SynchronizeReadWrite();

        final Thread reader1 = new Thread( () -> {
            for (int i = 0; i < 20; ++i)
            {
                obj.readData("ReaderOne");
            }
        });

        final Thread reader2 = new Thread( () -> {
            for (int i = 0; i < 20; ++i)
            {
                obj.readData("ReaderTwo");
            }
        });

        final Thread writer1 = new Thread( () -> {
            for (int i = 0; i < 4; ++i)
            {
                obj.writeData("WriterOne");
            }
        });

        final Thread writer2 = new Thread( () -> {
            for (int i = 0; i < 4; ++i)
            {
                obj.writeData("WriterTwo");
            }
        });

        writer1.start();
        reader1.start();
        writer2.start();
        reader2.start();

        reader1.join();
        reader2.join();
        writer1.join();
        writer2.join();
    }
}
