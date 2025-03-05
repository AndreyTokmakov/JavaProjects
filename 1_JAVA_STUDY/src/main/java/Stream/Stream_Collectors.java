package Stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream_Collectors
{
    @Data
    @AllArgsConstructor
    private final static class Book
    {
        private String name;
        private int releaseYear;
        private String isbn;
    }

    public static void StreamToList()
    {
        List<String> list = new ArrayList<String>(Arrays.asList("Value1", "Value1", "Value1", "Value2", "Value2", "Value2"));
        List<String> newList = list.stream().collect(Collectors.toList());
        // List<String> newList = list.stream().toList();

        System.err.println(newList.getClass());
        newList.forEach(System.out::println);
    }

    public static void StreamToList_2()
    {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));

        List<String> bookNames = bookList.stream().map(Book::getName).collect(Collectors.toList());
        System.out.println(bookNames);
    }

    public static void Stream2Map()
    {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));

        Map<String, String> listToMap = bookList.stream().collect(Collectors.toMap(
                Book::getIsbn, Book::getName));

        System.out.println(listToMap.getClass().getName());
        listToMap.forEach((k, v) -> System.out.println(k + " = " + v));
    }

    public static void main(String[] args)
    {
        // StreamToList();
        StreamToList_2();

        // Stream2Map();
    }
}
