/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Create_Stream_Tests.java class
*
* @name    : Create_Stream_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 7, 2021
****************************************************************************/

package Stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Create_Stream_Tests {
	public static void main(String[] args) 
	{
		System.out.println("--------------------------------- Stream from list: -----------------------------");
		
		{
			Collection<String> collection = Arrays.asList("a1", "a2", "a3");
        	Stream<String> streamFromCollection = collection.stream();
        	streamFromCollection.forEach(s -> System.out.print(s + ' '));
		}
		
		System.out.println("\n--------------------------------- Stream from values: -----------------------------");
		
		{
			Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");
			streamFromValues.forEach(s -> System.out.print(s + ' '));
		}
		
		System.out.println("\n--------------------------------- Stream from values: -----------------------------");
		
		{
			String[] array = {"a1","a2","a3"};                     
			Stream<String> streamFromArrays = Arrays.stream(array);
			streamFromArrays.forEach(s -> System.out.print(s + ' '));
		}
		
		System.out.println("\n--------------------------------- IntStream Stream: -----------------------------");
		
		{
			IntStream streamFromString = "123".chars();
			streamFromString.forEach(s -> System.out.print(s + "  "));
		}
		
		System.out.println("\n--------------------------------- Stream.builder: -----------------------------");
		
		{
			Stream<Object> stream = Stream.builder().add("a1").add("a2").add("a3").build();
			stream.forEach(s -> System.out.print(s + "  "));
		}
		
		System.out.println("\n--------------------------------- Infinite lopp: -----------------------------");
		
		{
			/*
			Stream<Integer> stream = Stream.iterate(1, n -> n + 1);
			stream.forEach(s -> System.out.print(s + "  "));
			*/
		}
		
		System.out.println("\n--------------------------------- Infinite lopp: -----------------------------");
		
		{
			Stream<String> streamFromGenerate = Stream.generate(() -> "a1");
			streamFromGenerate.forEach(s -> System.out.print(s + "  "));
		}
	}
}
