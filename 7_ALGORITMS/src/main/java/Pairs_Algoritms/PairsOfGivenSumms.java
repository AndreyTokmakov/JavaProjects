package Pairs_Algoritms;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Worker {
	
	// TODO. Error - Filed with NEgatives
	protected void FindPairsWithGivenSum_Limited(final int[] numbers, int xSum, int max) {
		int[] tmp = new int[max + 1];
		for (int value : numbers) {
			if (1 == tmp[xSum - value])
				System.out.println("{" + (xSum - value) + ", " + value + "}");
			tmp[value] = 1;
		}
	}
	
	protected void FindPairsWithGivenSum(final int[] numbers, int xSum) {
		Set<Integer> set = new HashSet<>();
		for (int value : numbers) {
			if (true == set.contains(xSum - value)) {
				System.out.println("{" + (xSum - value) + ", " + value + "}");
			}
			set.add(value);
		}
	}
}

class Tests {
	private final static Worker worker = new Worker();
	
	protected void Test1() {
		int[] data = {2,4,5,1,3,2,7,4,1,7,8,2,5};
		worker.FindPairsWithGivenSum_Limited(data, 10, 8);
	}
	
	protected void Test2() {
		int[] data = {2,4,5,1,3,2,7,4,1,7,8,2,5};
		worker.FindPairsWithGivenSum(data, 10);
	}
}

public class PairsOfGivenSumms {
	private final static Tests tests = new Tests();
	
	public static void main(String[] args) {
		// tests.Test1();
		tests.Test2();
	}
}
