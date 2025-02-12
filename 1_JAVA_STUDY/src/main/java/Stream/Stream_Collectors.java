package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream_Collectors
{
    public static void StreamToList()
    {
        List<String> list = new ArrayList<String>(Arrays.asList("Value1", "Value1", "Value1", "Value2", "Value2", "Value2"));
        List<String> newList = list.stream().collect(Collectors.toList());
        // List<String> newList = list.stream().toList();

        System.err.println(newList.getClass());
        newList.forEach(System.out::println);
    }

    public static void main(String[] args)
    {
        StreamToList();

    }
}
