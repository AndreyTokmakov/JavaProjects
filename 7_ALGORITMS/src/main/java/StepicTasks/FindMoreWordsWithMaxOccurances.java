package StepicTasks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FindMoreWordsWithMaxOccurances
{
    public static void test()
    {
        final Scanner scanner = new Scanner(System.in);
        final Map<String, Integer> wordsDict = new HashMap<String, Integer>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            List<String> words = splitByIndex(removeInvalidChars(line));
            words.forEach(s -> {
                if (!s.isEmpty()) {
                    Integer current = wordsDict.get(s);
                    if (null == current)
                        wordsDict.put(s, 1);
                    else
                        wordsDict.put(s, ++current);
                }
            });
        }

        wordsDict.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .reversed().thenComparing(Map.Entry.<String, Integer>comparingByKey()))
                .limit(10).forEach(entry -> { System.out.println(entry.getKey());});
    }

    public static String removeInvalidChars(final String text) {
        final StringBuilder sb = new StringBuilder();
        for (char c: text.toCharArray()) {
            if (false == Character.isLetter(c))
                c = ' ';
            sb.append(c);
        }
        return sb.toString().toLowerCase();
    }

    public static List<String> splitByIndex(String text) {
        final List<String> parts = new ArrayList<String>();
        int pos = 0, end;
        while ((end = text.indexOf(' ', pos)) >= 0) {
            parts.add(text.substring(pos, end));
            pos = end + 1;
        }
        parts.add(text.substring(pos, text.length()));
        return parts;
    }

    public static void main(String[] args) throws UnsupportedEncodingException
    {

        String str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, " +
                "faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. " +
                "Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula " +
                "mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Integer vel odio nec mi tempor dignissim.";

        String str1 = "???? ???? ???? 33 ????!";

        InputStream is = new ByteArrayInputStream(str1.getBytes(StandardCharsets.UTF_8));
        System.setIn(is);

        test();


    }
}
