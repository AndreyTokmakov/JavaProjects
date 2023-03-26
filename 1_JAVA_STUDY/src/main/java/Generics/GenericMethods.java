package Generics;

import java.util.*;

public final class GenericMethods
{
    protected static <T> T getValue(T value) {
        System.out.println(value.getClass().getSimpleName());
        return value;
    }

    public static void main(String ... params) {
        getValue(1);
        getValue(1d);
        getValue(1f);
        getValue("String");
        getValue(List.of());
        getValue(Collections.emptyList());
        getValue(Set.of());
        getValue(new HashMap<String, String>());
    }
}
