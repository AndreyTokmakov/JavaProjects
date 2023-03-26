package Closeable_AutoCloseable;

public class AutoCloseableTest {

    static class Resource implements AutoCloseable
    {
        private final String className = getClass().getSimpleName();

        public Resource() {
            System.out.println(className + " created");
        }

        public void DoWork() {
            System.out.println("*** DoWork ***");
        }

        public void DoWork_ThrowException() {
            System.out.println("*** DoWork ***");
            throw new RuntimeException("Opss");
        }

        @Override
        public void close() {
            System.out.println(className + " closed");
        }
    }

    static class Resource2 implements AutoCloseable
    {
        private final String className = getClass().getSimpleName();

        public Resource2() {
            System.out.println(className + " created");
        }

        public void DoWork() {
            System.out.println("*** DoWork ***");
        }

        @Override
        public void close() {
            System.out.println(className + " closed");
        }
    }


    static void test1() {
        try (final Resource res = new Resource()) {
            System.out.println("Do some work");
            res.DoWork();
        }
        catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
        finally {
            System.out.println("Finally block");
        }
    }

    static void test2() {
        try (final Resource res = new Resource()) {
            System.out.println("Do some work");
            res.DoWork_ThrowException();
        }
        catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
        finally {
            System.out.println("Finally block");
        }
    }

    static void test3() {
        try (final Resource res = new Resource();
             final Resource2 res2 = new Resource2()) {
            System.out.println("Do some work");
            res.DoWork();
        }
        catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
        finally {
            System.out.println("Finally block");
        }
    }

    public static void main(String[] args) {
        // test1();
        // test2();
        test3();
    }
}
