package Queue;

import java.util.AbstractQueue;
import java.util.concurrent.*;

public class SynchronousQueue_Test {

    public static void test()
    {
        SynchronousQueue<String> queue = new SynchronousQueue<String>();

        new Thread(() -> {
            try {
                System.out.println("Producer: putting value");
                queue.put("Value");
                System.out.println("Producer: value put ");
            }
            catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("Consumer: querying value");
                String val = queue.take();
                System.out.println("Consumer: value = " + val);
            }
            catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        }).start();
    }

    public static void test2() throws InterruptedException {
        final ExecutorService executor = Executors.newFixedThreadPool(2);
        final SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        Runnable producer = () -> {
            Integer producedElement = ThreadLocalRandom.current().nextInt();
            System.out.println("producedElement = " + producedElement);
            try {
                queue.put(producedElement);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        Runnable consumer = () -> {
            try {
                final Integer consumedElement = queue.take();
                System.out.println("consumedElement = " + consumedElement);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        executor.execute(producer);
        executor.execute(consumer);

        executor.awaitTermination(500, TimeUnit.MILLISECONDS);
        executor.shutdown();
        System.out.println("Queue size = " + queue.size());
    }

    public static void main(String[] args) throws InterruptedException {
        // test();
        test2();
    }
}
