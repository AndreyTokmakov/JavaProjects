package SummaryStatistics;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class IntSummaryStatisticsTests {
	protected IntSummaryStatistics stats = new IntSummaryStatistics();
	
	public static void printIntSummaryStatistics(final String message, 
												 final IntSummaryStatistics intSummaryStatistics) {
	    System.out.println(message);
	    System.out.println("Count: " + intSummaryStatistics.getCount());
	    System.out.println("Avg: " + intSummaryStatistics.getAverage());
	    System.out.println("Min: " + intSummaryStatistics.getMin());
	    System.out.println("Max: " + intSummaryStatistics.getMax());
	    System.out.println("Sum: " + intSummaryStatistics.getSum());
	    System.out.println();
	}
	
	protected void Test1() {
        stats.accept(1);
        stats.accept(2);
        stats.accept(3);
        stats.accept(4);
        stats.accept(5);
        
        printIntSummaryStatistics("", stats);
	}
}

class LongSummaryStatisticsTests {
	
	public static void printLongSummaryStatistics(String message, LongSummaryStatistics longSummaryStatistics) {
	    System.out.println(message);
	    System.out.println("Count: " + longSummaryStatistics.getCount());
	    System.out.println("Avg: " + longSummaryStatistics.getAverage());
	    System.out.println("Min: " + longSummaryStatistics.getMin());
	    System.out.println("Max: " + longSummaryStatistics.getMax());
	    System.out.println("Sum: " + longSummaryStatistics.getSum());
	    System.out.println();
	}

	protected void Test1() {
		LongSummaryStatistics longSummaryStatistics = LongStream.range(1, 100).summaryStatistics();
		printLongSummaryStatistics("LongSummaryStatistics for range 1-100", longSummaryStatistics);
	}
	
	protected void Test2() {
		LongSummaryStatistics longSummaryStatistics = List.of(1L, 2L, 3L, 4L, 5L)
				                                          .stream()
				                                          .collect(Collectors.summarizingLong(Long::longValue));
		printLongSummaryStatistics("LongSummaryStatistics for range 1-5", longSummaryStatistics);
	}
}

public class SummaryStatisticsTests {
	
	public static void TestAll() {
		System.out.println("---DoubleSummaryStatistics---");		
		DoubleSummaryStatistics dstats = DoubleStream.of(5.33d,2.34d,5.32d,2.31d,3.51d)
				                                     .collect(DoubleSummaryStatistics::new, DoubleSummaryStatistics::accept,  DoubleSummaryStatistics::combine);
		System.out.println("Max:"+dstats.getMax()+", Min:"+dstats.getMin());
		System.out.println("Count:"+dstats.getCount()+", Sum:"+dstats.getSum());
		System.out.println("Average:"+dstats.getAverage());		
		
		System.out.println("---LongSummaryStatistics---");			
		LongSummaryStatistics lstats = LongStream.of(51l,23l,53l,23l,35l)
				                                 .collect(LongSummaryStatistics::new, LongSummaryStatistics::accept, LongSummaryStatistics::combine);
		System.out.println("Max:"+lstats.getMax()+", Min:"+lstats.getMin());
		System.out.println("Count:"+lstats.getCount()+", Sum:"+lstats.getSum());
		System.out.println("Average:"+lstats.getAverage());		
		
		System.out.println("---IntSummaryStatistics---");			
		IntSummaryStatistics istats = IntStream.of(51,22,50,27,35)
				                               .collect(IntSummaryStatistics::new, IntSummaryStatistics::accept, IntSummaryStatistics::combine);
		System.out.println("Max:"+istats.getMax()+", Min:"+istats.getMin());
		System.out.println("Count:"+istats.getCount()+", Sum:"+istats.getSum());
		System.out.println("Average:"+istats.getAverage());		
	}
	
	public static void main(String[] args) {
		IntSummaryStatisticsTests tests = new IntSummaryStatisticsTests();
		 // tests.Test1();

		LongSummaryStatisticsTests testsLong = new LongSummaryStatisticsTests();
		// testsLong.Test1();
		// testsLong.Test2();
		
		TestAll();
	}
}
