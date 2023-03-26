/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* NIO_Buffers demo class
*
* @name    : NIO_Buffers.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 03, 2020
****************************************************************************/ 

package Files_NIO_Streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class NIO_Buffers {
	
	public static void Create() {
		CharBuffer charBuffer1 = CharBuffer.allocate(100);
		System.out.println("CharBuffer1 of " + charBuffer1.length() + " created");
		
		char[] myArray = new char[100];
		CharBuffer charbuffer2 = CharBuffer.wrap (myArray);
		System.out.println("CharBuffer2 of " + charbuffer2.length() + " created");
		
		CharBuffer charbuffer3 = CharBuffer.wrap (myArray, 12, 42);
		System.out.println("CharBuffer3 of " + charbuffer3.length() + " created");
	}
	
	public static void Filling_Buffer() {
		char [] myArray = new char [100];
		CharBuffer buffer = CharBuffer.wrap (myArray , 12, 42);
		System.out.println("Buffer of " + buffer.length() + " created");
		
		buffer.put('H').put('e').put('l').put('l').put('o');
		System.out.println("Buffer of " + buffer.length() + " created");
	}
	

	public static void FromByteBufferToString() 
	{
		// Allocate a new non-direct byte buffer with a 50 byte capacity set this to a big value to avoid BufferOverflowException
		ByteBuffer buf = ByteBuffer.allocate(50);
	 
	    // Creates a view of this byte buffer as a char buffer
		CharBuffer cbuf = buf.asCharBuffer();
	 
		// Write a string to char buffer
		cbuf.put("How to do in java");
	 
		// Flips this buffer. The limit is set to the current position and then
		// the position is set to zero. If the mark is defined then it is
		// discarded
		cbuf.flip();
	 
		String s = cbuf.toString(); // a string
	 
		System.out.println(s);
	}
	
	public static void FileCopyUsingFileChannelAndBuffer() {
        String inFileStr = "S:\\Temp\\Folder_For_Testing\\COPY\\avataar.jpg";
        String outFileStr = "S:\\Temp\\Folder_For_Testing\\COPY\\avataar_out.jpg";
        long startTime, elapsedTime; 
        int bufferSizeKB = 4;
        int bufferSize = bufferSizeKB * 1024;
 
        // Check file length
        File fileIn = new File(inFileStr);
        System.out.println("File size is " + fileIn.length() + " bytes");
        System.out.println("Buffer size is " + bufferSizeKB + " KB");
        System.out.println("Using FileChannel with an indirect ByteBuffer of " + bufferSizeKB + " KB");
         
        try (FileChannel in  = new FileInputStream(inFileStr).getChannel();
		     FileChannel out = new FileOutputStream(outFileStr).getChannel() ) 
        {
            // Allocate an indirect ByteBuffer
            ByteBuffer bytebuf = ByteBuffer.allocate(bufferSize);
 
            startTime = System.nanoTime();
             
            int bytesCount = 0;
            // Read data from file into ByteBuffer
            while ((bytesCount = in.read(bytebuf)) > 0) { 
                // flip the buffer which set the limit to current position, and position to 0.
                bytebuf.flip();
                out.write(bytebuf); // Write data from ByteBuffer to file
                bytebuf.clear(); // For the next read
            }
             
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        } 
        catch (IOException ex) {
            ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// Create();
		// Filling_Buffer();
		// FromByteBufferToString();
		FileCopyUsingFileChannelAndBuffer();
	}
}
