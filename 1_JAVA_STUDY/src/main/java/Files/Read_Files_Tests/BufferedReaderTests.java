/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Buffered reader tests
*
* @name      : BufferedReaderTests.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 19, 2020
* 
****************************************************************************/ 

package Files.Read_Files_Tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class BufferedReader_Tester {
	private static final String fileName = "S:\\Temp\\Folder_For_Testing\\Read_Write_Files\\read_write.txt";
	
	protected void ReadFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = br.read()) != -1){
                System.out.print((char)c);
            }
        } catch(IOException ex){          
            System.out.println(ex.getMessage());
        } 
    }
	
	protected void ReadFile_Lines() {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String s;
		    int line = 1;
		    while ((s = br.readLine()) != null) {    
		        System.out.println(line++ + " line: " + s);
		    }
		} catch(IOException ex){   
		    System.out.println(ex.getMessage());
		} 
	}
}

public class BufferedReaderTests {
	private final static BufferedReader_Tester tester = new BufferedReader_Tester();
	
	public static void main(String[] args) {
		// tester.ReadFile();
		tester.ReadFile_Lines();
	}
}
