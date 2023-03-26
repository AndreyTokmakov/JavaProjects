package Files_NIO_Streams;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

class PrintStream_Tester {
	
	private static final String fileName = "S:\\Temp\\Folder_For_Testing\\Read_Write_Files\\read_write.txt";
	
	public void Write_ToFile() {
        String text = "Hello World";
        try (FileOutputStream outputStream = new FileOutputStream(fileName);
             PrintStream printStream = new PrintStream(outputStream))
        {
            printStream.println(text);
            System.out.println("Write done.");
        } catch(final IOException ex){
            System.out.println(ex.getMessage());
        }  
    } 
	
    public void Write_ToFile_Formaed() {
        
        try (PrintStream printStream = new PrintStream(fileName))
        {
            printStream.print("Hello World!");
            printStream.println("Welcome to Java!");
            printStream.printf("Name: %s Age: %d \n", "Tom", 34);
             
            String message = "PrintStream";
            byte[] message_toBytes = message.getBytes();
            printStream.write(message_toBytes);
             
            System.out.println("The file has been written");
        } catch(final IOException exc) {
            System.out.println(exc.getMessage());
        }  
    } 
}


public class PrintStream_Tests {
	private static final PrintStream_Tester tester = new PrintStream_Tester();
	
	public static void main(String[] args) {
		
		// tester.Write_ToFile();
		tester.Write_ToFile_Formaed();
	}
}
