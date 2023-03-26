package Exceptions;

class SomeResource implements AutoCloseable {
	
	public SomeResource() {
		System.out.println("Allocate resources");
	}
	
    @Override
    public void close() {
    	System.out.println("Release alocated resources");
    }
}

public class ExceptionsTesting {
	
	public void AutoCloseableTest() {
		try (final SomeResource res = new SomeResource()) {
			System.out.println("Do some work");
            int[] values = new int[5];
            values[10] = 12;
        }
		catch (Exception exc) {
			System.err.println(exc);
		}
		finally {
			System.out.println("Finaly block");
		}
	}

	/** TESTS. **/
	public static void main(String[] args) {
		ExceptionsTesting tester = new ExceptionsTesting();
		tester.AutoCloseableTest();
	}
}
