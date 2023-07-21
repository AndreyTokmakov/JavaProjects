package Numbers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;


public class Numbers_Algoritms
{

    // In the Gregorian calendar, a year is a leap year in two cases:
    // either it is a multiple of 4, but it is not a multiple of 100, or it is a multiple of 400.
    // Implement a method that calculates the number of leap years from the beginning of
    // our era (the first year) to a given year, inclusive.
    // In fact, the Gregorian calendar was introduced much later, but here,
    // for simplicity, we extend its effect to our entire era.

    public static int leapYearCount(int year) {
        int k4 = year/4;
        int k100 = year/100;
        int k400 = year/400;
        return k4 - k100 + k400;
    }

    public static void ReverseNumber() {
        int number = 1234567;
        System.out.println(number);

        int result = 0;
        while (number > 0) {
            result = result * 10 + number % 10;
            number = number / 10;
        }

        System.out.println(result);
    }

    public static void Factorial() {
        int value = 3;
        BigInteger result = BigInteger.valueOf(value);

        while (--value > 1) {
            // result *= value;
            // result *= BigInteger.valueOf(value);
            result = result.multiply(BigInteger.valueOf(value));
        }

        System.out.println(result);
    }

    //-----------------------------------------------------------------------------------------

    public static int[] mergeArrays(int[] a1, int[] a2) {
        if (0 == a1.length)
            return a1;
        else if (0 == a2.length)
            return a2;

        int aPos = 0, bPos = 0, index = 0;
        int [] result = new int[a1.length + a2.length];
        while (true) {
            if (aPos >= a1.length && bPos < a2.length) {
                result[index++] = a2[bPos++];
                continue;
            }
            if (bPos >= a2.length && aPos < a1.length) {
                result[index++] = a1[aPos++];
                continue;
            }

            if (bPos >= a2.length && aPos >= a1.length)
                break;
            else {
                if (a1[aPos] > a2[bPos])
                    result[index++] = a2[bPos++];
                else
                    result[index++] = a1[aPos++];
            }
        }
        return result;
    }

    public static int[] mergeArrays2(int[] a1, int[] a2) {
        int [] result = new int[a1.length + a2.length];
        int pos1 = 0;
        int pos2 = 0;
        while(pos1<a1.length || pos2<a2.length) {
            result[pos1+pos2] = (pos1<a1.length && (pos2 == a2.length || a1[pos1]<a2[pos2]) ?
                    a1[pos1++] : a2[pos2++]);
        }
        return result;
    }

    static void MergeArraysTests() {
        int[] a1 = {1,3,5,6,7,9,233,23232332};
        int[] a2 = {2,4,4,123};
        int[] a3 = mergeArrays(a1, a2);
        int[] a4 = mergeArrays2(a1, a2);

        System.out.println(Arrays.toString(a3));
        System.out.println(Arrays.toString(a4));
    }


    static void calc(String s)
    {
        BigInteger sum = new BigInteger("1");
        for (char c: s.toCharArray())
        {
            int ord = c;
            sum.multiply(BigInteger.valueOf(ord));
        }
        System.out.println();
    }

    private final static class FindFirstRepeatingElement
    {
        private static int solutionOne(int[] numbers)
        {
            int min = -1;
            final HashSet<Integer> set = new HashSet<>();
            for (int idx = numbers.length - 1; idx >= 0; idx--)  {
                if (!set.add(numbers[idx]))
                    min = idx;
            }

            return -1 == min ? -1 : numbers[min];
        }

        public static void test()
        {
            final int[] arr = { 10, 5, 3, 4, 3, 5, 6 };
            int result = solutionOne(arr);

            System.out.println(result);
        }
    }

    public static void main(String[] args)
    {
        // ReverseNumber();
        // leapYearCount(1234);
        // Factorial();
        // MergeArraysTests();

        // calc("abc");

        FindFirstRepeatingElement.test();
    }
}
