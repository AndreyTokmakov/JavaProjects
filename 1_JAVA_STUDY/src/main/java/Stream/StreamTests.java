package Stream;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class Phone {
    private String name;
    private int price;
     
    public Phone(String name, int price){
        this.name=name;
        this.price=price;
    }
     
    public String getName() {
        return name;
    }
     
    public int getPrice() {
        return price;
    }
}


public class StreamTests {
	
	private final static Consumer<Integer> print_in_line = value -> System.out.printf(value + " ");
	
	private final static Consumer<String> print_strings = value -> System.out.println(value);
	
	
	public static void Create_Stream() {
		{   // Empty stream
			Stream<String> stream = Stream.empty();
			// stream.forEach(s -> System.out.print(s + ' '));
			System.out.println("Empty");
		}

		{   // From collection
			Collection<String> collection = Arrays.asList("a1", "a2", "a3");
			Stream<String> stream = collection.stream();

			stream.forEach(s -> System.out.print(s + ' '));
			System.out.println();
		}

		{	// From collection (MAP)
			Map<String, Integer> map = Map.ofEntries(
					Map.entry("One", 1),
					Map.entry("Two", 2),
					Map.entry("Three", 3)
			);

			Stream<String> keyStream = map.keySet().stream();
			Stream<Integer> valStream = map.values().stream();
			Stream<Map.Entry<String, Integer>> entryStream = map.entrySet().stream();


			keyStream.forEach(s -> System.out.print(s + ' '));
			System.out.println();
			valStream.forEach(s -> System.out.print(s + " "));
			System.out.println();
			entryStream.forEach(e -> System.out.print(e.getKey() + " = " + e.getValue() + ", "));
			System.out.println();
		}

		{   // Stream.of
			Stream<String> stream = Stream.of("b1", "b2", "b3");
	
			stream.forEach(s -> System.out.print(s + ' '));
			System.out.println();
		}
		
		{	// Arrays.stream
			String[] array = {"c1", "c2", "c3"};
			Stream<String> stream = Arrays.stream(array);
			
			stream.forEach(s -> System.out.print(s + ' '));
			System.out.println();
		}
		
		{   // Builder
			Stream<String> stream = Stream.<String>builder().add("d1").add("d2").add("d3").build();
			
			stream.forEach(s -> System.out.print(s + ' '));
			System.out.println();
		}
	}
	
	public static void Create_Infinite() {
		UnaryOperator<Integer> x10 = n  ->  n * 5;
		
		Stream<Integer> stream1 = Stream.iterate(1, n -> n + 1).limit(10);
		
		stream1.forEach(s -> System.out.print(s + " "));
		System.out.println();
		
		Stream<Integer> stream2 = Stream.iterate(1, n  ->  n * 2).limit(10);
		 
		stream2.forEach(s -> System.out.print(s + " "));
		System.out.println();
		 
        Stream<Integer> stream3 = Stream.iterate(1, x10).limit(10);

        stream3.forEach(s -> System.out.print(s + " "));
		System.out.println();    
	}

	public static void Create_Range() {
		IntStream intStream = IntStream.range(20, 30);

		intStream.forEach(System.out::println);
	}
	
	public static void Create_IntStream() {
		final IntStream stream = "123".chars();
		
		stream.forEach(s -> System.out.print(s + ' '));
		System.out.println();
	}
	
	public static void ReadFile_ToStream() throws IOException {
		Stream<String> stream = Files.lines(Paths.get("resources//file.txt"));
		stream.forEach(System.out::println);
	}

	public static void Match_All_Any_None() {
		final Predicate<Integer> p = num -> num % 2 == 0;
		final List<Integer> list = Arrays.asList(3,5,6);
		
		System.out.println(list);
		System.out.println("allMatch : " + list.stream().allMatch(p));
		System.out.println("anyMatch : " + list.stream().anyMatch(p));
		System.out.println("noneMatch: " + list.stream().noneMatch(p));
	}
	
	public static void firstMatch() {
		List<String> list = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3"));
		
		for (final String key : Arrays.asList("Value1", "Value5")) {
			Optional<String> result = list.stream().filter(val -> 0 == val.compareTo(key)).findFirst();
			if (true == result.isPresent())
				System.out.println(result.get());
			else {
				System.err.println(String.format("Failed to find elements for key '%s'", key));
			}
		}
	}


	public static void Collect() {
		final List<Integer> list = Arrays.asList(3, 5, 6);
		int sum = list.stream().collect(Collectors.summingInt(i->i));
		System.out.println("Sum: "+ sum);
	}
	
	public static void Collect_ToList() {
		List<Integer> intLst = Arrays.asList(1, 2, 3, 4, 2, 5, 6, 4, 8, 2, 7, 8);
		List<Integer> evenLst = intLst.stream().filter(i -> i%2 != 0).collect(Collectors.toList());
		System.out.println(evenLst);
	}
	
	public static void Collect_ToSet() {
		List<Integer> intLst = Arrays.asList(1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4);
		Set<Integer> intSet = intLst.stream().filter(i -> i%2 == 0).collect(Collectors.toSet());
		System.out.println(intSet);
	}
	


	public static void Concat() {
		final List<Integer> list1 = Arrays.asList(1, 3, 5);
		final List<Integer> list2 = Arrays.asList(2, 4, 6);

		Stream<Integer> resStream = Stream.concat(list1.stream(), list2.stream());
		final Consumer<Integer> printer = value -> System.out.printf(value + " ");
		resStream.forEach(printer);
	}
	
	public static void Count() {
		
		System.out.println("Using count() function: \n");
		
		long count = Stream.of("how","to","do","in","java").count();
	    System.out.printf("There are %d elements in the stream %n", count);
	     
	    count = IntStream.of(1,2,3,4,5,6,7,8,9).count();
	    System.out.printf("There are %d elements in the stream %n", count);
	     
	    count = LongStream.of(1,2,3,4,5,6,7,8,9).filter(i -> i%2 == 0).count();
	    System.out.printf("There are %d elements in the stream %n", count);
	    
	    System.out.println("\nAnd now using counting() function: \n");
	    
	    count = Stream.of("how","to","do","in","java").collect(Collectors.counting());
	    System.out.printf("There are %d elements in the stream %n", count);
	     
	    count = Stream.of(1,2,3,4,5,6,7,8,9).collect(Collectors.counting());
	    System.out.printf("There are %d elements in the stream %n", count);
	     
	    count = Stream.of(1,2,3,4,5,6,7,8,9).filter(i -> i%2 == 0).collect(Collectors.counting());
	    System.out.printf("There are %d elements in the stream %n", count);
	}	
	
	public static void StreamCount_Predicate() {
		final Predicate<Integer> p = num -> num % 2 == 0;
		List<Integer> list = Arrays.asList(3, 4, 6, 5);
		System.out.println("Count: " + list.stream().filter(p).count());
	}
	
	public static void Distinct() {
		List<Integer> list = Arrays.asList(3, 4, 6, 6, 4);
		System.out.println("Number of different elements in sequence: " + list.stream().distinct().count());
	}
	
	public static void Distinct1() {
		final List<String> ordered = Arrays.asList("a1", "a2", "a2", "a3", "a1", "a2", "a2");
		
		final List<String> l1 = ordered
				.stream()
				.distinct()
				.collect(Collectors.toList());
		System.out.println(l1);
	}
	
	public static void Filter_1() {
		Predicate<Integer> pred = num -> num % 2 == 0;
		List<Integer> list = Arrays.asList(3,4,6);
		list.stream().filter(pred).forEach(e -> System.out.print(e + " "));
	}
	
	public static void Filter_2() {
		final Predicate<Integer> isOdd = (num) -> num % 2 == 0;
		final Consumer<Integer> printer = value-> System.out.printf(value + " ");
		
		final List<Integer> list = Arrays.asList(3, 4, 6);
		list.stream().filter(isOdd).forEach(printer);
	}
	
	public static void Filter_Equals() {
		List<String> list = new ArrayList<String>(Arrays.asList("Value1", "Value1", "Value133", "Value2", "Value133", "Value2"));
		long count = list.stream().filter("Value133"::equals).count();
		System.out.println(count);
	}
	

	public static void Stream_Filter_ToList() {
		List<String> list = new ArrayList<String>(Arrays.asList("Value1", "Value1", "Value1", "Value2", "Value2", "Value2"));
		List<String> newList = list.stream().filter(entry -> entry.contains("e1")).toList();
		newList.forEach(print_strings);
	}
	
	public static void Filter_ForEach_Print() {
		Stream.of("dd2", "aa2", "bb1", "bb3", "cc4").filter(s -> {
			System.out.println("Filter: " + s);
			return true;
		}).forEach(s -> System.out.println("Print with forEach: " + s));
	}
	
	public static void FindAny() {
		List<String> list = Arrays.asList("G","B","F","E");
		String any = list.stream().findAny().get();
		System.out.println("FindAny : "+ any);
	}
	
	public static void FindFirst_OrElse() {
		List<String> list = Arrays.asList("TT","##","AA","E");
		String first = list.stream().findFirst().orElse("Opps");
		System.out.println("First : "+ first);
		
		first = Collections.<String>emptyList().stream().findFirst().orElse("Opps");
		System.out.println("First : "+ first);
	}

	
	public static void FindFirst() {
		List<String> list = Arrays.asList("TT","##","AA","E");
		String first = list.stream().findFirst().get();
		System.out.println("FindFirst : "+ first);
	}
	
	public static void forEachOrdered() {
		final Integer[] data = {1, 5, 6, 4, 2, 3 ,7};
		System.out.println("ForEach demo:");
		Arrays.stream(data).filter(num -> num%2 == 1).forEach(s -> System.out.print(s+" "));
		System.out.println("\nForEachOrdered demo:");	
		Arrays.stream(data).filter(num -> num%2 == 1).forEachOrdered(s -> System.out.print(s+" "));
	}
	
	public static void Generate() {
		final String str = "Hello World!";
        Stream<String> stream = Stream.generate(str::toString).limit(5);
        stream.forEach(s->System.out.println(s));


        final Stream<String> intStream = Stream.generate(() -> "A").limit(10);
        intStream.forEach(value -> System.out.print(value + ' '));
        
        System.out.println();
        
        Stream<Integer> intSteam2 = Stream.iterate(2, i -> i + 3).limit(10);
        intSteam2.forEach(value -> System.out.print(value + "  "));
        
        System.out.println();
        
        Stream<Integer> randomNumbers = Stream.generate(() -> (ThreadLocalRandom.current().nextInt(0, 10)));
       	randomNumbers.limit(20).forEach(value -> System.out.print(value + "  "));
	}
	
	public static void Iterate() {
		 UnaryOperator<Integer> x10 = n  ->  n * 5;
		
		 Stream<Integer> stream1 = Stream.iterate(1, n  ->  n * 2).limit(5);
         Stream<Integer> stream2 = Stream.iterate(1, x10).limit(5);
         
         stream1.forEach(print_in_line);
         System.out.println();
         stream2.forEach(print_in_line);
         
         /* Output:
			1 2 4 8 16 
			1 5 25 125 625 
         */
	}
	
	// map: There is an opportunity to create a function with which we will change each element and skip it further
	public static void Map() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		list.stream().map(i -> i * i).forEach(s -> System.out.print(s + " "));
	}
	
	public static void Map_FindValuesInMap() {
		final Map<String, String> tokens = Map.ofEntries(
			Map.entry("One", "1"),
			Map.entry("Two", "2"),
			Map.entry("Three", "3")
		);

		final List<String> list = Arrays.asList("One","Two","Three");
		list.stream().map(tokens::get).forEach(System.out::println);
	}
	
	
	public static void FlatMap() {
		final Predicate<Integer> isPositive = x -> (x % 2) == 0;
		final Predicate<Integer> isNegative = x -> (x % 2) == 1;
		final Consumer<Integer> printer = value -> System.out.printf(value + " ");
		
		Integer[][] data = {{1,2},{3,4},{5,6}};
		
		System.out.println("All values:");
		Arrays.stream(data).flatMap(row -> Arrays.stream(row)).forEach(printer);
		System.out.println("\nPositive values are filered:");
		Arrays.stream(data).flatMap(row -> Arrays.stream(row)).filter(isPositive).forEach(printer);
		System.out.println("\nNegative values are filered:");
		Arrays.stream(data).flatMap(row -> Arrays.stream(row)).filter(isNegative).forEach(printer);
	}
	
	public static void FlatMap2() 
    {
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6);
        List<Integer> list3 = Arrays.asList(7,8,9);
          
        List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);
         
        List<Integer> listOfAllIntegers = listOfLists.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
        System.out.println(listOfAllIntegers);
    }
	
	public static void FlatMap3() 
    {
        String[][] dataArray = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};
         
        List<String> listOfAllChars = Arrays.stream(dataArray).flatMap(x -> Arrays.stream(x)).collect(Collectors.toList());
        System.out.println(listOfAllChars);
    }
	
	public static void Min() {
		final List<String> list = Arrays.asList("G","B","F","E");
		String min = list.stream().min(Comparator.comparing(String::valueOf)).get();
		System.out.println("Min:"+ min);		
		
		final List<Integer> numbers = Arrays.asList(7,4,9,4,9,13,7,1);
		Integer min2 = Collections.min(numbers);
		System.out.println("Min:"+ min2);	
	}
	
	public static void Max() {
		final List<String> list = Arrays.asList("G","B","F","E");
		String max = list.stream().max(Comparator.comparing(String::valueOf)).get();
		System.out.println("Max:"+ max);		
	}
	
	public static void Peek() {
		final List<String> list = Arrays.asList("A","B","C");
        list.stream().peek(s->System.out.println(s + s)).collect(Collectors.toList());		
        
        final List<String> list2 = Arrays.asList("a","b","c");
        list2.stream().map(String::toUpperCase).peek((e) -> System.out.print("[" + e + "] ")).collect(Collectors.toList());
	}
	
	public static void Reduce()
	{
		int[] array = {3,5,10,15};
		int sum = Arrays.stream(array).reduce(0, (x, y) -> x + y);
		System.out.println("Sum:"+ sum);
	
		Stream<String> wordsStream = Stream.of("One", "Two", "Three");
		Optional<String> sentence = wordsStream.reduce((x, y) -> x + " " + y);
		System.out.println(sentence.get());
	}

	public static void Reduce1()
	{
		Stream<Phone> phoneStream = Stream.of(
				new Phone("iPhone 6 S", 54000),
				new Phone("Lumia 950", 45000),
				new Phone("Samsung Galaxy S 6", 40000),
				new Phone("LG G 4", 32000)
		);

		Integer sum = phoneStream.map(Phone::getPrice).reduce(0, Integer::sum);
		System.out.println(sum); // 171000
	}

	public static void Reduce2()
	{
		Stream<Phone> phoneStream = Stream.of(
				new Phone("iPhone 6 S", 54000),
				new Phone("Lumia 950", 45000),
				new Phone("Samsung Galaxy S 6", 40000),
				new Phone("LG G 4", 32000)
		);
	 
		int sum = phoneStream.reduce(0, (x,y)-> {
			if (y.getPrice() < 50000)
				return x + y.getPrice();
			else return x;}, Integer::sum);
		System.out.println(sum); // 117000
	}
	
	public static void Skip() {
		int[] array = {3, 5, 10, 15};
		Arrays.stream(array).skip(1).forEach(s -> System.out.println(s + " "));
	}
	
	public static void Sorted_List() {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(5,4,3,2,1));
		
		
		System.out.println("Sort by Map Value:");
		list.stream().sorted().forEach(e -> System.out.println(e));
	}
	
	public static void Sorted_Map() {
		final Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "BBBB");
		map.put(2, "AAAA");
		map.put(3, "CCCC");
		
		System.out.println("Sort by Map Value:");
	    map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
	          .forEach(e -> System.out.println("Key: "+ e.getKey() +", Value: "+ e.getValue()));
	}
	
	public static void 	Limit() {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		numbers.stream().limit(5).forEach(val -> System.out.println(val));
		
		List<Integer> numbers2  = Arrays.asList(1,2,3,4,5,6,7);
		numbers.stream().limit(5).forEach(val -> System.out.println(val));
	}

	public static void Range() {
        IntStream intStream = IntStream.range(20, 30);
        intStream.forEach(System.out::println);
        
        IntStream.range(8, 12).forEach(System.out::println);
	}

	static final class TestComparator<T> implements Comparator<T> {
		@Override
		public int compare(final T v1, final T v2) {
			return -1;
		}
	}

	public static <T> void test( Stream<? extends T> stream,
								Comparator<? super T> order) {

		final List<? extends T> list = stream.sorted(order).collect(Collectors.toList());
		if (list.isEmpty())
			System.out.println(list.get(0) + " " +  list.get(list.size() - 1));
		System.out.println(list.get(0) + " " +  list.get(list.size() - 1));
	}

	public static void TESTS_and_EXPERIMENTS() 
	{
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(5,4,3,2,1));
		test(list.stream(), new TestComparator<Integer>());



	}
	
	public static void main(String[] args) throws IOException
	{
		
		//Create_Stream();
		// Create_IntStream();
		// Create_Infinite();
		// Create_Range();
		// Generate();
		
		// ReadFile_ToStream();
		
		// Match_All_Any_None();
		
		// Collect();  /* Sum of the array */
		// Collect_ToList();
		// Collect_ToSet();
		
		// Concat();
		
		// Count();
		
		// Distinct();
		// Distinct1();
		
		// Filter_1();
		// Filter_2();
		// Filter_Equals();
		// Stream_Filter_ToList();
		// Filter_ForEach_Print();
		
		// FindAny();
		// FindFirst();
		// FindFirst_OrElse();

		// firstMatch();

		// forEachOrdered();
		
		// Iterate();
		// Limit();
		
		// Map();
		// Map_FindValuesInMap();

		// FlatMap();
		// FlatMap2();
		// FlatMap3();
		 
		// Min();
		// Max();
		
		// Peek();
		
		// Reduce();
		Reduce1();
		// Reduce2();
		
		// Range();
		
		// Skip();
		
		// Sorted_List();
		// Sorted_Map();
		
		// TESTS_and_EXPERIMENTS();
	}
}
