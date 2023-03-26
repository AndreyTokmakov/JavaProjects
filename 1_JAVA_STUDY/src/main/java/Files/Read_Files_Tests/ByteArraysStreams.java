/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Byte arrays streams.
*
* @name    : ByteArraysStreams.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 19, 2020
* 
****************************************************************************/ 

package Files.Read_Files_Tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class ByteIO_Tester {
	private static final String fileName = "S:\\Temp\\Folder_For_Testing\\Read_Write_Files\\bytes";
	
	public void ReadBytes() { 
		byte[] array1 = new byte[]{1, 3, 5, 7};
		ByteArrayInputStream byteStream1 = new ByteArrayInputStream(array1);
        int b;
        while (-1 !=(b=byteStream1.read())){
            System.out.println(b);
        }
         
        String text = "Hello world!";
        byte[] array2 = text.getBytes();
        ByteArrayInputStream byteStream2 = new ByteArrayInputStream(array2, 0, 5);
        int c;
        while (-1 != (c=byteStream2.read())){
            System.out.println((char)c);
        }
    } 
	
	public void WriteBytes() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String text = "Hello Wolrd!";
        byte[] buffer = text.getBytes();
        try{
            baos.write(buffer);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        // turn an array of bytes into a string
        System.out.println(baos.toString());
         
        // get an array of bytes and print character by character
        byte[] array = baos.toByteArray();
        for (byte b: array){
            System.out.print((char)b);
        }
        System.out.println();
    } 
	
	public void BytesToFile() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String text = "Hello Wolrd!";
		byte[] buffer = text.getBytes();
		
		try {
			baos.write(buffer);
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		
		try (FileOutputStream outoutStream = new FileOutputStream(fileName)){
			baos.writeTo(outoutStream);
		} catch(IOException e){		 
			System.out.println(e.getMessage());
		}
    } 
}

public class ByteArraysStreams {
	private final static ByteIO_Tester tester = new ByteIO_Tester();
	
	public static void main(String[] args) {
		// tester.WriteBytes();
		// tester.BytesToFile();
		
		tester.ReadBytes();
	}
}
