package false_sharing;

public class CounterAffectedByFalseSharing {
    private volatile long counterA = 0L;
    private volatile long counterB = 0L;

    public long incrementA() {
        return counterA++;
    }

    public long incrementB() {
        return counterB++;
    }
}
