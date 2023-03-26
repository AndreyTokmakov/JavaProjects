package Memory;

import java.util.concurrent.TimeUnit;

public class Variable {
	/** Variable name: **/
	protected String name;
	/** Variable value: **/
	protected String value;
	
	public Variable() {
		this("<Empty>", "<None>");
	}

	public Variable(final String name, final String value) {
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
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("{%s, %s}", name, value);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		// super.finalize();
		System.out.println("Finalizing " + this.toString());
		TimeUnit.MILLISECONDS.sleep(500);
	}
}
