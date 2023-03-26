/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* AutoCloseableTests class
*
* @name    : AutoCloseableTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Some_Month 01, 2020
****************************************************************************/ 

package Memory;

class SomeResource implements AutoCloseable {
	public SomeResource() {
		System.out.println("Allocate resources");
	}
	
	public void DoWork() {
		System.out.println("*** DoWork ***");
	}
	
    @Override
    public void close() {
    	System.out.println("SomeResource::close()  Release alocated resources");
    }
}

public class TryCatch_With_Resources {
	
	public void Test1() {
		try (final SomeResource res = new SomeResource()) {
			System.out.println("Do some work");
            int[] values = new int[5];
        }
	}
	
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

	public void AutoCloseableTest_NoException() {
		try (final SomeResource res = new SomeResource()) {
			System.out.println("Do some work");
			res.DoWork();
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
		TryCatch_With_Resources tester = new TryCatch_With_Resources();
		
		tester.Test1();
		// tester.AutoCloseableTest();
		// tester.AutoCloseableTest_NoException();
	}
}
