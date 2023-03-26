package ObjectOrientedProgramming;

class PrivateBase {
	private int value = 0;
	
	@SuppressWarnings("unused")
	private void Info() {
		System.out.println("Info:  " + this.getClass().getName());
	}

	private int getValue() {
		return value;
	}

	private void setValue(int value) {
		this.value = value;
	}
	
	protected void Info_Protected() {
		System.out.println("Info_Protected:  " + this.getClass().getName());
	}
}

class PrivateDerived extends PrivateBase {
	
	public void Test() {
		// ERROR: We can not call PrivateBase.Info();
		// super.Info();
		
		super.Info_Protected();
	}
}

//////////////////////////// Protected ////////////////////////////////////

class ProtectedBase {
	protected int value = 0;
	
	protected void Info() {
		System.out.println("Info:  " + this.getClass().getName());
	}

	protected int getValue() {
		return value;
	}

	protected void setValue(int value) {
		this.value = value;
	}
}


///////////////////////////////////////////////////////

public class AccessModifiersTests {
	
	protected void Private_Class_Tests() {
		PrivateBase obj = new PrivateBase();
		
		// ERROR: We can not call PrivateBase.Info();
		// obj.Info();
	}
	
	protected void Protected_Class_Tests() {
		ProtectedBase obj = new ProtectedBase();
		
		obj.Info();
		
		// We can call setValue and setValue even if they are 'protected'
		obj.setValue(123);
		System.out.println("Value:  " + obj.getValue());
	}
	
	
	public static void main(String[] args) {
		AccessModifiersTests tests = new AccessModifiersTests();
		
		tests.Private_Class_Tests();
		
		tests.Protected_Class_Tests();
		
	}
}
