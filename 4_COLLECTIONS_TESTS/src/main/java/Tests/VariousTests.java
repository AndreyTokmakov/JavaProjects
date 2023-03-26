package Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class VariousTests
{
    public static void main(String[] args) {
        Collection<?> collection = new ArrayList<Integer>();
        Object object = Integer.valueOf(123);


        // collection.add(object);
        // collection.addAll(Arrays.asList(object));

        collection.clear();


        Iterator<?> iter = collection.iterator();
        Object[] objs = collection.toArray();
        collection.contains(object);
        collection.remove(object);
    }
}
