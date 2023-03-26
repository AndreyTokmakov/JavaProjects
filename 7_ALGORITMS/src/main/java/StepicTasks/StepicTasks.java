package StepicTasks;

public class StepicTasks {

    // In the Gregorian calendar, a year is a leap year in two cases:
    // either it is a multiple of 4, but it is not a multiple of 100, or it is a multiple of 400.
    // Implement a method that calculates the number of leap years from the beginning of
    // our era (the first year) to a given year, inclusive.
    // In fact, the Gregorian calendar was introduced much later, but here,
    // for simplicity, we extend its effect to our entire era.

    public static int _leapYearCount(int year) {
        int k4 = year/4;
        int k100 = year/100;
        int k400 = year/400;
        return k4 - k100 + k400;
    }

    public static void FindLeapYears() {
        int count = _leapYearCount(2020);
        System.out.println(String.format("There was %d leap year in the range 0 - 2000", count));
    }

    //--------------------------------------------------------------------

    /** Implement a method that returns the answer to the question: is it true that a + b = c?
     The permissible error is 0.0001 (1E-4) **/

    public static boolean doubleExpression(double a,
                                           double b,
                                           double c) {;
        double diff = Math.abs(a + b - c);
        return 0.0001 >= diff;
    }

    static void DoubleExpression_Test() {
        boolean result = doubleExpression(0.10001, 0.20001, 0.3);
        System.out.println(result);
    }

    //-------------------------------------------------------------------------

    /** Implement a method that returns the letter that stands in the UNICODE table after the " \ "
     * character (backslash) at a distance of a.
     */

    public static char _charExpression(int a) {
        return (char)('\\' + a);
    }

    public static void  charExpressionTest() {
        {
            System.out.println(_charExpression(1));
        }
    }

    //-------------------------------------------------------------------------

    /** Checks if given valueis a power of two. */
    public static boolean _isPowerOfTwo(int value) {
        value = Math.abs(value);
        if (1 == value)
            return true;
        else if (0 == value || 0 != value % 2)
            return false;
        return Integer.bitCount(Math.abs(value)) == 1;
    }

    public static void isPowerOfTwoTests() {
        int value = 0;
        int n = 0;
        while (true) {
            value = (int)Math.pow(2, n++);
            if (value >= Integer.MAX_VALUE)
                break;

            boolean result = _isPowerOfTwo(value);
            if (false == result) {
                System.out.println("Erorr: " + value + ". Result = " + result);
            }
        }
        System.out.println("OK");
    }

    //-------------------------------------------------------------------------


    public static void main(String[] args)
    {
        // FindLeapYears();
        // DoubleExpression_Test();
        // charExpressionTest();
        isPowerOfTwoTests();
    }
}
