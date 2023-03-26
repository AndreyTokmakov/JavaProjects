package Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Files_NIO_Tests {
	
	private final static String testDirectory = "S:\\Temp\\Folder_For_Testing";

	protected void Path_Basic_Tests() {
		Path path = Paths.get(testDirectory);
		
		System.out.println("Full path: " + path);
		System.out.println("Parent: " + path.getParent());

		System.out.println("Parts: ");
		for (int i = 0; i < path.getNameCount(); i++)
			System.out.println("     " + path.getName(i));
		
		System.out.println("\n\nRoot: " + path.getRoot());
	}
	
	protected void Concat_Paths() {
		Path path = Paths.get(testDirectory);
		
		System.out.println(path);
		path = path.resolve("Dir1").resolve("Dir2").resolve("Dir3");
		System.out.println(path);
	}
	
	protected void File_Statuses() throws IOException {
		String exeFile = "S:\\Temp\\Folder_For_Testing\\Collections_Study_And_Test_Project.exe";
		
		Path path = Paths.get(exeFile);

		System.out.println("File: " + path + "\n");
		
		
		System.out.println("isReadable = " + Files.isReadable(path));
		System.out.println("isWritable = " + Files.isWritable(path));
		System.out.println("isExecutable = " + Files.isExecutable(path));
		
		System.out.println("\nisSameFile = " + Files.isSameFile(path, Paths.get(exeFile)));
		System.out.println("isSameFile = " + Files.isSameFile(path, Paths.get(testDirectory)));
	}
	
	
	protected void Metadata() {
		Path path = Paths.get(testDirectory);
		path = path.resolve("File_11.txt");
		
		System.out.println("Exists: " + path);
		
	}
	
	/*************** Tests 
	 * @throws IOException ******************/
	public static void main(String[] args) throws IOException {
		Files_NIO_Tests tests = new Files_NIO_Tests();
		
		// tests.Path_Basic_Tests();
		// tests.Concat_Paths();
		// tests.File_Statuses();
		
		tests.Metadata();
	}
}
