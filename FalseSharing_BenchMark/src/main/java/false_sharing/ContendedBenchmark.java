package false_sharing;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

@Fork(value = 1, jvmArgs = "-XX:-RestrictContended")
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ContendedBenchmark {

    private static final Counter COUNTER = new Counter();

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Group("contended")
    @GroupThreads(1)
    public long contendedIncrementA() {
        return COUNTER.incrementA();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Group("contended")
    @GroupThreads(1)
    public long contendedIncrementB() {
        return COUNTER.incrementB();
    }

}
