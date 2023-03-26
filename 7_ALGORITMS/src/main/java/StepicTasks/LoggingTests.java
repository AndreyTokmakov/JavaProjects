package StepicTasks;

import java.util.logging.*;

public class LoggingTests {
    public static void main(String[] args)
    {
        Logger LOGGER1 = Logger.getLogger("org.stepic.java.logging.ClassA");
        LOGGER1.setLevel(Level.ALL);

        Logger LOGGER2 = Logger.getLogger("org.stepic.java.logging.ClassB");
        LOGGER2.setLevel(Level.WARNING);

        Logger LOGGER3 = Logger.getLogger("org.stepic.java");
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new XMLFormatter());
        LOGGER3.addHandler(handler);
        LOGGER3.setUseParentHandlers(false);

        LOGGER3.log(new LogRecord(Level.INFO, "Test"));

    }
}
