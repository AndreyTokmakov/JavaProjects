package StepicTasks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        String str = "-1e3\n" +
                "18 .111 11bbb";
        InputStream is = new ByteArrayInputStream( str.getBytes(StandardCharsets.UTF_8));
        System.setIn(is);

        final Scanner scanner = new Scanner(System.in);
        final StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext())
            stringBuilder.append(scanner.nextLine() + "||");

        final StringTokenizer tokenizer = new StringTokenizer(stringBuilder.toString(), " ||");
        double result = 0;
        while (tokenizer.hasMoreTokens()) {
            try {
                result += Double.parseDouble(tokenizer.nextToken());
            } catch (Exception exc) {
            }
        }
        System.out.println(String.format("%.5f", result));
    }
}