/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* FileWriter_Tests description
*
* @name    : FileWriter_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 19, 2020
* 
****************************************************************************/ 

package Files.Write_Files_Tests;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class FileWriterTester {
    private static final String fileName = "/home/andtokm/tmp/testing_folder/trace.log";

    protected void WriteFile() {
	    try (FileWriter writer = new FileWriter(fileName, false))  {

            writer.write("Line_1\n");
            writer.write("Line_2\n");
            writer.write("Line_3\n");
             
            writer.flush();
        } catch (final IOException ex){   
            System.out.println(ex.getMessage());
        } 
	}
	
	
	protected void AppendFile() {
	    try (FileWriter writer = new FileWriter(fileName, true))  {
	    	
            writer.write("Line_4\n");
            writer.write("Line_5\n");
            writer.write("Line_6\n");
             
            writer.flush();
        } catch (final IOException ex){   
            System.out.println(ex.getMessage());
        } 
	}
	
	protected void ReadFle_Buff() {
	    try (FileReader reader = new FileReader(fileName)) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf))>0) {
                if (c < buf.length){
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
            } 
           
        } catch (final IOException ex){
            System.out.println(ex.getMessage());
        }       
    }   
	
	protected void ReadFle_Chars() {
	    try (FileReader reader = new FileReader(fileName)) {
	    	int c;
	    	while ((c = reader.read())!=-1){
	    		System.out.print((char)c);
	    	} 
	    } catch(IOException ex){
	    	System.out.println(ex.getMessage());
        }       
    }   
	
}

public class FileWriter_Tests {
	private static final FileWriterTester tester = new FileWriterTester();

	public static void main(String[] args) {

		 tester.WriteFile();
		// tester.AppendFile();
		
		// tester.ReadFle_Buff();
		// tester.ReadFle_Chars();
	}
}
