package FunctionalInterface.Consumer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;

class MyUtils
{
    // Static method:
    public static void printContactInfo(String text, Double value)  {
        System.out.println("Text: " + text + ", Value: " + value);
    }

    public static void printContactInfo2(String text, double value)  {
        System.out.println("Text: " + text + ", Value: " + value);
    }
}

public class BiConsumerTests {
    public static void main(String[] args)
    {
        final Map<String, Double> tokens = Map.ofEntries(
                Map.entry("One", 1.0),
                Map.entry("Two", 2.0),
                Map.entry("Three", 3.0)
        );

        BiConsumer<String, Double> consumer1 = MyUtils::printContactInfo;
        ObjDoubleConsumer<String> consumer2 = MyUtils::printContactInfo2;
        tokens.forEach(consumer1);

        consumer2.accept("TEXT", 1.2);
    }
}
