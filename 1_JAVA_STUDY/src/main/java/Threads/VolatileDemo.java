package Threads;

public class VolatileDemo {
    volatile private boolean exit = false;
    volatile private boolean isRunning = true;

    final Runnable readInput = new Runnable() {
        @Override
        public void run() {
            int k=-1;
            while (isRunning) {
                try {
                    k = System.in.read() ;
                    exit = k>=0;
                    System.out.println("Input: " + k);
                } catch (Exception e) {
                }
            }
            System.out.println("Input thread finished.");
        }
    };

    final Runnable consumer = new Runnable() {
        @Override
        public void run() {
            int count =1;
            while (false == exit) {
            	count += count;
            	count %= 100;              
                System.out.print(" ");
            }
            isRunning = false;
            System.out.println("Consumer thread done.");
        }
    };

    public void start() {
        new Thread(readInput).start();
        new Thread(consumer).start();
    }

    public static void main(String[] args) {
        new VolatileDemo().start();
    }
}