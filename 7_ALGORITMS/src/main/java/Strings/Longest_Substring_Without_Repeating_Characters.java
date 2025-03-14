package Strings;

import java.util.HashMap;
import java.util.Map;

public class Longest_Substring_Without_Repeating_Characters
{
    public static int length_of_longest_substring_1(String s)
    {
        int[] chars = new int[128];
        int result = 0;
        for (int i = 0, j = 0; j < s.length();)
        {
            char right_char = s.charAt(j);
            chars[right_char]++;
            while (chars[right_char] > 1) {
                char left_char = s.charAt(i);
                chars[left_char]--;
                i++;
            }
            result = Math.max(result, j - i + 1);
            j++;
        }
        return result;
    }

    public static int length_of_longest_substring_2(String s)
    {
        int result = 0;
        Map<Character, Integer> hash_map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length();)
        {
            char ch = s.charAt(j);
            if (hash_map.containsKey(ch)) {
                i = Math.max(hash_map.get(ch), i);
            }
            result = Math.max(result, j - i + 1);
            hash_map.put(ch, j + 1);
            j++;
        }
        return result;
    }

    public static void main(String[] args)
    {

        final Map<String, Integer> testData = Map.ofEntries(
                Map.entry("abcde", 5),
                Map.entry("abcbef", 4),
                Map.entry("aaaaaa", 1),
                Map.entry("aaabbbccc", 2),
                Map.entry("abcabcbb", 3)
        );

        for (Map.Entry<String, Integer> entry: testData.entrySet())
        {
            {
                int actual = length_of_longest_substring_1(entry.getKey());
                if (actual != entry.getValue()) {
                    System.err.println("Error Actual (" + actual + ") != Expected (" + entry.getValue() + ")" );
                }
            }
            {
                int actual = length_of_longest_substring_2(entry.getKey());
                if (actual != entry.getValue()) {
                    System.err.println("Error Actual (" + actual + ") != Expected (" + entry.getValue() + ")" );
                }
            }
        }
        System.out.println("All tests passed");
    }
}
