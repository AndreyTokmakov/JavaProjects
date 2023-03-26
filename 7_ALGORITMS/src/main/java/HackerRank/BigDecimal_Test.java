package HackerRank;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.BigInteger;
import java.math.BigDecimal;

/*
Java's BigDecimal class can handle arbitrary-precision signed decimal numbers. Let's test your knowledge of them!
Given an array, , of  real number strings, sort them in descending order — but wait, there's more!
Each number must be printed in the exact same format as it was read from stdin, meaning that  is printed as ,
and  is printed as . If two numbers represent numerically equivalent values (e.g., ), then they must be listed
in the same order as they were received as input).
Complete the code in the unlocked section of the editor below. You must rearrange array 's elements according
to the instructions above
*/

public class BigDecimal_Test {
    private static final Scanner input = new Scanner(System.in);

    private static final class MyComparer implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            final BigDecimal a = new BigDecimal(str1);
            final BigDecimal b = new BigDecimal(str2);
            return b.compareTo(a);
        }
    }

    public static void main(String[] args)
    {
        int count = input.nextInt();
        input.skip("\\R");

        final List<String> numbers = new ArrayList<String>();
        while (count-- > 0) {
            numbers.add(input.nextLine());
        }

        numbers.sort(new MyComparer());
        for (String s: numbers)
            System.out.println(s);
    }
}
