package FunctionalInterface.Predicate;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class PredicateChaining
{
    public static <T, U> Function<T, U>
    ternaryOperator(Predicate<? super T> condition,
                    Function<? super T, ? extends U> ifTrue,
                    Function<? super T, ? extends U> ifFalse) {
        return  (T value) -> {
            return condition.test(value) ? ifTrue.apply(value) : ifFalse.apply(value);
        };
    }

    public static void AND()
    {
        Predicate<String> predicate1 = str -> str.startsWith("A");
        Predicate<String> predicate2 = str -> str.length() > 5;
        Predicate<String> predicate3 = predicate2.and(predicate1);

        String text = "ABC";
        boolean b1 = predicate1.test(text);
        boolean b2 = predicate2.test(text);
        boolean b3 = predicate3.test(text);

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }

    public static void main(String[] args)
    {
        // AND();

        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);

        System.out.println(safeStringLength.apply(null));



    }
}
