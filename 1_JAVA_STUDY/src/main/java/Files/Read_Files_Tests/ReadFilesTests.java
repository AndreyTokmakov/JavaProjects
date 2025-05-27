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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class FileReaderTester {
	/** Test filename: **/
	private static final String fileName = "/home/andtokm/Documents/Binance/ssh_Key/ed25519.pem";
	
	protected void ReadLambdaTest_Stream() throws IOException {
		try (final Stream<String> lines = Files.lines(Paths.get(FileReaderTester.fileName))) {
			lines.forEach(System.out::println);
		}
	}
	
	protected void ReadLambdaTest_Stream_2() throws IOException {
		try (final Stream<String> linesStream = Files.lines(new File(FileReaderTester.fileName).toPath())) {
			linesStream.forEach(line -> { System.out.println(line); });
		}
	}
	
	public void ReadFile_BufferedReader() throws IOException {
		String line = "";
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			while (null != (line = bufferedReader.readLine())) 
				System.out.println(line);
		}
	}
	
	public void Read_All_Bytes() throws Exception  { 
	    String data = new String(Files.readAllBytes(Paths.get(fileName))); 
	    System.out.println(data); 
	} 
	
	public void Read_All_Bytes_2() throws Exception
	{
		byte[] allBytes = Files.readAllBytes(Paths.get(fileName));
		System.out.println(Arrays.toString(allBytes));
	} 
	
	public void Read_All_Lines() throws Exception  { 
		// Collections
		try { 
			List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
			for (String s: lines)
				System.out.println(s);
		} catch (IOException e)  { 
			e.printStackTrace(); 
		}
	} 
	
	
}

public class ReadFilesTests {
	/** File reader tester: **/
	private static FileReaderTester reader = new FileReaderTester();
	
	public static void main(String[] args) throws Exception
	{
		// reader.ReadLambdaTest_Stream();
		// reader.ReadLambdaTest_Stream_2();
		// reader.ReadFile_BufferedReader();
		
		// reader.Read_All_Bytes();
		reader.Read_All_Bytes_2();
		
		// reader.Read_All_Lines();
	}
}
