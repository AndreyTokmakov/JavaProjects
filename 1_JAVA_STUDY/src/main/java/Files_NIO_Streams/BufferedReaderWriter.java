package Files_NIO_Streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class BufferedReaderWriter_Tester {
	private static final String fileName = "S:\\Temp\\Folder_For_Testing\\Read_Write_Files\\read_write.txt";
	
	protected void WriteFile() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
			String text = "Hello  World!\nHey! Teachers! Leave them kids alone.";
			bw.write(text);
		} catch (IOException ex){
			System.out.println(ex.getMessage());
		} 
	} 
	
	protected void ReadFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = br.read()) != -1){
                System.out.print((char)c);
            }
        } catch(IOException ex){          
            System.out.println(ex.getMessage());
        } 
    }
	
	protected void ReadFile_Lines() {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String s;
		    int line = 1;
		    while ((s = br.readLine()) != null) {    
		        System.out.println(line++ + " line: " + s);
		    }
		} catch(IOException ex){   
		    System.out.println(ex.getMessage());
		} 
	}
}

public class BufferedReaderWriter {
	
	private final static BufferedReaderWriter_Tester tester = new BufferedReaderWriter_Tester();
	
	public static void main(String[] args) {
		// tester.WriteFile();
		// tester.ReadFile();
		tester.ReadFile_Lines();
	}
}
