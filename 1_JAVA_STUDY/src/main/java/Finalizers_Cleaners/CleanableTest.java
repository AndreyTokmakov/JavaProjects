package Finalizers_Cleaners;

import java.lang.ref.Cleaner;

class AppCleanerProvider {
    private static final Cleaner CLEANER = Cleaner.create();

    public static Cleaner getCleaner() {
        return CLEANER;
    }
}

class Resource {
    private final String className = getClass().getName();

    public Resource() {
        System.out.println(className + " created");
    }
}

class ClassAccessingResource implements AutoCloseable {

    private final Cleaner cleaner = AppCleanerProvider.getCleaner();
    private final Cleaner.Cleanable cleanable;

    //This resource needs to be cleaned after usage
    private final Resource resource;

    public ClassAccessingResource() {
        this.resource = new Resource();
        this.cleanable = cleaner.register(this, cleanResourceAction(resource));
    }

    public void businessOperation() {
        //Access the resource in methods
        System.out.println("Inside businessOperation()");
    }

    public void anotherBusinessOperation() {
        //Access the resource in methods
        System.out.println("Inside anotherBusinessOperation()");
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }

    private static Runnable cleanResourceAction(final Resource resource) {
        return () -> {
            // Perform cleanup actions
            // resource.release();
            System.out.println("*** Resource Cleaned Up ****\n");
        };
    }
}

public class CleanableTest {
    public static void main(String[] args) throws Exception
    {
        //1 Implicit Cleanup
        try (final ClassAccessingResource clsInstance = new ClassAccessingResource()) {
            // Safely use the resource
            clsInstance.businessOperation();
            clsInstance.anotherBusinessOperation();
        }

        //2 Explicit Cleanup
        final ClassAccessingResource clazzInstance = new ClassAccessingResource();
        clazzInstance.businessOperation();
        clazzInstance.anotherBusinessOperation();
        clazzInstance.close();
    }
}
