package Memory.Utilities;

import org.assertj.core.api.Assertions;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

class LeakDetector extends PhantomReference<Object> {

    private final String description;

    /**
     * Initialization of the memory leaks detector.
     * @param referent the object(resource) for which we are looking for leaks.
     */
    public LeakDetector(Object referent) {
        super(referent, new ReferenceQueue<>());
        this.description = String.valueOf(referent);
    }

    /**
     * If exists memory leaks then throws a fail.
     *
     * WARN: Run this method after delete all references on the checkable object(resource)
     */
    public void assertMemoryLeaksNotExist() {
        GCUtils.fullFinalization();
        Assertions.assertThat(isEnqueued())
                  .as("Object: " + description + " was leaked").isTrue();
    }

    /**
     * If not exists memory leaks then throws a fail.
     *
     * WARN: Run this method after delete all references on the checkable object(resource)
     */
    public void assertMemoryLeaksExist() {
    	GCUtils.fullFinalization();

        Assertions.assertThat(isEnqueued())
                  .as("Object: " + description + " was collected by GC").isFalse();
    }
}