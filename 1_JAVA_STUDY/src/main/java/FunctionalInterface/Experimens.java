package FunctionalInterface;

public class Experimens
{
    @FunctionalInterface
    interface Worker {
        public void doSomething(String text);
    }

    static void invokeFirstMethod(Worker worker) {
        worker.doSomething("TEST_DATAS");
    }

    public static void main(String[] args) {
        invokeFirstMethod((s) -> {
            System.out.println("Method doSomething() called with input: " + s);
        });
    }
}
