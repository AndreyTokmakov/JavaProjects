/**
 * 
 */
package Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringsTests {
	
	private final java.util.Scanner input = new java.util.Scanner(System.in); 
	
	public static void RemoteFirstSymbol() {
		final String originStr = "Jamaica";
		String result = originStr.substring(1);
		
		System.out.println("Origin string: " + originStr);
		System.out.println("Modified: " + result);
	}
	
	public static void FormatString() {
		String output = String.format("%s = %d", "joe", 35);
		System.out.println("Modified: " + output);
	}
	
	public void IsStringPalindrome() {
		String line = input.nextLine();
		final String reversed = new StringBuffer(line).reverse().toString();		
		System.out.println(reversed);
	}
	
	public void SplitString()
	{
		{
			final String str = "TEST1 TEST2 TEST3";
			List<String> str_list = Arrays.asList(str.split(" "));
			System.out.println(str_list);
		}
		{
			final String str = "persistent://OPNX-V1/PRETRADE-ME/ORDER-IN-BTC/USDT";
			List<String> str_list = Arrays.stream(str.split("/"))
					.filter(s -> !s.isBlank()).skip(3).toList();
			System.out.println(str_list);
		}
	}
	
	public void Join() {
		String result = String.join(" < ", "One", "Two", "Three", "Four", "Five");
		System.out.println(result);
	}
	
	public void RegionMatches() {
		String str1 = new String("Hello, How are you");
	    String str2 = new String("How");
	    String str3 = new String("HOW");

	    System.out.print("Result of Test1: " );
	    System.out.println(str1.regionMatches(7, str2, 0, 3));

	    System.out.print("Result of Test2: " );
	    System.out.println(str1.regionMatches(7, str3, 0, 3));

	    System.out.print("Result of Test3: " );
	    System.out.println(str1.regionMatches(true, 7, str3, 0, 3));
	}
	
	
	
	public void Compare_String() {
		String str1 = new String("TEST_TEST");
		String str2 = new String("TEST_TEST");
		String str3 = str2;
		
		System.out.println(str1.equals(str2));
		System.out.println(0 == str1.compareTo(str2));
		System.out.println(str1 == str2);
		System.out.println();
		System.out.println(str3 == str2);
		
		
		String str4 = new String("test123");
		String str5 = new String("TEST123");
		System.out.println(str4.equalsIgnoreCase(str5));
		
	}
	
	public void Replace() {
		String str1 = new String("aaqwertyb1234");
		
		str1 = str1.replace("a", "").replace("b", "bb");
		
		System.out.println(str1);
	}
	
	
	public void SwitchTests() {
		final String type = "Type1";
		switch (type) {
			case "Type1":
				System.out.println("1");
				break;
			case "Type2":
				System.out.println("2");
				break;
			default:
				System.out.println("Error");
				break;
		}
	}
	
	public void Various_Tests() {
		String str = "0123456789";
		for (int i = 0; i < str.length(); ++i)
			System.out.print(str.charAt(i));
	}
	
	
	public void Repeat() {
		 String str = "1".repeat(51);
		 System.out.println(str); 
	}

	public void Trim() {
		String str = "  11  111   ";
		System.out.println("[" + str.trim() + "]");
	}

	public void TrimRemoveSpaces() {
		String str = "  11  111   ";
		System.out.println("[" + str.replace(" ", "") + "]");
	}
	
	public void isBlank()
	{
		for (final String str: List.of("11", "", "  "))
		{
			System.out.println("'" + str + "': blank: " + str.isBlank() + ", empty: " + str.isEmpty());
		}
	}
	
	 public void Lines() 
	 {
		 final String testString = "hello\nworld\nis\nexecuted";
		 List<String> lines = new ArrayList<>();
		 testString.lines().forEach(line -> lines.add(line));
		 System.out.println(lines);
	 }

	 public static void CountChars() {
		 String str = "1:2:3:4:5";
		 System.out.println(str.split(":").length - 1);
		 System.out.println(str.chars().filter(ch -> ch == ':').count());
	 }

	public static void main(String[] args)
	{
		StringsTests tests = new StringsTests();

		// CountChars();
		
		// tests.SplitString();

		// tests.Join();

		// tests.RegionMatches();
		
		// tests.Compare_String();
		
		// tests.Replace();
		
		// tests.SwitchTests();
		
		// tests.Various_Tests();
		
		// tests.Repeat();
		
		// tests.isBlank();
		
		// tests.Lines();

		// tests.Trim();
		// tests.TrimRemoveSpaces();

		String str1 = new String("12345");
		String str2 = "12345";

		System.out.println(str1 == str2);
	}
}
