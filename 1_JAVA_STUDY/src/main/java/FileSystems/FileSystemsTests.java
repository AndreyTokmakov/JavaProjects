/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* FileSystemsTests.java class
*
* @name    : FileSystemsTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 9, 2020
****************************************************************************/

package FileSystems;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileSystemsTests {

	public static void printDetails(FileStore store) {
		try {
			String desc = store.toString();
			String type = store.type();
			
			long totalSpace = store.getTotalSpace();
			long unallocatedSpace = store.getUnallocatedSpace();
			long availableSpace = store.getUsableSpace();
			
			System.out.println(desc + ".  Type: " + type  + ", Total: " + totalSpace + ",  Unallocated: " + unallocatedSpace + ",  Available: " + availableSpace);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void Test() {
		FileSystem fileSystem = FileSystems.getDefault();

		System.out.println("Read-only file system: " + fileSystem.isReadOnly());
		System.out.println("File name separator: " + fileSystem.getSeparator());
		
		System.out.println();
		for (FileStore store : fileSystem.getFileStores())
			printDetails(store);
		for (Path root : fileSystem.getRootDirectories())
			System.out.println(root);
	}
	
	public static void main(String[] args) {
		Test();
	}
}
