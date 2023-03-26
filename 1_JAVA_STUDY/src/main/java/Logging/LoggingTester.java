//============================================================================
// Name        : Logging.java
// Created on  : June 20, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Cache file class
//============================================================================

package Logging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

class MyHtmlFormatter extends Formatter {
    // this method is called for every log records
    public String format(LogRecord rec) {
            StringBuffer buf = new StringBuffer(1000);
            buf.append("<tr>\n");

            // colorize any levels >= WARNING in red
            if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
                    buf.append("\t<td style=\"color:red\">");
                    buf.append("<b>");
                    buf.append(rec.getLevel());
                    buf.append("</b>");
            } else {
                    buf.append("\t<td>");
                    buf.append(rec.getLevel());
            }

            buf.append("</td>\n");
            buf.append("\t<td>");
            buf.append(calcDate(rec.getMillis()));
            buf.append("</td>\n");
            buf.append("\t<td>");
            buf.append(formatMessage(rec));
            buf.append("</td>\n");
            buf.append("</tr>\n");

            return buf.toString();
    }

    private String calcDate(long millisecs) {
            SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            Date resultdate = new Date(millisecs);
            return date_format.format(resultdate);
    }

    // this method is called just after the handler using this
    // formatter is created
    public String getHead(Handler h) {
        return "<!DOCTYPE html>\n<head>\n<style>\n"
            + "table { width: 100% }\n"
            + "th { font:bold 10pt Tahoma; }\n"
            + "td { font:normal 10pt Tahoma; }\n"
            + "h1 {font:normal 11pt Tahoma;}\n"
            + "</style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<h1>" + (new Date()) + "</h1>\n"
            + "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n"
            + "<tr align=\"left\">\n"
            + "\t<th style=\"width:10%\">Loglevel</th>\n"
            + "\t<th style=\"width:15%\">Time</th>\n"
            + "\t<th style=\"width:75%\">Log Message</th>\n"
            + "</tr>\n";
      }

    // this method is called just after the handler using this
    // formatter is closed
    public String getTail(Handler h) {
            return "</table>\n</body>\n</html>";
    }
}


class MySimpleLogFormat extends Formatter {
	/** **/
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/** @method format. **/
	/** This method is called for every log records: **/
    public String format(LogRecord rec) {
        return String.format("%s [%-7s] %s\n",calcDate(rec.getMillis()), rec.getLevel(), formatMessage(rec));
    }
    
	/** @method calcDate. **/
	/** Date cast method. **/
	private String calcDate(long millisecs) {
		return dateFormat.format(new Date(millisecs));
    }
	/** @method getHead. **/
	/** This method is called just after the handler using this formatter is created. **/
    public String getHead(Handler h) {
        return "";
    }
	/** @method getTail. **/
	/** This method is called just after the handler using this formatter is closed. **/
    public String getTail(Handler h) {
            return "";
    }
}

// @author Tokmakov
public class LoggingTester {
	/** **/
	public LoggingTester() {
		// TODO Auto-generated constructor stub
	}

	/** **/
	public static void main(String[] args) {
		LoggerTest_Handlers();
		//LoggerTest_Properties();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	static void LoggerTest_Handlers() {
	
		Logger logger = Logger.getLogger(LoggingTester.class.getName());
		Handler fileHandler = null;
		Handler consoleHandler = null;
		try {
			fileHandler = new FileHandler("src//Logging//trace.log");
			consoleHandler = new ConsoleHandler();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String stringMessage = "TEST STRING MESSAGE";
		String stringMessageFormat ="Test string message format";
		Throwable throwable = new Throwable();
		//ResourceBundle resourceBundle = ResourceBundle.getBundle("logging.jul.bundle");
		//Supplier<String> stringMessageSupplier = ()->"Сообщение";
		
		consoleHandler.setLevel(Level.ALL);
		consoleHandler.setFormatter(new MySimpleLogFormat());
		
		fileHandler.setLevel(Level.ALL);
		fileHandler.setFormatter(new MySimpleLogFormat()); 
		//fileHandler.setFormatter(new MyHtmlFormatter()); // + Change log file to .html
		
		logger.addHandler(consoleHandler);
		logger.addHandler(fileHandler);
		
		logger.log(new LogRecord(Level.INFO, stringMessage));
		logger.log(Level.INFO, stringMessage);
		//logger.log(Level.INFO, stringMessageSupplier);
		logger.log(Level.INFO, stringMessageFormat);
		logger.log(Level.INFO, stringMessage, throwable );
		//logger.log(Level.INFO, throwable, stringMessageSupplier);
		
		logger.logp(Level.WARNING, "ClassName", "MethodName", stringMessage);
		//logger.logp(Level.INFO, "ClassName", "MethodName", stringMessageSupplier);
		logger.logp(Level.INFO, "ClassName", "MethodName1", stringMessageFormat);
		logger.logp(Level.INFO, "ClassName", "MethodName22", stringMessage, throwable);
		//logger.logp(Level.INFO, "ClassName", "MethodName", throwable, stringMessageSupplier);
		
		//logger.logrb(Level.INFO, "ClassName", "MethodName", resourceBundle, "messageId");
		//logger.logrb(Level.INFO, "ClassName", "MethodName", resourceBundle, "messageId", throwable);
		
		logger.throwing("ClassName","MethodName", throwable);
	}

	static void LoggerTest_Properties() {
		Logger logger = null;
		try {
			LogManager.getLogManager().readConfiguration(LoggingTester.class.getResourceAsStream("logging.properties"));
			logger = Logger.getLogger(LoggingTester.class.getName());
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String message = "TEST MESSAGE 1";
		Throwable throwable = new Throwable();
		
		logger.log(new LogRecord(Level.INFO, message));
		logger.log(Level.SEVERE, message);
		logger.log(Level.WARNING, message);
		logger.log(Level.INFO, message, throwable);
		logger.logp(Level.INFO, "ClassName", "MethodName", message);
	}
}
