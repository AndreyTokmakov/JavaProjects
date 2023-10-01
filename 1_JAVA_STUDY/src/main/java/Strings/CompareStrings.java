package Strings;

public class CompareStrings {
    public static void main(String[] args) {
        String first = "hello";
        String second = "hello";

        if (first == second)
        {
            System.out.printf("%s == %s%n", first, second);
        }

        if (first.equals(second))
        {
            System.out.printf("%s equals %s%n", first, second);
        }
    }
}
