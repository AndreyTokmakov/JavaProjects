package CollectionsTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Experiments
{
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(3);
        numbers.add(1);
        numbers.add(2);

        System.out.println(numbers);

        Collections.sort(numbers, (item1, item2)-> {
            return item1 - item2;
        });

        System.out.println(numbers);
    }
}
