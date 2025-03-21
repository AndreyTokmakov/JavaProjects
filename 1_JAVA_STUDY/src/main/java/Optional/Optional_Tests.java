package Optional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Optional_Tests {
	
	public String getMyDefault() {
	    System.out.println("Getting Default Value");
	    return "Default Value";
	}
	
	public Optional<String> getOptional(int x) {
	    if (1 == x)
	    	return Optional.<String>of("Something");
	    else if(2 == x) {
	    	return Optional.empty();
	    }
	    else
	    	return null;
	}
	
	public void Handle_Return_Optional()
	{
		 Optional<String> val1 = getOptional(1);
		 try {
			 System.out.println(val1.orElse("No Value"));
		 }
		 catch (final Exception exc) {
			 System.out.println(exc);
		 }
		 
		 Optional<String> val2 = getOptional(2);
		 try {
			 System.out.println(val2.orElse("No Value"));
		 }
		 catch (final Exception exc) {
			 System.out.println(exc);
		 }
		 
		 Optional<String> val3 = getOptional(3);
		 try {
			 System.out.println(val3.orElse("No Value"));
		 }
		 catch (final Exception exc) {
			 System.out.println(exc);
		 }
	}
	
	
	public void Filter_Tests() {
		List<String> list = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3"));
		for (final String key : Arrays.asList("Value12", "Value2", "Value5")) {
			Optional<String> result = list.stream().filter(val -> 0 == val.compareTo(key)).findFirst();
			if (true == result.isPresent())
				System.out.println(result.get());
			else {
				System.err.println(String.format("Failed to find elements for key '%s'", key));
			}
		}
	}

	public void String_Filter_Nulls()
	{
		final List<Optional<String>> list = List.of(Optional.of("One"), Optional.of("Two"),
				Optional.of("Three"), Optional.empty(), Optional.of("Five")
		);

		List<String> strList = list.stream().filter(Optional::isPresent).map(Optional::get).toList();
		System.out.println(strList);
	}
	
	public void FindMin_InArray() {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.addAll(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
		final Optional<Integer> min = numbers.stream().min(Integer::compare);
		if (true == min.isPresent())
			System.out.println("Min value: = " + min.get());  // 1
	} 
	
	public void OrElse() {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.addAll(Arrays.asList(new Integer[]{ /*EMPTY*/ }));
		final Optional<Integer> min = numbers.stream().min(Integer::compare);
		System.out.println(min.orElse(-1)); // 4
	} 
	
	public void CreateTests() {
		Optional<String> optEmpty = Optional.empty();

		System.out.println("Empty  : " + optEmpty.isEmpty());
		System.out.println("Present: " + optEmpty.isPresent());

		Optional<String> optVal = Optional.of("Some_Value");
		System.out.println("optVal = " + optVal.get());
		System.out.println("optVal = " + optVal.orElse("Empty"));
	}

	public void CreateTests1() {
		Optional<String> optEmpty = Optional.ofNullable(null);

		System.out.println("Empty  : " + optEmpty.isEmpty());
		System.out.println("Present: " + optEmpty.isPresent());

		Optional<String> optVal = Optional.of("Some_Value");
		System.out.println("optVal = " + optVal.get());
		System.out.println("optVal = " + optVal.orElse("Empty"));
	}
	
	public void OrElseGet_vs_OrElse() {
		String text = null;
		System.out.println("Test1:");
		{
			String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
			System.out.println(defaultText);
			defaultText = Optional.ofNullable(text).orElse(getMyDefault());
			System.out.println(defaultText);
		}
		
		System.out.println("\n\nTest2:");
		{
			text = "TEST";
			String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
			defaultText = Optional.ofNullable(text).orElse(getMyDefault());
			System.out.println(defaultText);
		}
	}
	
	public void OrElseThrow() {
		String text = null;
		String defaultText = Optional.ofNullable(text).orElseThrow(IllegalArgumentException::new);
	}
	
	public void Filter()
	{
		{
		    Integer year = 2016;
		    Optional<Integer> optYear = Optional.of(year);
		    boolean is2016 = optYear.filter(y -> y == 2016).isPresent();
		    boolean is2017 = optYear.filter(y -> y == 2017).isPresent();
		    System.out.println("Test1: " + is2016 + "  " + is2017);
		}
		{
		    String text = "12345";
		    Optional<String> optYear = Optional.of(text);
		    boolean test1 = optYear.filter(str -> str.contains("3456")).isPresent();
		    boolean test2 = optYear.filter(str -> str.contains("45")).isPresent();
		    System.out.println("Test1: " + test1 + "  " + test2);
		}
	}

	public void BigDecimal_Optional_Convert_toString_IfPresent_And_GreaterZero()
	{
		// BigDecimal decimal = null;
		BigDecimal quantity = BigDecimal.valueOf(3);

		String result = Optional.ofNullable(quantity).
				filter(v -> v.compareTo(BigDecimal.ZERO) > 0).map(BigDecimal::toString).orElse(null);
		System.out.println(result);
	}

	public void BigDecimal_Optional_Convert_toString_IfPresent_And_GreaterZero_1()
	{
		// BigDecimal decimal = null;
		BigDecimal quantity = BigDecimal.valueOf(0);

		String result = Optional.ofNullable(quantity).
				filter(v -> v.compareTo(BigDecimal.ZERO) > 0). // Get only if Grater than 0
				map(v -> v.add(BigDecimal.valueOf(1))).        // Add 1
				map(BigDecimal::toString).                               // --> String
				orElse(null);
		System.out.println(result);
	}
	
	public void Tests()
	{
		// BigDecimal decimal = 1;
		BigDecimal decimal = BigDecimal.valueOf(1);

		String result = Optional.ofNullable(decimal).
				filter(v -> v.compareTo(BigDecimal.ZERO) > 0 ).
				map(v -> v.add(BigDecimal.valueOf(1))).
				map(BigDecimal::toString).
				orElse(null);
		System.out.println(result);
	}
	
	public void IsPresent() {
		{
			String text = "12345";
			System.out.println(Optional.of(text).isPresent());
		}
		{
			boolean b1 = Optional.ofNullable(null).isPresent();
			System.out.println(b1);
			
			boolean b2 = Optional.ofNullable("ABC").isPresent();
			System.out.println(b2);
		}
		{
			String text = "23456";
			Optional<String> optStr = Optional.ofNullable(text);
			boolean b = optStr.isPresent();
			System.out.println(b);
		}
		{
			String text = null;
			Optional<String> optStr = Optional.ofNullable(text);

			boolean b = optStr.isPresent();
			System.out.println(b);
			System.out.println("Done");
		}
	}
	
	public void IfPresent() {
		{
			String text = "12345";
			Optional.of(text).ifPresent(System.out::println);
		}
		{
			Optional.ofNullable(null).ifPresent(System.out::println);
			Optional.ofNullable("ABC").ifPresent(System.out::println);
		}
		{
			String text = "23456";
			Optional<String> optStr = Optional.ofNullable(text);
			optStr.ifPresent(System.out::println);
		}
		{
			String text = null;
			Optional<String> optStr = Optional.ofNullable(text);
			optStr.ifPresent(System.out::println);
			System.out.println("Done");
		}
	}
	
	public void IfPresentOrElse()
	{
		
        Optional<Integer> op_empty  = Optional.empty(); 
        Optional<String> op_string = Optional.of("Some_Text");
        
        System.out.println("Optional: " + op_empty); 
        try { 
        	op_empty.ifPresentOrElse((v) -> { System.out.println( "Value is present, its: "+ v); }, 
							   	 	 ( ) -> { System.out.println( "Value is empty"); }); 
        } catch (Exception e) { 
            System.err.println(e);
        } 
        
        System.out.println("\nOptional: " + op_string); 
        try { 
        	op_string.ifPresentOrElse((v) -> { System.out.println( "Value is present, its: "+ v); }, 
							   	 	 ( ) -> { System.out.println( "Value is empty"); }); 
        } catch (Exception e) { 
            System.err.println(e);
        } 
    }

	private void modifyReturnedIfPresent(Integer intVar)
	{
		Integer result = Optional.ofNullable(intVar).map(v -> v + 1).orElse(null);
		System.out.println(result);
	}

	public void ModifyReturned_Value_IfPresent()
	{
		modifyReturnedIfPresent(null);
		modifyReturnedIfPresent(100);
	}

	private static final class MyObject {
		private String name = "";
		
		MyObject(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "MyObject(" + name  + ")";
		}
	}
	
	public void Map() {
		{
			final List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
			final Optional<List<String>> listOptional = Optional.of(companyNames);
	
			// Calling method List.size() for object controlled by companyNames
			int size = listOptional.map(List::size).orElse(0);
			System.out.println(companyNames + ". Size = " + size);
		}
		{
			final String name = "baeldung";
			final Optional<String> nameOptional = Optional.of(name);

			// Calling method String.length() for object controlled by nameOptional
		    int length = nameOptional.map(String::length).orElse(0);
		    System.out.println(name + ". Length = " + length);
		}
	}
	
	
	public void Map1() {
		final Optional<MyObject> nameOptional = Optional.of(new MyObject("12345"));
		
		System.out.println(nameOptional.get());
		System.out.println("Name: " + nameOptional.map(MyObject::getName).orElse("Unnamed"));
	}
	
	/************* main ************ **/
	public static void main(String[] args)
	{
		Optional_Tests tests = new Optional_Tests();
		
		// tests.FindMin_InArray();
		
		// tests.OrElse();
		// tests.CreateTests();
		// tests.CreateTests1();
		// tests.OrElseGet_vs_OrElse();
		// tests.OrElseThrow();
		
		// tests.Filter();
		// tests.Filter_Tests();
		
		// tests.Map();
		// tests.Map1();
		
		// tests.IsPresent();

		tests.String_Filter_Nulls();
		
		// tests.IfPresent();
		// tests.IfPresentOrElse();
		// tests.ModifyReturned_Value_IfPresent();

		// tests.Handle_Return_Optional();
		// tests.BigDecimal_Optional_Convert_toString_IfPresent_And_GreaterZero();
		// tests.BigDecimal_Optional_Convert_toString_IfPresent_And_GreaterZero_1();

		//tests.Tests();

	}
}
