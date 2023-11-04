package false_sharing;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;

@Fork(value = 1, jvmArgs = "-XX:-RestrictContended")
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class FalseSharingBenchmark {

    private static final CounterAffectedByFalseSharing COUNTER_AFFECTED_BY_FALSE_SHARING = new CounterAffectedByFalseSharing();

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Group("falseSharing")
    @GroupThreads(1)
    public long incrementCounterA() {
        return COUNTER_AFFECTED_BY_FALSE_SHARING.incrementA();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Group("falseSharing")
    @GroupThreads(1)
    public long incrementCounterB() {
        return COUNTER_AFFECTED_BY_FALSE_SHARING.incrementB();
    }

}
