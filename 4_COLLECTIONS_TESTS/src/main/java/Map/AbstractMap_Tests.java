package Map;

import java.util.Map;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;

public class AbstractMap_Tests
{
    public static void main(String[] args)
    {
        Map.Entry<String, String> entry1 = new AbstractMap.SimpleImmutableEntry<String, String>("One", "Two");
        Map.Entry<String,Integer> entry2 = new AbstractMap.SimpleEntry<String, Integer>("Example", 42);

        System.out.println(entry1);
        System.out.println(entry2);
    }
}
