package utils;

import org.apache.commons.lang3.NotImplementedException;
import java.util.concurrent.TimeUnit;

public class Utilities
{
    // TODO: Refactor
    public static void sleep(long milliseconds)
    {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private Utilities() {
        throw new NotImplementedException();
    }
}
