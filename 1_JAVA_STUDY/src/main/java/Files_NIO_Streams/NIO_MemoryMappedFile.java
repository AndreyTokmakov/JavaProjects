/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* NIO MappedByteBuffer demo class
*
* @name    : NIO_MemoryMappedFile.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 04, 2020
****************************************************************************/ 

package Files_NIO_Streams;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

class NIO_MemoryMappedFile_Reader {
	
	private final static String filePath = "S:\\Temp\\Folder_For_Testing\\Read_Write_Files\\text_file.txt";
	
	public void write() throws Exception {
		//int length = 0x8FFFFFF; 
		int length = 100;  // Write 100 bytes to file
		try (RandomAccessFile file = new RandomAccessFile(NIO_MemoryMappedFile_Reader.filePath, "rw")) {
			MappedByteBuffer out = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
			for (int i = 0; i < length; i++) {
				out.put((byte) 'x');
			}
			System.out.println("Finished writing");
        }
	}
	
	public void ReadFile() throws Exception 
    {
        try (RandomAccessFile file = new RandomAccessFile(new File(NIO_MemoryMappedFile_Reader.filePath), "r")) {
            //Get file channel in read-only mode
            FileChannel fileChannel = file.getChannel();
             
            //Get direct byte buffer access using channel.map() operation
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
             
            // the buffer now reads the file as if it were loaded in memory. 
            System.out.println("isLoaded: " + buffer.isLoaded());  //prints false
            System.out.println("capacity: " + buffer.capacity());  //Get the size based on content size of file
             
            // Read the file from this buffer the way you like.
            for (int i = 0; i < buffer.limit(); i++)
            	System.out.print((char) buffer.get()); //Print the content of file
        }
    }
}

public class NIO_MemoryMappedFile {
	public static void main(String[] args) throws Exception {
		NIO_MemoryMappedFile_Reader reader = new NIO_MemoryMappedFile_Reader();
		// reader.write();
		reader.ReadFile();
	}
}
