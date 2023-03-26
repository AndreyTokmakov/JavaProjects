/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Files memory storage:
*
* @name    : FilesInMemoryStorage.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 19, 2020
****************************************************************************/ 

package Files.FilesInMemoryStorage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

final class MemoryFile {
	/** **/
	private static final long serialVersionUID = -5373458384984923091L;
	/** **/
	private byte[] fileBytes;
	/** **/
	private File file = null;
	
	public MemoryFile(final String pathname) {
		this.file = new File(pathname);
	}	
	
	protected void readFile() {
		try {
			this.fileBytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public byte[] getFileBytes() {
		return this.fileBytes;
	}

	public long bytesBufferLength() {
		return this.fileBytes.length;
	}
}

public class FileMemoryStorage {
	
	/*
	 * torage.addFile(new CacheFile("S:\\Temp\\FILES\\TestFile1.txt"));
		storage.addFile(new CacheFile("S:\\Temp\\FILES\\TestFile2.txt"));
		storage.addFile(new CacheFile("S:\\Temp\\FILES\\TestFile3.txt"));
	*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemoryFile file = new MemoryFile("S:\\\\Temp\\\\FILES\\\\TestFile1.txt");
		file.readFile();
	}
}
