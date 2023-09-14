package Numbers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


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

    private final static class FindCommonElements_3_SortedArrays
    {
        static int[] listToArray(final List<Integer> list)
        {
            return list.parallelStream().mapToInt(Integer::intValue).toArray();
        }

        private static void solutionOne(final List<Integer> ar1,
                                        final List<Integer> ar2,
                                        final List<Integer> ar3)
        {
            // Iterate through three arrays while all arrays have elements
            for (int i = 0, j = 0, k = 0; i < ar1.size() && j < ar2.size() && k < ar3.size(); /** **/ ) {
                // If x = y and y = z, print any of them and move ahead in all arrays
                if (ar1.get(i) == ar2.get(j) && ar2.get(j) == ar3.get(k)) {
                    System.out.print(ar1.get(i) + " ");
                    i++;
                    j++;
                    k++;
                }

                else if (ar1.get(i) < ar2.get(j)) // x < y
                    i++;
                else if (ar2.get(j) < ar3.get(k)) // y < z
                    j++;
                else                       // We reach here when x > y and z < y, i.e., z is smallest
                    k++;
            }
        }

        static void solutionTwo(int[] ar1, int[] ar2, int[] ar3)
        {
            for (int i = 0, j = 0, k = 0; i < ar1.length && j < ar2.length && k < ar3.length; /** **/) {
                if (ar1[i] == ar2[j] && ar2[j] == ar3[k]) {
                    System.out.print(ar1[i] + " ");
                    i++;
                    j++;
                    k++;
                }

                else if (ar1[i] < ar2[j]) // x < y
                    i++;
                else if (ar2[j] < ar3[k]) // y < z
                    j++;
                else                       // We reach here when x > y and z < y, i.e., z is smallest
                    k++;
            }
        }

        public static void test()
        {
            List<Integer> array1 = Arrays.asList(1, 5, 10, 20, 40, 80);
            List<Integer> array2 = Arrays.asList(6, 7, 20, 80, 100);
            List<Integer> array3 = Arrays.asList(3, 4, 15, 20, 30, 70, 80, 120);

            System.out.print("Common elements are ");
            solutionOne(array1, array2, array3);

            System.out.print("\nCommon elements are ");
            solutionTwo(listToArray(array1), listToArray(array2), listToArray(array3));
        }
    }


    public static void main(String[] args)
    {
        // ReverseNumber();
        // leapYearCount(1234);
        // Factorial();
        // MergeArraysTests();

        // calc("abc");

        // FindFirstRepeatingElement.test();

        FindCommonElements_3_SortedArrays.test();
    }
}
