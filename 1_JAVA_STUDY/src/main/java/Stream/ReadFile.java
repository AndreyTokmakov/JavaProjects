/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ReadFile pattern demo class
*
* @name    : ReadFile.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 06, 2020
****************************************************************************/ 

package Stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadFile {
	
	private static final String fileName = "D:\\Temp\\Folder_For_Testing\\Read_Write_Files\\text_file.txt";
	
	protected static void Read_File_Lines() throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			lines.forEach((String line) -> {System.out.println(line);});
			lines.close();
		}
	}

	public static void main(String[] args) throws IOException {
		Read_File_Lines();
	}
}
