package Lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ClosuresTests
{
    @FunctionalInterface
    interface IFaceTest {
        public void CallOne();
    }


    public static void main(String[] args)
    {
        List<String> names = new ArrayList<String>();

        Consumer<String> func = s -> {
            names.add(s);
        };

        func.accept("11");
        func.accept("22");
        func.accept("33");

        System.out.println(names);

    }
}
