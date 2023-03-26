package Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

class DirectoriesTester {
	private final static String baseDir = "S:\\Temp\\Folder_For_Testing\\111";
	
	protected void Create_Many_Directories() {
		try {
			final Path path = Paths.get(DirectoriesTester.baseDir + "\\Dir1\\Dir2\\Dir3");
			Files.createDirectories(path);
			System.out.println("Directory is created!");
		} catch (IOException e) {
			System.err.println("Failed to create directory!" + e.getMessage());
		}
	}
	
	protected void Create_One_Dir() {
		try {
			final Path path = Paths.get(DirectoriesTester.baseDir + "\\DirX");
			Files.createDirectory(path);
			System.out.println("Directory is created!");
		} catch (IOException e) {
			System.err.println("Failed to create directory!" + e.getMessage());
		}
	}
	
	protected void Delete_One_Dir(Path path) {
	    try {
	        Files.delete(path);
	    } catch (IOException e) {
	        System.err.printf("Unable to delete this path : %s%n%s", path, e);
	    }
	}
	
	protected void Delete_Directories() throws IOException {
		final Path path = Paths.get(DirectoriesTester.baseDir + "\\Dir1");

	    // read java doc, Files.walk need close the resources.
	    // try-with-resources to ensure that the stream's open directories are closed
	    try (Stream<Path> walk = Files.walk(path)) {
	        walk.sorted(Comparator.reverseOrder()).forEach(this::Delete_One_Dir);
	    }
	}
}

public class Directories {
	private final static DirectoriesTester tester = new DirectoriesTester();
	
	public static void main(String[] args) throws IOException {
		// tester.Create_Many_Directories();
		// tester.Create_One_Dir();

		tester.Delete_Directories();
	}
}
