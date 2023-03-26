package Finalizers_Cleaners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

class Finalizable {
    private final String className = getClass().getName();

    public Finalizable() {
        System.out.println(className);
    }

    @Override
    public void finalize() {
        System.out.println("finalize() shall be called for " + className);
    }
}

public class Finalizers {
    public static void main(String[] args) throws IOException, InterruptedException {

        Finalizable f = new Finalizable();
        // System.gc();
        // Runtime.runFinalizersOnExit(true);
        Runtime.getRuntime().runFinalization();
        // TimeUnit.SECONDS.sleep(1);
    }
}
