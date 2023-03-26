/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Read files tests
*
* @name    : ReadFilesTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 19, 2020
* 
****************************************************************************/ 

package Files.Read_Files_Tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Worker {
	private static final String fileName = "S:\\Temp\\Folder_For_Testing\\Read_Write_Files\\notes.txt";
	
	/*
	protected void WriteToFile() { 
		String text = "Hello world!"; 
		try (FileOutputStream fos = new FileOutputStream(fileName)) {
			// 2Bytes
			byte[] buffer = text.getBytes();
			fos.write(buffer, 0, buffer.length);
		} catch( IOException ex){
			System.out.println(ex.getMessage());
		}
		System.out.println("The file has been written");
	}
	*/
	
	protected void ReadFromFile() { 
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
}

public class FileInputStream_Tests {
	private static final Worker worker = new Worker();
	
	public static void main(String[] args) {
		worker.ReadFromFile();
	}
}
