package Serialization;

import java.io.*;

class Logon implements Serializable {
    /** **/
	@Serial
	private static final long serialVersionUID = 1L;
	
	private final String login;
    private final transient String password;

    public Logon(final String login, 
    		     final String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Logon [login='" + login + '\'' +", password='" + password + ']';
    }
}


class LogonExt implements Externalizable {
    private String login;
    private String password;

    public LogonExt() {
    	// TODO
    }

    public LogonExt(final String login, 
    		        final String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(login);
    }

    @Override
    public String toString() {
		return "Logon [login='" + login + '\'' +", password='" + password + ']';
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        login = (String) in.readObject();
    }
}


public class ExternalizableTests {
	
	protected void Classic_Serialization_Test() throws FileNotFoundException, IOException, ClassNotFoundException {
		Logon igor = new Logon("IgorIvanovich", "Khoziain");
		Logon renat = new Logon("Renat", "2500RUB");
		
		System.out.println("Before: \n" + igor);
		System.out.println(renat);
		
		ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("/home/andtokm/tmp/JAVA_TESTS/Externals.out"));
		out.writeObject(igor);
		out.writeObject(renat);
		out.close();

		ObjectInputStream input = new ObjectInputStream(
				new FileInputStream("/home/andtokm/tmp/JAVA_TESTS/Externals.out"));
		igor = (Logon) input.readObject();
		renat = (Logon) input.readObject();
		input.close();

		System.out.println("After: \n" + igor);
		System.out.println(renat);
	}
	
	
	protected void Externalizable_Test() {
		Logon igor = new Logon("IgorIvanovich", "Khoziain");
		Logon renat = new Logon("Renat", "2500RUB");
		

		System.out.println("Before:");
		System.out.println(igor);
		System.out.println(renat);

		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("/home/andtokm/tmp/JAVA_TESTS/Externals1.out"))) {
			out.writeObject(igor);
			out.writeObject(renat);
		} catch (final IOException exc) {
			System.err.println(exc);
		} 
		
		try (ObjectInputStream input = new ObjectInputStream(
				new FileInputStream("/home/andtokm/tmp/JAVA_TESTS/Externals1.out"))) {
			igor = (Logon) input.readObject();
			renat = (Logon) input.readObject();
		} catch (final IOException | ClassNotFoundException exc) {
			System.err.println(exc);
		} 

		System.out.println("After:");
		System.out.println(igor);
		System.out.println(renat);
	}
	
	// TODO Auto-generated method stub
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		ExternalizableTests tests = new ExternalizableTests();
		
		tests.Classic_Serialization_Test();
		// tests.Externalizable_Test();
	}
}
