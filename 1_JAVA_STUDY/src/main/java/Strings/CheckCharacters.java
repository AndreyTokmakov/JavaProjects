package Strings;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CheckCharacters
{
    public static String removeInvalidChars(final String text) {
        final StringBuilder sb = new StringBuilder();
        for (char c: text.toCharArray())
            if (' ' == c || Character.isLetter(c))
                sb.append(c);
        return sb.toString().toLowerCase();
    }

    public static void CheckIsLetter_UTF8() {
        String str = "Мама мыла раму 33 раза!";


        for (char c: str.toCharArray()) {
            // System.out.println("Character '" + c + "' is letter = " + Character.isLetter(c));
        }


        System.out.println(str);
        System.out.println(removeInvalidChars(str));
    }

    public static void CheckIsLetter() {
        String str = "12345_QWERTY_aa  a_$#*$_-++_FFF";

        for (char c: str.toCharArray()) {
            System.out.println("Character '" + c + "' is letter = " + Character.isLetter(c));
        }
    }

    public static void CheckIsAlphabetic() {
        String str = "12345_QWERTY_aa  a_$#*$_-++_FFF";

        for (char c: str.toCharArray()) {
            System.out.println("Character '" + c + "' is letter = " + Character.isAlphabetic(c));
        }
    }

    public static void CheckIsLetterOrDigit() {
        String str = "12345_QWERTY_aa  a_$#*$_-++_FFF";

        for (char c: str.toCharArray()) {
            System.out.println("Character '" + c + "' is letter = " + Character.isLetterOrDigit(c));
        }
    }

    public static void Read_From_SystemIN_UTF8() {
        String str1 = "Мама мыла раму 33 раза!";
        InputStream is = new ByteArrayInputStream(str1.getBytes(StandardCharsets.UTF_8));
        System.setIn(is);
        final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.println("[" + line + "]");
            System.out.println("[" + removeInvalidChars(line) + "]");
        }
    }

    public static void main(String[] args)
    {
        // CheckIsLetter();
        // CheckIsAlphabetic();
        CheckIsLetterOrDigit();
        // CheckIsLetter_UTF8();
        // Read_From_SystemIN_UTF8();
    }
}
