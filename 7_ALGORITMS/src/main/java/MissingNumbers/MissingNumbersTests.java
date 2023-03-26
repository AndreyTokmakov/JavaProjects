package MissingNumbers;

import java.util.Arrays;
import java.util.BitSet;

class Worker {
	protected void printMissingNumber(int[] numbers, int count) {
        int missingCount = count - numbers.length;
        BitSet bitSet = new BitSet(count);

        for (int number : numbers) {
            bitSet.set(number - 1);
        }
 
        System.out.printf("Missing numbers in integer array %s, with total number %d is %n", Arrays.toString(numbers), count);
        int lastMissingIndex = 0;

        for (int i = 0; i < missingCount; i++) {
            lastMissingIndex = bitSet.nextClearBit(lastMissingIndex);
            System.out.println(++lastMissingIndex);
        }
    }
	
	protected void printMissingNumber2(int[] numbers, int count) {
		System.out.printf("Missing numbers in integer array %s, with total number %d is %n", Arrays.toString(numbers), count);
		long sum = (count * (count + 1))/2;
	    for (int number : numbers) 
	    	sum -= number;
	    System.out.println(sum);
	}
}

class Tests {
	private final static Worker worker = new Worker();
	
	void Test1() {
		// one missing number 
		worker.printMissingNumber(new int[]{1, 2, 3, 4, 6}, 6); 

		// two missing number 
		worker.printMissingNumber(new int[]{1, 2, 3, 4, 6, 7, 9, 8, 10}, 10);

		// three missing number 
		worker.printMissingNumber(new int[]{1, 2, 3, 4, 6, 9, 8}, 10);

		// four missing number 
		worker.printMissingNumber(new int[]{1, 2, 3, 4, 9, 8}, 10);
	}
	
	void Test2() {
		worker.printMissingNumber2(new int[]{1, 2, 3, 4, 6}, 6);
	}
}

public class MissingNumbersTests {
	public static void main(String[] args) {
		Tests test = new Tests();
		test.Test1();
		// test.Test2();
	}
}
