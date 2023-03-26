/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* PrintWriter_Simple description
*
* @name    : PrintWriter_Simple.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 19, 2020
* 
****************************************************************************/ 

package Files.Write_Files_Tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PrintWriter_Simple {
	private static final String fileName = "S:\\Temp\\FILES\\TestFile3.txt";
	
	protected static void write() throws FileNotFoundException, UnsupportedEncodingException { 
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		writer.println("The first lin1e");
		writer.println("The second line1");
		writer.close();
    }
	
	
	protected static void read() { 
		try (FileInputStream inputStream = new FileInputStream(fileName)) {
			System.out.printf("File size: %d bytes \n", inputStream.available());
            int i = -1;
            while (-1 != (i=inputStream.read())) {
            	System.out.print((char)i);
            }   
        } catch(final IOException ex){   
            System.out.println(ex.getMessage());
        } 
    }
	
	public static void main(String[] args) 
			throws FileNotFoundException, UnsupportedEncodingException {
		write();
		read();
	}
}
