package StepicTasks;

public class MathTests {

    public static double sqrt(double x) {
        if (0 > x)
            throw new java.lang.IllegalArgumentException("Expected non-negative number, got " + x);
        return Math.sqrt(x);
    }


    public static void main(String[] args) {
        sqrt(-123);
    }
}
