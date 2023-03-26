package Map;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


class Color {
	private String name = "None";
	
	public Color(String colorName) {
		this.name = colorName;
		System.out.println("Color(" + name + ") created.");
	}

    @Override
    public String toString() { 
        return String.format("Color(%s)", this.name);
    }

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) 
			return true;
		else if (null == obj || obj.getClass() != this.getClass())
			return false;
		else {
			return this.name.equals(((Color)obj).name);
		}
	} 
	
	@Override
	public int hashCode() {
	   int result = name.hashCode();  
	   return result;
	}
}

class ValueComparator implements Comparator<String> {
    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}

public class HashMap_Tests {
	
	protected static void VariousTests()
	{
		Map<String, List<String>> suitesCounter = new HashMap<String, List<String>>();

		suitesCounter.put("key1", new ArrayList<String>(Arrays.asList("Text_1")));
		suitesCounter.put("key2", new ArrayList<String>(Arrays.asList("Text_2")));
		suitesCounter.put("key3", new ArrayList<String>(Arrays.asList("Text_3")));

		for (final var entry: suitesCounter.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		var x = suitesCounter.putIfAbsent("key1", new ArrayList<String>(Arrays.asList("Text_133")));
		x.add("232323");

		/*
		String result = suitesCounter.put("key1", "Text3333");
		System.out.println(result);
		*
		 */

		for (final var entry: suitesCounter.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}

	protected static void IncrementCounters()
	{
		Map<String, Integer> suitesCounter = new HashMap<String, Integer>();

		Integer v = suitesCounter.putIfAbsent("Name", 1);
		System.out.println(v);

		System.out.println(suitesCounter.get("Name"));
	}

	protected static void MapSortTests() {
		
		Random rand = new Random();
		Map<String, Integer> suitesCounter = new HashMap<String, Integer>();
		
		for (int i = 0; i < 20; i++) {
			int randomInt = rand.nextInt(100);
			String key = "Key_" + String.valueOf(i);
			suitesCounter.put(key, randomInt);
		}
		
		System.out.println("Before sort : ");
		for (Map.Entry<String, Integer>  entry: suitesCounter.entrySet()) { 
			String key = entry.getKey(); 
			Integer value = entry.getValue(); 
			System.out.println("   (" + key + ", " + value + ")");
		} 
		
		System.out.println("\n\nAfter sort : ");
		suitesCounter.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
	}
	
	protected static void MapSortTests_Top10() {
		Random rand = new Random();
		Map<String, Integer> suitesCounter = new HashMap<String, Integer>();
		
		for (int i = 0; i < 20; i++) {
			int randomInt = rand.nextInt(100);
			String key = "Key_" + String.valueOf(i);
			suitesCounter.put(key, randomInt);
		}
		
		System.out.println("Before sort : ");
		for (Map.Entry<String, Integer>  entry: suitesCounter.entrySet()) { 
			String key = entry.getKey(); 
			Integer value = entry.getValue(); 
			System.out.println("   (" + key + ", " + value + ")");
		} 
		
		System.out.println("\n\nAfter sort (Top 10): ");
		suitesCounter.entrySet()
					 .stream()
					 .sorted(Map.Entry.comparingByValue())
					 .limit(10)
					 .forEach(System.out::println);
	}
	
	public static void Get() {
		final Map<String, String> tokens = Map.ofEntries(
				Map.entry("1", "One"),
				Map.entry("2", "Two"),
				Map.entry("3", "Three")
			);
		
		String result = tokens.get("11");
		System.out.println(result);

	}
	
	
	public static void GetOrDefault() {
		Map<String, Color> palette = new HashMap<String, Color>();
		palette.put("Red", new Color("Red"));
		
		System.out.println("---------------- Tests: -----------------");
		
		System.out.println(palette.getOrDefault("Red", new Color("Green"))); // BAD BAD BAD!!!!! new Color("Green") is created
		System.out.println(palette.getOrDefault("Blue", new Color("Blue")));
	}
	
	public static Color getDefault() {
		return new Color("DEFAULT");
	}
	
	public static void GetOrDefault_Lambda() {
		HashMap<String, Color> palette = new HashMap<String, Color>();
		palette.put("Red", new Color("Red"));
		
		System.out.println("---------------- Tests: -----------------");
		
		// Color color = palette.

	}
	
	
	
	public static void Put() {
		Map<String, Color> palette = new HashMap<String, Color>();
		
		Color col1 = palette.put("Red", new Color("RedColorNumber1"));
		System.out.println(col1);
		System.out.println(palette.get("Red") + "\n");

		Color col2 = palette.put("Red", new Color("RedColorNumber12222"));
		System.out.println(col2);
		System.out.println(palette.get("Red") + "\n");
		
		Color col3 = palette.put("Red", new Color("RedColorNumber434234242343"));
		System.out.println(col3);
		System.out.println(palette.get("Red") + "\n");
	}
	
	public static void Create() {
		{
			Map<Integer, String> dict = new HashMap<Integer, String>();
			dict.put(1, "One");
			dict.put(2, "Two");
			dict.put(3, "Three");
			dict.put(4, "Four");
			
			System.out.println(dict);
		}
		
		{
			Map<Integer, String> dict = Map.of(1, "One", 2, "Two",3, "Three",4, "Four");
			System.out.println(dict);
		}
	}
	
	public static void Map_for_Dictionary() {
		final Map<String, String> tokens = Map.ofEntries(
			Map.entry("1", "One"),
			Map.entry("2", "Two"),
			Map.entry("3", "Three"),
			Map.entry("4", "Four"),
			Map.entry("5", "Five"),
			Map.entry("6", "Six"),
			Map.entry("7", "Seven"),
			Map.entry("8", "Eigth"),
			Map.entry("9", "Nine"),
			Map.entry("0", "Zero")
		);
	}

	protected static void Iterate_Tests()	{
		Map<String, Integer> dict = new HashMap<String, Integer>();

		dict.put("key1", 1);
		dict.put("key2", 5);
		dict.put("key3", 3);
		
		dict.forEach((k,v) -> {System.out.println(k + " = " + v);});
		
		for (Map.Entry<String, Integer> entry: dict.entrySet()) {
			// TODO
		}
	}

	protected static void ComputeIsAbsent()	{
		Map<Integer, ArrayList<String>> dict = new HashMap<Integer, ArrayList<String>>();

		ArrayList<String> list =
				dict.computeIfAbsent(123, s -> new ArrayList<>(Arrays.asList("One")) );
		if (null != list)
			list.add("One");

		for (Map.Entry<Integer, ArrayList<String>> entry: dict.entrySet()) {
			entry.getValue().stream().forEach(System.out::println);
		}

	}
	
	
	public static void main(String[] args) {
		
		Create();
		
		// Iterate_Tests();

		// Map_for_Dictionary();

		// ComputeIsAbsent();
		
		// MapSortTests();
		
		// MapSortTests_Top10();
		
		// VariousTests();

		// IncrementCounters();
		
		// Get();
		// GetOrDefault();
		
		// GetOrDefault_Lambda();
		 
		// Put();
	}
}










