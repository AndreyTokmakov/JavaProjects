package Numbers;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalTests {
    public static void main(String[] args)
    {
        double a = 0.1;
        double b = 0.2;
        double c = 0.333;

        double result = Math.abs(a + b - c);
        System.out.println(result);
    }
}
