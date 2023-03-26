package Strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Worker {

	protected void FirstRepeatedCharacter(final String text) {
		int tmp[] = new int[256];
		for (char c : text.toCharArray()) {
			if (1 == tmp[Integer.valueOf(c)]) {
				System.out.println(c);
				return;
			}
			else
				tmp[Integer.valueOf(c)] = 1;
		}
	}
	
	protected void FirstRepeatedCharacter2(final String text) {
		Set<Character> set = new HashSet<Character>();
		for (char c : text.toCharArray()) {
			if (false == set.add(c)) {
				System.out.println(c);
				return;
			}
		}
	}
	
	protected void GetCharsOccurancesCount(final String text) {
		int chars[] = new int[256];
		for (char c : text.toCharArray()) {
			chars[Integer.valueOf(c)] ++;
		}
		for (int i = 0; i < chars.length; ++i) {
			if (chars[i] > 0) {
				System.out.println((char)(i) + " = " + chars[i]);
			}
		}
	}
	
	
	protected void GetFirstCharWithMaxOccurance(final String text) {
		int chars[] = new int[256];
		for (char c : text.toCharArray()) 
			chars[Integer.valueOf(c)] ++;
		
		int max = 0;
		char result = '\0';
		for (char c : text.toCharArray()) {
			if (chars[Integer.valueOf(c)] > 0) {
				int m = Math.max(max, chars[Integer.valueOf(c)]);
				if (m > max)
				{
					result = c;
					max = m;
				}
			}
		}
		System.out.println("First char is '" + result + "' with maximum " + max + " occurencies.");
	}
	
	protected boolean Is_Strings_Are_Anagrams(final String str1, 
			                                  final String str2) {
		if (str1.length() != str2.length())
			return false;
			
		int[] chars = new int[256];
		for (char c : str1.toLowerCase().toCharArray()) 
			chars[(int) c]++;
		for (char c : str2.toLowerCase().toCharArray()) { 
			final int x = chars[(int) c]--;
			if (0 == x)
				return false;
		}
		return true;
	}
	
	protected void Swap_Array(char[] array) {
		char tmp = 'X';
		for (int i = 0; i < array.length/2; i++) {
			tmp = array[i];
			array[i] = array[array.length - 1 - i]; 
			array[array.length - 1 - i] = tmp;
		}
	}
}

class Algoritms 
{
	private final static Worker worker = new Worker();
	
	protected void Test1() {
		worker.FirstRepeatedCharacter("qwertAyuiopBasdfghjkAlzxcBvbnm");
		worker.FirstRepeatedCharacter2("qwertAyuiopBasdfghjkAlzxcBvbnm");
		worker.FirstRepeatedCharacter("qwertyuiopBasdfghjkAlzxcBvbnm");
		worker.FirstRepeatedCharacter2("qwertyuiopBasdfghjkAlzxcBvbnm");
	}
	
	protected void Swap_Test() {
		char data[] = {'0','1','2','3','4','5','6','7','8','9'};
		System.out.println(data);
		worker.Swap_Array(data);
		System.out.println(data);
	}
	
	protected void CharsOccurancesCount() {
		final String str = "ABCABCABCDDEEE";
		worker.GetCharsOccurancesCount(str);
	}
	
	protected void FirstCharWithMaxOccurance() {
		final String str = "ABCABCABCDDEEEE";
		worker.GetFirstCharWithMaxOccurance(str);
	}
	
	protected void IsAnagrams() {
		{
			final String str1 = "Hello", str2 = "Hello";
			System.out.println(String.format("String '%s' and '%s' are Anagrams = %b", 
				          	   str1, str2, worker.Is_Strings_Are_Anagrams(str1, str2)));
		}
		{
			final String str1 = "Test", str2 = "estt";
			System.out.println(String.format("String '%s' and '%s' are Anagrams = %b", 
				          	   str1, str2, worker.Is_Strings_Are_Anagrams(str1, str2)));
		}
	}

	//-----------------------------------------------------------------------------

	public static boolean isPalindrome(String text) {
		int lastIndex = text.length() - 1;
		text = text.toLowerCase();
		for (int i = 0; i < text.length()/2; ++i) {
			if (text.charAt(i) != text.charAt(lastIndex - i))
				return false;
		}
		return true;
	}

	public static void isPalindromeTest() {
		{
			String s = "123321";
			System.out.println(isPalindrome(s));
		}
		{
			String s = "12321";
			System.out.println(isPalindrome(s));
		}
		{
			String s = "12345";
			System.out.println(isPalindrome(s));
		}
		{
			String s = "Потоп";
			System.out.println(isPalindrome(s));
		}
	}

	//-----------------------------------------------------------------------------

	public static boolean isPalindrome_SkipASCII(String text) {
		char[] letters = text.toLowerCase().toCharArray();
		int size = 0;
		/*
		for (char c: letters) {
			if ((c >= '0' && '9' >= c) || (c >= 'a' && 'z' >= c) || (c >= 'A' && 'Z' >= c))
				letters[size++] = c;
		}

		for (int i = 0; i < size / 2; ++i) {
			if (letters[i] != letters[size - 1 - i])
				return false;
		}
		*/
		return true;
	}

	public static boolean isPalindrome_SkipASCII_2(String text) {
		String result = text.replaceAll("[^a-zA-Z0-9]", "");
		return result.equalsIgnoreCase(new StringBuilder(result).reverse().toString());
	}

	public static boolean isPalindrome_SkipASCII_3 (String text) {
		text = text.toLowerCase().replaceAll("[^a-z0-9]", "");
		int len = text.length();
		for (int i=0; i<(len/2); i++){
			if (text.charAt(i) != text.charAt(len - 1 - i))
				return false;
		}
		return true;
	}

	public static boolean isPalindrome_SkipASCII_4(String text) {
		int left = 0;
		int right = text.length() - 1;
		char leftChar, rightChar;
		while (left < right) {
			leftChar = text.charAt(left);
			if (isNotLetterOrNumber(leftChar)) {
				++left;
				continue;
			}

			rightChar = text.charAt(right);
			if (isNotLetterOrNumber(rightChar)) {
				--right;
				continue;
			}

			int charDifference = Math.abs(leftChar - rightChar);
			if (charDifference != 0 && charDifference != 32) {
				return false;
			}
			++left;
			--right;
		}
		return true;
	}

	private static boolean isNotLetterOrNumber(char symbol) {
		return (symbol < '0' || symbol > '9') &&
				(symbol < 'A' || symbol > 'Z') &&
				(symbol < 'a' || symbol > 'z');
	}

	public static void isPalindrome_SkipASCII_Test() {
		{
			String s = "123321";
			System.out.println(isPalindrome_SkipASCII(s) + "  " + isPalindrome_SkipASCII_2(s));
		}
		{
			String s = "12321";
			System.out.println(isPalindrome_SkipASCII(s) + "  " + isPalindrome_SkipASCII_2(s));
		}
		{
			String s = "12345";
			System.out.println(isPalindrome_SkipASCII(s) + "  " + isPalindrome_SkipASCII_2(s));
		}
		{
			String s = "Madam, I'm Adam!";
			System.out.println(isPalindrome_SkipASCII(s) + "  " + isPalindrome_SkipASCII_2(s));
		}
	}

	public static void isPalindrome_SkipASCII_Perfomance()
	{
		String s = "Madam, I'm Adam!";
		int TEST_REPS_COUNT = 5000;

		{
			long start = System.nanoTime();
			for (long n = 0; n < TEST_REPS_COUNT; ++n) {
				for (long i = 0; i < TEST_REPS_COUNT; ++i) {
					isPalindrome_SkipASCII(s);
				}
			}
			long nsDuration = System.nanoTime() - start;
			System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));
		}
		/*
		{
			long start = System.nanoTime();
			for (long n = 0; n < TEST_REPS_COUNT; ++n) {
				for (long i = 0; i < TEST_REPS_COUNT; ++i) {
					isPalindrome_SkipASCII_2(s);
				}
			}
			long nsDuration = System.nanoTime() - start;
			System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));
		}
		{
			long start = System.nanoTime();
			for (long n = 0; n < TEST_REPS_COUNT; ++n) {
				for (long i = 0; i < TEST_REPS_COUNT; ++i) {
					isPalindrome_SkipASCII_3(s);
				}
			}
			long nsDuration = System.nanoTime() - start;
			System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));
		}*/
		{
			long start = System.nanoTime();
			for (long n = 0; n < TEST_REPS_COUNT; ++n) {
				for (long i = 0; i < TEST_REPS_COUNT; ++i) {
					isPalindrome_SkipASCII_4(s);
				}
			}
			long nsDuration = System.nanoTime() - start;
			System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));
		}
	}
}

public class StringsAlgoritms {
	/** **/
	private final static Algoritms algoritms = new Algoritms();
	
	public static void main(String[] args) {
		// algoritms.Test1();
		// algoritms.Swap_Test();
		// algoritms.CharsOccurancesCount();
		// algoritms.FirstCharWithMaxOccurance();
		// algoritms.IsAnagrams();

		algoritms.isPalindromeTest();
		// algoritms.isPalindrome_SkipASCII_Test();
		// algoritms.isPalindrome_SkipASCII_Perfomance();
	}
}
