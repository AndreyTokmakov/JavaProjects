package Closeable_AutoCloseable;

public class SimpleExample
{
    private static class Resource implements AutoCloseable
    {
        public Resource() {
            System.out.println("Resource is created");
        }

        public void doWork() {
            System.out.println("*** DoWork ***");
        }

        @Override
        public void close() {
            System.out.println("Resource is closed");
        }
    }

    public static void main(String[] args) {
        try (final SimpleExample.Resource res = new SimpleExample.Resource()) {
            System.out.println("Do some work");
            res.doWork();
            throw new RuntimeException("Ohh shit");
        }
        catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
}
