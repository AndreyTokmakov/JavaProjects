package HackerRank;

import java.util.*;
import java.math.BigInteger;

public class BigInteger_Test {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        BigInteger a = new BigInteger(input.nextLine());
        BigInteger b = new BigInteger(input.nextLine());

        System.out.println(a.add(b));
        System.out.println(a.multiply(b));
    }
}
