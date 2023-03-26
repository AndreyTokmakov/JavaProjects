/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* VerifyingTests.java class
*
* @name    : VerifyingTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 15, 2020
****************************************************************************/

package Caliper_Tests;

import com.google.caliper.Benchmark;
import com.google.caliper.runner.CaliperMain;

public class DemoBenchmark {
	
	@Benchmark
	void timeStringBuilder(int reps) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < reps; i++) {
			sb.setLength(0);
      		sb.append("hello world");
		}
	}
	
	public static void main(String[] args) {
		CaliperMain.main(DemoBenchmark.class, args);
	}
}