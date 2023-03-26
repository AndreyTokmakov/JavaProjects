package Serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class SimpleParameters implements Serializable {
	/**  **/
	private static final long serialVersionUID = -7383306993098189706L;
	protected String name = "";
	protected int value = 123;

	public SimpleParameters() {
		this("<Empty>", -1);
	}

	public SimpleParameters(final String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("SimpleParameters: {%s, %d}", this.name, this.value);
	}
}

class SimpleParamsTest {
	
	protected void PutObject(final String name, int value) throws IOException {
		FileOutputStream fileStream = new FileOutputStream("S:\\Temp\\TESTING_ROOT_DIR\\params.ser");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileStream);

		SimpleParameters params = new SimpleParameters(name, value);
		objectOutputStream.writeObject(params);
		
		objectOutputStream.close();
		fileStream.close();
	}
	
	protected void GetObject() throws IOException {
		FileInputStream fileStream = new FileInputStream("S:\\Temp\\TESTING_ROOT_DIR\\params.ser");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);

		SimpleParameters params = null;
		try {
			params = (SimpleParameters)objectInputStream.readObject();
		} catch (final ClassNotFoundException | IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		
		objectInputStream.close();
		fileStream.close();
		
		System.out.println(params);
	}
	
	public void Test() throws IOException {
		// this.PutObject("Nyaaaaaaaaaa", 123);
		this.GetObject();
	}
}

///////////////////////////////////////////////////////////////////////


public class SerializationTests {

	/******************** main 
	 * @throws IOException ************************/
	public static void main(String[] args) throws IOException {
		SimpleParamsTest tests = new SimpleParamsTest();
		tests.Test();
	}
}
