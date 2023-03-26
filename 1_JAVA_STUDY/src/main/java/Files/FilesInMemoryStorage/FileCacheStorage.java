/**
* Copyright 2020 (C) Andrey Tokmakov
* Class implements CacheFile object
*
* @name      : BrowserBuilds.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 17, 2020
*/ 

package Files.FilesInMemoryStorage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

class CacheFile extends File {
	/** **/
	private static final long serialVersionUID = -5373458384984923091L;
	
	/** **/
	private byte[] fileBytes;
	
	/** **/
	private boolean isBytesValid = false;	
	
	public CacheFile(File parent, String child) {
		super(parent, child);
		this.readFileBytes();
	}
	
	public CacheFile(String pathname) {
		super(pathname);
		this.readFileBytes();
	}
	
	public CacheFile(URI uri) {
		super(uri);
		this.readFileBytes();
	}
	
	protected void readFileBytes() {
		try {
			this.fileBytes = FileUtils.readFileToByteArray(this);
			isBytesValid = true;
		} catch (IOException exception) {
			exception.printStackTrace();
			isBytesValid = false;
		}
	}
	
	public byte[] getFileBytes() {
		return this.fileBytes;
	}
	
	public boolean hasBytesData() {
		return this.isBytesValid;
	}
	
	public long bytesBufferLength() {
		return this.fileBytes.length;
	}
}

// TODO : Make it Singleton
public class FileCacheStorage {
	/** **/
	private Map<String, CacheFile> cachedFiles = new HashMap<String, CacheFile>();
	
	/** FileCacheStorage class constructor : **/
	public FileCacheStorage() {
		System.out.println(this.getClass().getName() + " created.");
	} 

	public void addFile(final CacheFile file) {
		if (true == file.hasBytesData()) {
			System.out.println("File '" + file.getAbsolutePath() + "' added to cache.");
			this.cachedFiles.put(file.getName(), file);
		}
	}
	
	public boolean isFileCached(final String fileName) {
		return this.cachedFiles.containsKey(fileName);
	}
	
	public CacheFile getFile(final String fileName) {
		return this.cachedFiles.get(fileName);
	}
	
	public void Test() {
		for(final Map.Entry<String, CacheFile> entry : cachedFiles.entrySet()) {
		    String name = entry.getKey();
		    CacheFile file = entry.getValue();
		    System.out.println(name + "  " + file.getAbsolutePath() + ". Byte buffer length : " + file.bytesBufferLength());
		}
	}
	
	
	/////////////////////////// MAIN /////////////////////////////
	public static void main(String[] args) {
		FileCacheStorage storage = new FileCacheStorage();
		
		storage.addFile(new CacheFile("S:\\Temp\\FILES\\TestFile1.txt"));
		storage.addFile(new CacheFile("S:\\Temp\\FILES\\TestFile2.txt"));
		storage.addFile(new CacheFile("S:\\Temp\\FILES\\TestFile3.txt"));
		
		storage.Test();
		System.out.println(storage.isFileCached("TestFile1.txt"));
		
		System.out.println(storage.getFile("232323"));
	}
}