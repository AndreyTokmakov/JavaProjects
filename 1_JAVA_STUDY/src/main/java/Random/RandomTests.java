package Random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;


class Tests {
	private static final Random random = new Random();
	
	protected void Test_Int() {
		for (int i = 0; i < 10; i++)
			System.out.println(String.format("%d random int is %d", i, random.nextInt()));
	};
	
	protected void Test_Int_Range() {
		for (int i = 0; i < 10; i++)
			System.out.println(String.format("%d random int is %d", i, random.nextInt(100)));
	};
	
	protected void Random_InRange() {
		final int min = 10;
		final int max = 20;
		
		for (int i = 0; i < 20; i++) {
			int x = ThreadLocalRandom.current().nextInt(min, max + 1);
			System.out.print(x + "  ");
		}
		System.out.println();
	}
	
    public void Random_String() {
    	for (int i = 0; i < 5; ++i)
    		System.out.println(RandomStringUtils.randomAlphanumeric(8));
    	for (int i = 0; i < 5; ++i)
    		System.out.println(RandomStringUtils.randomAlphanumeric(16));
    	for (int i = 0; i < 5; ++i)
    		System.out.println(RandomStringUtils.randomAlphanumeric(32));
    }
};


class ThreadLocalRandomTests {
	
	protected void Test1() {
		int val = ThreadLocalRandom.current().nextInt(0, 100);
		System.out.println(val);
	} 
	
}

public class RandomTests {
	public static void main(String[] args) {
		Tests tests = new Tests();
		
		// tests.Test_Int();
		// tests.Test_Int_Range();	
		// tests.Random_InRange();
		tests.Random_String();
		
		ThreadLocalRandomTests test2 = new ThreadLocalRandomTests();
		// test2.Test1();
	}
}
