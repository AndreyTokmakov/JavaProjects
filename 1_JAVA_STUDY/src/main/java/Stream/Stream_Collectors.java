package Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.function.Function;
import java.util.*;
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
        private Boolean enabled;
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
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318", false));
        bookList.add(new Book("The Two Towers", 1954, "0345339711", true));
        bookList.add(new Book("The Return of the King", 1955, "0618129111", true));

        List<String> bookNames = bookList.stream().map(Book::getName).collect(Collectors.toList());
        System.out.println(bookNames);
    }

    public static void Stream2Map()
    {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318", true));
        bookList.add(new Book("The Two Towers", 1954, "0345339711", false));
        bookList.add(new Book("The Return of the King", 1955, "0618129111", true));

        Map<String, String> listToMap = bookList.stream().filter(Book::getEnabled).collect(Collectors.toMap(
                Book::getIsbn, Book::getName));

        System.out.println(listToMap.getClass().getName());
        listToMap.forEach((k, v) -> System.out.println(k + " = " + v));
    }


    public static void Map_Stream_to_Map()
    {
        Map<String, Book> books = new HashMap<String, Book>();
        books.put("One",new Book("The Fellowship of the Ring", 1954, "0395489318", true));
        books.put("Two",new Book("The Fellowship of the Ring", 1954, "0395489318", true));
        books.put("Three",new Book("The Fellowship of the Ring", 1954, "0395489318", true));

        List<String> names = List.of("One", "Three");

        var result = names.stream().collect(
                Collectors.toMap(name -> name, name -> {
                    var b = books.get(name);
                    if (null == b) {

                    }
                    return new Book(name, 1954, "0395489318", true);}
                )
        );

        System.out.println(result);
    }

    public static void Map_handle_Missing_values()
    {
        List<String> strValues = List.of("One", "Two", "Three");
        Map<String, Integer> strToIntMap = Map.of("One", 1, "Two",2, "Five",5);

        var result = strValues.stream().map(strToIntMap::get).map(String::valueOf).toList();
        System.out.println(result);

    }

    public static void To_MAP_Handle_Duplicate_Keys()
    {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "101", true));
        bookList.add(new Book("The Two Towers", 1954, "102", false));
        bookList.add(new Book("The Return of the King", 1955, "101", true));

        try {
            Map<String, Book> listToMap = bookList.stream().filter(Book::getEnabled).collect(Collectors.toMap(
                    Book::getIsbn, o -> o));

            System.out.println(listToMap.getClass().getName());
            listToMap.forEach((k, v) -> System.out.println(k + " = " + v));
        }
        catch (Exception exc) {
            System.err.println(exc.fillInStackTrace());
        }
    }

    public static void To_MAP_Handle_Duplicate_Keys_Merge()
    {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "101", true));
        bookList.add(new Book("The Two Towers", 1954, "102", true));
        bookList.add(new Book("The Return of the King", 1955, "101", true));


        try {
            Map<String, Book> listToMap = bookList.stream().filter(Book::getEnabled).collect(Collectors.toMap(
                    Book::getIsbn,  Function.identity(),
                    (existing, replacement) -> existing));

            System.out.println(listToMap.getClass().getName());
            listToMap.forEach((k, v) -> System.out.println(k + " = " + v));
        }
        catch (Exception exc) {
            System.err.println(exc.fillInStackTrace());
        }
    }

    public static void main(String[] args)
    {
        // StreamToList();
        // StreamToList_2();

        // Stream2Map();
        // Map_Stream_to_Map();
        // Map_handle_Missing_values();

        // To_MAP_Handle_Duplicate_Keys();
        To_MAP_Handle_Duplicate_Keys_Merge();
    }
}
