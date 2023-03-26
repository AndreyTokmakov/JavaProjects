/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* NIO channels demo class
*
* @name    : NIO_Channels.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 04, 2020
****************************************************************************/ 

package Files_NIO_Streams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class NIO_Channels {
	private static final String fodler = "S:\\Temp\\Folder_For_Testing\\COPY";
	private static final String srcFileName = "Input.txt";
	private static final String dstFileName = "Output.txt";
	
	private static final String srcFilePath = fodler + "\\" + srcFileName;
	private static final String dstFilePath = fodler + "\\" + dstFileName;
	
    private static void copyData(ReadableByteChannel src, 
    		                     WritableByteChannel dest) throws IOException  {
    	ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1)  {
            // Prepare the buffer to be drained
            buffer.flip();
 
            // Make sure that the buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
 
            // Make the buffer empty, ready for filling
            buffer.clear();
        }
    }
    
    public static void CopyFiles() throws IOException {
        FileInputStream input = new FileInputStream (srcFilePath);
        FileOutputStream output = new FileOutputStream(dstFilePath);
        
        ReadableByteChannel source = input.getChannel();
        WritableByteChannel dest = output.getChannel();
 
        copyData(source, dest);
 
        source.close();
        dest.close();
    }

	public static void main(String[] args) throws IOException {
		CopyFiles();

	}
}