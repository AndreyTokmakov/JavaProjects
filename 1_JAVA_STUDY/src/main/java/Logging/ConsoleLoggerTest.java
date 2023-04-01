package Logging;

import java.util.logging.*;

public class ConsoleLoggerTest
{
    public static void main(String[] args)
    {
        final Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new MySimpleLogFormat());

        final Logger logger = Logger.getLogger(LoggingTester.class.getName());
        logger.addHandler(consoleHandler);


        String stringMessage = "TEST STRING MESSAGE";
        String stringMessageFormat ="Test string message format";
        Throwable throwable = new Throwable();

        logger.log(Level.INFO, "1");
        logger.log(Level.INFO, "2");


        /*
        logger.log(new LogRecord(Level.INFO, stringMessage));
        logger.log(Level.INFO, stringMessage);
        logger.log(Level.INFO, stringMessageFormat);
        logger.log(Level.INFO, stringMessage, throwable );

        logger.logp(Level.WARNING, "ClassName", "MethodName", stringMessage);
        logger.logp(Level.INFO, "ClassName", "MethodName1", stringMessageFormat);
        logger.logp(Level.INFO, "ClassName", "MethodName22", stringMessage, throwable);
        */
    }
}
