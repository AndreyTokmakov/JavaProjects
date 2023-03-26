package IntegralCalculations;

import java.util.function.DoubleUnaryOperator;

class Worker
{
    double rectangleIntegral(DoubleUnaryOperator func,
                             final double start,
                             final double end,
                             final int numSteps) {
        final double step = (end - start) / numSteps;
        double sum = 0, xCoord = 0;

        for (int i = 0; i < numSteps; ++i) {
            // we calculate the argument of the function at this step which is the value
            // of the X coordinate at this step: START + ITER * STEP_SIZEs
            xCoord = start + i * step;

            // calculating the sum of the function values
            sum += func.applyAsDouble(xCoord);
        }
        return (sum * step);
    }



    void Linear_Function_Test() {
        // Simple linear function:
        DoubleUnaryOperator func =  (x) -> { return x;};

        double value = 10;
        int numSteps = 10_000;

        double expected = func.applyAsDouble(value) * value / 2;
        double actual = rectangleIntegral(func, 0, value, numSteps);

        System.out.println(String.format("Actual = %f", actual));
        System.out.println(String.format("Expected = %f", expected));
    }
}

public class LeftRectanglesMethod
{
    public static void main(String[] args)
    {
        Worker worker = new Worker();
        worker.Linear_Function_Test();

    }
}
