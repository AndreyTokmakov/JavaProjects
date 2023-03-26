package FunctionalInterface.BooleanSupplier;

import java.util.function.BooleanSupplier;

public class BooleanSupplierTest
{
    public static void main(String[] args)
    {
        BooleanSupplier bsObj1 = () -> true;
        BooleanSupplier bsObj2 = () -> 5 > 50;
        BooleanSupplier bsObj3 = () -> "techndeck.com".equals("justanordinarywriter.com");

        System.out.println("Result of bsObj1: " + bsObj1.getAsBoolean());
        System.out.println("Result of bsObj2: " + bsObj2.getAsBoolean());
        System.out.println("Result of bsObj3: " + bsObj3.getAsBoolean());
    }
}
