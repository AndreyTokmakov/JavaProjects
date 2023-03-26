package Lambdas;

import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class CreateLambdaTests {

    public static void main(String[] args) throws Exception {

        Callable<Integer> c = () -> 42;
        System.out.println(c.call());

        Predicate<Integer> predicate = (x) -> { return x > 10;};
        System.out.println(predicate.test(11));
        System.out.println(predicate.test(9));

    }
}
