package ClassTemplatesTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class BaseEntry {
	private long id = -1;
	private String name = "";
	
	public BaseEntry(long id,
					 final String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	public long getid() {
		return this.id;
	}
}

class Build extends BaseEntry {
	public Build() {
		super(1, "Build");
	}
}

class AutoTest extends BaseEntry {
	public AutoTest() {
		super(2, "AutoTest");
	}
}

class UnitTest extends BaseEntry {
	public UnitTest() {
		super(3, "UnitTest");
	}
}

class Handler<Entry extends BaseEntry> {
	public void update(final Entry entry) {
		final Class<?> cls =  entry.getClass() ;

		if (cls == Build.class) {
			System.err.println("This is BUILD class instance");
		} else if (cls == AutoTest.class) {
			System.err.println("This is AUTO TESTS class instance");
		} else if (cls == UnitTest.class) {
			System.err.println("This is UNIT TESTS class instance");
		}
		
		System.out.println("Update to: id = " + entry.getid() + ", name = " + entry.getName());
	}
}

abstract class Worker<Entry extends BaseEntry> {
	protected Handler<Entry> handler = new Handler<Entry>();
	public abstract void Test();
}

class BuildWorker extends Worker<Build> {
	public  void Test() {
		this.handler.update(new Build());
	}
}

class AutoTestWorker extends Worker<AutoTest> {
	public  void Test() {
		this.handler.update(new AutoTest());
	}
}

class UnitTestWorker extends Worker<UnitTest> {
	public  void Test() {
		this.handler.update(new UnitTest());
	}
}



public class ClassTemplatesTests {
    public static void main(String args[]) {
    	BuildWorker buildWorker = new BuildWorker();
    	AutoTestWorker autoTestWorker = new AutoTestWorker();
    	UnitTestWorker unitTestWorker = new UnitTestWorker();
    	
    	buildWorker.Test();
    	autoTestWorker.Test();
    	unitTestWorker.Test();
    }
}
