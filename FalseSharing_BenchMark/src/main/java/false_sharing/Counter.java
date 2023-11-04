package false_sharing;

import jdk.internal.vm.annotation.Contended;

public class Counter {
    @Contended
    private volatile long counterA = 0L;
    @Contended
    private volatile long counterB = 0L;

    public long incrementA() {
        return counterA++;
    }

    public long incrementB() {
        return counterB++;
    }
}
