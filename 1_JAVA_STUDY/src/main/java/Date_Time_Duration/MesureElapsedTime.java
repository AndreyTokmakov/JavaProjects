package Date_Time_Duration;

import java.time.Duration;
import java.time.Instant;

public class MesureElapsedTime {
	
	public static void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Test1() {
		long startTime = System.nanoTime();
		sleep(2000); 
		long stopTime = System.nanoTime();
		System.out.println((stopTime - startTime) / (1000 * 1000) + " seconds.");
	}
	
	public static void Test2() {
		Instant start = Instant.now();
		sleep(2000); 
		Instant end = Instant.now();
		System.out.println(Duration.between(start, end)); // prints PT1M3.553S
	}
	
	
	public static void main(String[] args) {
		 Test1();
		
		//Test2();
	}
}
