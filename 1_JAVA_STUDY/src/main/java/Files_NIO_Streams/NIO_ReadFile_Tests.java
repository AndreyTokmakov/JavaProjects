/****************************************************************************
 * Copyright 2020 (C) Andrey Tokmakov
* Observer pattern demo class
*
* @name    : ReadFile_NIO_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 03, 2020
****************************************************************************/ 

package Files_NIO_Streams;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class File_NIO_Reader {
	private static final String fodler = "D:\\Temp\\Folder_For_Testing\\Read_Write_Files";
	private static final String file_name = "read_write.txt";
	private static final String fileName = fodler + "\\" + file_name;
	
	public void Read() throws IOException {
		
		RandomAccessFile aFile = new RandomAccessFile(File_NIO_Reader.fileName, "r");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		while (inChannel.read(buffer) > 0) {
			buffer.flip();
			for (int i = 0; i < buffer.limit(); i++)
				System.out.print((char) buffer.get());
			buffer.clear();
		}
	        
		inChannel.close();
	    aFile.close();
	}
	
	public void Read_ByteChannel() throws IOException {
		Path file = null;
		try {
			file = Path.of(File_NIO_Reader.fileName);
		} catch (InvalidPathException exc) {
			System.out.println(exc);
			return;
		}
		
		try (SeekableByteChannel byteChannel = Files.newByteChannel(file, StandardOpenOption.READ)) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(16);

			Charset charset = Charset.forName("US-ASCII");
			while (byteChannel.read(byteBuffer) > 0) {
				byteBuffer.rewind();
				System.out.print(charset.decode(byteBuffer));
				byteBuffer.flip();
			}
		} catch (IOException exc)  {
			exc.printStackTrace();
		}
	}
	
	public void Read_MappedBuffer() throws IOException {
		
		RandomAccessFile file = new RandomAccessFile(File_NIO_Reader.fileName, "r");
		FileChannel inChannel = file.getChannel();
		MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
	 
		buffer.load();  
		for (int i = 0; i < buffer.limit(); i++) {
			System.out.print((char) buffer.get());
		}
	        
		buffer.clear(); // do something with the data and clear/compact it.
		inChannel.close();
		file.close();
	} 
	
	public void Read_LineByLine() {
		Path filePath = Paths.get(fodler, file_name);
		
		try (Stream<String> lines = Files.lines( filePath ))  {
		    lines.forEach(System.out::println);
		}  catch (IOException e)  {
		    e.printStackTrace();
		}
	}
	
	public void Read_LineByLine_Stream_Filter() {
		Path filePath = Paths.get(fodler, file_name);
		
		try (Stream<String> lines = Files.lines(filePath)) {
		     List<String> filteredLines = lines.filter(s -> s.contains("Teachers")).collect(Collectors.toList());
		     filteredLines.forEach(System.out::println);
		     lines.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	protected void Read_LineByLine_Stream() throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			lines.forEach((String line) -> {System.out.println(line);});
			lines.close();
		}
	}
}

public class NIO_ReadFile_Tests {
	public static void main(String[] args) throws IOException 
	{
		File_NIO_Reader reader = new File_NIO_Reader();
		
		 reader.Read();
		// reader.Read_MappedBuffer();
		// reader.Read_ByteChannel();
		
		// reader.Read_LineByLine();
		// reader.Read_LineByLine_Stream_Filter();
		// reader.Read_LineByLine_Stream();
	}
}
