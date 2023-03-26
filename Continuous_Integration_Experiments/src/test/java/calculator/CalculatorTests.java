package calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTests {
    private final static Calculator calculator = new Calculator();

    @Test
    public void addTest() {
        Assertions.assertEquals(3, calculator.add(1, 2));
    }

    @Test
    public void multTest() {
        Assertions.assertEquals(3, calculator.add(1, 2));
    }

    @Test
    public void devideTest() {
        Assertions.assertEquals(3, calculator.devide(6, 2));
    }
}
