package false_sharing;

import java.io.IOException;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jol.info.ClassLayout;

public class FalseSharingApplication {

	public static void main(String[] args) throws IOException, RunnerException {
		//you need to run with -XX:-RestrictContended in order to see changes in memory class layout
		System.out.println(ClassLayout.parseClass(CounterAffectedByFalseSharing.class).toPrintable());
		System.out.println(ClassLayout.parseClass(Counter.class).toPrintable());
		org.openjdk.jmh.Main.main(args);
	}

}
