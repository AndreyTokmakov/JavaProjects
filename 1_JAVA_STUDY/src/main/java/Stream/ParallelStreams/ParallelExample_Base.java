/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ParallelExample_Base class
*
* @name    : ParallelExample_Base.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 29, 2020
****************************************************************************/ 

package Stream.ParallelStreams;

import java.util.stream.IntStream;

public class ParallelExample_Base {
	
    public static void main(String[] args) 
    {
        System.out.println("Normal...");
      
        IntStream range = IntStream.rangeClosed(1, 10);
        range.forEach(System.out::println);

        System.out.println("Parallel...");

        IntStream range2 = IntStream.rangeClosed(1, 10);
        range2.parallel().forEach(System.out::println);
    }
}
