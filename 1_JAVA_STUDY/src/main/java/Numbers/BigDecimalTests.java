package Numbers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;
import java.util.OptionalInt;

public class BigDecimalTests
{
    public static void test1()
    {
        double a = 0.1;
        double b = 0.2;
        double c = 0.333;

        double result = Math.abs(a + b - c);
        System.out.println(result);
    }

    public static void nullTest()
    {
        BigDecimal quantity = null ;
        System.out.println(quantity.toString());
    }

    public static void Optional_Subtract()
    {
        Optional<BigDecimal> quantity = Optional.of(BigDecimal.valueOf(123));
        quantity.ifPresent((value) -> {
            value = value.add(BigDecimal.valueOf(123));
            System.out.println("Value after modification:=> " + value);
        });

        System.out.println(quantity);
    }

    public static void Optional_Subtract2()
    {
        OptionalInt quantity = OptionalInt.of(2234);

        quantity.ifPresent((value) -> {
            value = value * 2;
            System.out.println("Value after modification:=> " + value);
        });

        System.out.println(quantity);
    }

    public static void main(String[] args)
    {
        // test1();
        // nullTest();
        // subtract();

        Optional_Subtract();
        Optional_Subtract2();
    }
}
