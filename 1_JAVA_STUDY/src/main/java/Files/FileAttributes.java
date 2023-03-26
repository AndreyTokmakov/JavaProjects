package Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

class Tester {
	private static final DateTimeFormatter DATE_FORMATTER = 
			DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	
	private static final String fileName = "S:\\Temp\\Folder_For_Testing\\File_1.txt";
	
	public String formatDateTime(FileTime fileTime) {
		LocalDateTime localDateTime = fileTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return localDateTime.format(DATE_FORMATTER);
	}
	
	public BasicFileAttributes getFileAttributes() throws IOException {
		 Path file = Paths.get(Tester.fileName);
         return Files.readAttributes(file, BasicFileAttributes.class);
	}
	
	public void File_LastModified_Time() {
        try {
        	BasicFileAttributes attributes = this.getFileAttributes();
            System.out.println("lastModifiedTime: " + attributes.lastModifiedTime());
            FileTime fileTime = attributes.lastModifiedTime();
            System.out.println("lastModifiedTime: " + formatDateTime(fileTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void Last_Access_Time() {
        try {
        	BasicFileAttributes attributes = this.getFileAttributes();
            System.out.println("creationTime: "     + formatDateTime(attributes.creationTime()));
            System.out.println("lastAccessTime: "   + formatDateTime(attributes.lastAccessTime()));
            System.out.println("lastModifiedTime: " + formatDateTime(attributes.lastModifiedTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}


public class FileAttributes {
	protected static final Tester tester = new Tester();
	
	public static void main(String[] args) {
		// tester.File_LastModified_Time();
		tester.Last_Access_Time();
	}
}









