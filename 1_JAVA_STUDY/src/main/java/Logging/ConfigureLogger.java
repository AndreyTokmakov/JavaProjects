package Logging;

import java.util.logging.*;

class ClassA {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    public void Test(String message) {
        System.out.println(LOG.getName());
        LOG.log(new LogRecord(Level.INFO, message));
        LOG.log(new LogRecord(Level.CONFIG, message));
        LOG.log(new LogRecord(Level.FINE, message));
        LOG.log(new LogRecord(Level.FINER, message));
        LOG.log(new LogRecord(Level.FINEST, message));
        LOG.log(new LogRecord(Level.ALL, message));
        LOG.log(new LogRecord(Level.SEVERE, message));
    }
}

class ClassB {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    public void Test(String message) {
        System.out.println(LOG.getName());
        LOG.log(new LogRecord(Level.INFO, message));
        LOG.log(new LogRecord(Level.CONFIG, message));
        LOG.log(new LogRecord(Level.FINE, message));
        LOG.log(new LogRecord(Level.FINER, message));
        LOG.log(new LogRecord(Level.FINEST, message));
        LOG.log(new LogRecord(Level.ALL, message));
        LOG.log(new LogRecord(Level.SEVERE, message));
        // LOG.log(new LogRecord(Level., message));
    }
}


public class ConfigureLogger
{
    protected static void configure() {
        Logger logger1 = Logger.getLogger("Logging.ClassA");
        logger1.setLevel(Level.ALL);

        Logger logger2 = Logger.getLogger("Logging.ClassB");
        logger2.setLevel(Level.WARNING);

        Logger logger3 = Logger.getLogger("Logging");
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new XMLFormatter());
        logger3.addHandler(handler);
        logger3.setUseParentHandlers(false);

    }

    public static void main(String[] args) {
        configure();

        /** Print out all messages: **/
        ClassA aObject = new ClassA();
        // aObject.Test("Test_Message111");

        /** Print out only SEVERE messages: **/
        ClassB bObject = new ClassB();
        bObject.Test("Test_Message123");

        /** Prints out using 'Logger logger3' configuration from method configure() **/
        Logger logger3 = Logger.getLogger(ConfigureLogger.class.getName());
        // logger3.log(new LogRecord(Level.INFO, "TEsts"));
    }
}
