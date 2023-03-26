package Lambdas;

public class FunctionalInterfacesTests
{
    @FunctionalInterface
    interface IFaceOne {
        public void CallOne();
    }

    @FunctionalInterface
    interface IFaceTwo {
        public void CallTwo();
    }

    public static void callOne(IFaceOne callable) {
        callable.CallOne();
    }

    public static void callTwo(IFaceTwo callable) {
        callable.CallTwo();
    }

    public static void main(String[] args)
    {
        callOne(() -> System.out.println(1));
        callTwo(() -> System.out.println(2));
    }
}
