package Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Apache_IOUtils_Tests {
	
	private final static String fileName = "src\\Files\\MetricsConfiguration.xml";
	
	public void ReadFileBytest() throws IOException
	{
		byte[] fileBytes = FileUtils.readFileToByteArray(new File(fileName));
	}

	public String GetFileLines_AsString(final String fileName) throws IOException {
		String text = "";
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				text += line;
			}
		}
		return text;
	}
	
	/** List files : 
	 * @throws IOException **/
	public List<String> GetFileLines(final String fileName) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		try (final Stream<String> linesStream = Files.lines(new File(fileName).toPath())) {
			linesStream.forEach(line -> { lines.add(line); });
		}
		return lines;
	}
	
	/** List files : 
	 * @return 
	 * @throws IOException **/	
	public ArrayList<String> GetFileLines_AsArray(final String fileName) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
		}
		return lines;
	}
	
	@SuppressWarnings("unused")
	private void readFile_APACHE_IO(final String file) throws IOException {
		FileReader fileReader = new FileReader(file);
		final String str = IOUtils.toString(fileReader);
		System.out.println(str);
	}
	
    /**
     * Resolves the input String.
     * Evaluates all contained system environment variables; replace
     * them all with correspond values.
     * 
	 * @param  str The input string to be evaluated.
	 * @return String with all variables replaced by their value
     * @throws NullPointerException if the specified input string is null
     */
	public String resolveString(final String str) {
		String resolvedString = str;
		final Map<String,String> sysVars = System.getenv();
		for (Entry<String, String> entry : sysVars.entrySet()) 
			resolvedString = resolvedString.replace("%" + entry.getKey() + "%", entry.getValue());
		return resolvedString;
	}
	
	private void readFile_APACHE_IO_ENV_VAR() throws IOException {
		String path = "%ALLUSERSPROFILE%\\Mail.Ru\\Id";
		String fileNameNew = resolveString(path);
		FileReader fileReader = new FileReader(fileNameNew);
		final String str = IOUtils.toString(fileReader);
		System.out.println(str);
	}
	
	@SuppressWarnings("unused")
	private void readFile_AsList_APACHE_IO() throws IOException {
		FileInputStream stream = new FileInputStream(fileName);
		final List<String> list = IOUtils.readLines(stream, "UTF-8");
		for (final String line: list)
			System.out.println(line);
	}

	// TODO: Add comments for method description
	/** readFileToString : 
	 * @return 
	 * @throws IOException **/	
	public static String readFileToString(final String fileName) throws IOException {
		String buffer = "";
		try (FileReader fileReader = new FileReader(fileName)) {
			buffer = IOUtils.toString(fileReader);
		}
		return buffer;
	}
	
	// TODO: Add comments for method description
	/** readFileToString : 
	 * @return 
	 * @throws IOException **/	
	public String resolveAndReadFileToString(final String fileName) throws IOException {
		FileReader fileReader = new FileReader(resolveString(fileName));
		return IOUtils.toString(fileReader);
	}
	
	// TODO: Add comments for method description
	/** readFileLines : 
	 * @return 
	 * @throws IOException **/	
	public static List<String> readFileLines(final String fileName) throws IOException {
		FileInputStream stream = new FileInputStream(fileName);
		return IOUtils.readLines(stream, "UTF-8");
	}
	
	
	////////////////////////////////////////////// LIST FILES /////////////////////////////////////////////////////////

	public void ListFiles(final String folder) throws IOException {
		try (Stream<Path> paths = Files.walk(Paths.get(folder))) {
		    paths.filter(Files::isRegularFile).forEach(System.out::println);
		} 
	}
	
	public void ListDirectoryFiles_1(final String dirPath) throws IOException {
		Files.list(Paths.get(dirPath)).forEach(System.out::println);
	}
	
	// List only files inside directory using filter expression
	public void ListDirectoryFiles_2(final String dirPath) throws IOException {
		Files.list(Paths.get(dirPath)).filter(Files::isRegularFile)		
									  .forEach(System.out::println);
	}
	
	public static void ListDirectoryFiles_3(final String dirPath) throws IOException {
		Files.newDirectoryStream(Paths.get(dirPath)).forEach(System.out::println);
	}
	
	//  List only files with Files.newDirectoryStream()
	public static void ListDirectoryFiles_4(final String dirPath) throws IOException {
		Files.newDirectoryStream(Paths.get(dirPath), path -> path.toFile().isFile()).forEach(System.out::println);
	}
	
	// List files of certain extention with
	public static void ListDirectoryFiles_5(final String dirPath, final String ext) throws IOException {
		Files.newDirectoryStream(Paths.get(dirPath),path -> path.toString().endsWith(ext)).forEach(System.out::println);
	}
	
	
	public static void main(String[] args) throws IOException {
		final String dirPath  = "D:\\Temp\\Folder_For_Testing";
		final String filePath = "D:\\Temp\\Folder_For_Testing\\BUILD.gn";
		
		final File dir = new File(dirPath);
		Apache_IOUtils_Tests tests = new Apache_IOUtils_Tests();
		

		
		// System.out.println(tests.GetFileLines_AsString(fileName));
		
		// tests.readFile_APACHE_IO(filePath);
		// tests.readFile_APACHE_IO_ENV_VAR();
		// tests.readFile_AsList_APACHE_IO();

		// tests.ListFiles(dirPath);
		// tests.ListDirectoryFiles_1(dirPath);
		// tests.ListDirectoryFiles_2(dirPath);
	}
}
