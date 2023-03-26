/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ProcessMemory.java class
*
* @name    : ProcessMemory.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 5, 2020
****************************************************************************/

package memory;

public class ProcessMemory {
    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }
	
	public static void main(String[] args)
	{

		// Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
		
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "+ bytesToMegabytes(memory));
	}
}
