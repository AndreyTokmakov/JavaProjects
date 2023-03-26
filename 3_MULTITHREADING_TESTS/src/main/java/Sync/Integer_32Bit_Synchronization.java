package Sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

class Int_Counter_Tester {
    // public int counter = 0;
    public AtomicInteger counter = new AtomicInteger(0);

    class Worker extends Thread {
        @Override
        public void run() {
            int count = 10_000;
            while (count-- > 0) {
                // ++counter;
                counter.incrementAndGet();
            }
            System.out.println(Thread.currentThread().getName() + " done");
        }
    }
}

public class Integer_32Bit_Synchronization {
    public static void main(String[] args) throws InterruptedException {
        Int_Counter_Tester tester = new Int_Counter_Tester();
        for (int i = 0; i < 10; ++i) {
            Thread thread = tester.new Worker();
            thread.start();
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println(tester.counter);
    }
}
