package Files_NIO_Streams;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class Person {
    public String name;
    public int age;
    public double height;
    public boolean married;
      
    public Person(String n, int a, double h, boolean m) {
        this.name = n;
        this.height = h;
        this.age = a;
        this.married =m ;
    }

	@Override
	public String toString() {
		return String.format("Person(%s, %d, %f, %b)", this.name, this.age, this.height, this.married);
	}
}

class DataOutputStream_Tester {
	private static final String fileName = "S:\\Temp\\Folder_For_Testing\\Read_Write_Files\\data.bin";
	
	
	protected void Write_Variables_FromFile() {
		 Person tom = new Person("Tom", 34, 1.68, false);
		 System.out.println(tom);
		 
		 // Write to file
	     try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
	            dos.writeUTF(tom.name);
	            dos.writeInt(tom.age);
	            dos.writeDouble(tom.height);
	            dos.writeBoolean(tom.married);
	            System.out.println("File has been written");
	        } catch (final IOException ex){
	            System.out.println(ex.getMessage());
	        }  
	          
	        // Read from file:
	        try (DataInputStream dos = new DataInputStream(new FileInputStream(fileName))) {
	        	Person tomNew = new Person("Tom", 34, 1.68, false);
	        	tomNew.name = dos.readUTF();
	        	tomNew.age = dos.readInt();
	        	tomNew.height = dos.readDouble();
	        	tomNew.married = dos.readBoolean();
	            System.out.println(tomNew);
	        }
	        catch (final IOException ex){   
	            System.out.println(ex.getMessage());
	        }  
	}
}


public class DataOutputStream_Tests {
	
	private final static DataOutputStream_Tester tester = new DataOutputStream_Tester();
	
	public static void main(String[] args) {
		tester.Write_Variables_FromFile();
	}
}
