/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Logging.java class
*
* @name    : Logging.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 25, 2020
****************************************************************************/

package examples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// import Logging.LoggerTest2;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

interface LogTester {
	public void putLogs();
}

@CommonsLog
class CommonsLogTest implements LogTester {
	@Override
	public void putLogs() {
		log.info("Some info!");
	}
}

@Log
class LogTest implements LogTester {
	@Override
	public void putLogs() {
		log.info("Some info!");
	}
}

@Log4j
class Log4jTest implements LogTester {
	@Override
	public void putLogs() {
		log.info("Some info!");
	}
}

@Log4j2
class Log4j2Test implements LogTester {

	@Override
	public void putLogs() {
		log.info("Some info!");
		log.error("And some ERRR");
	}
}

@Slf4j
class Slf4jTest implements LogTester{
 
	@Override
	public void putLogs() {
		log.info("Some info!");
		log.error("And some ERRR");
    }
}

public class Logging {
	
	public static void commonsLogTest() {
		LogTester tester = new CommonsLogTest();
		tester.putLogs();
	}
	
	public static void logTest() {
		LogTester tester = new LogTest();
		tester.putLogs();
	}
	
	public static void log4jTest() {
		LogTester tester = new Log4jTest();
		tester.putLogs();
	}
	
	public static void log4j2Test() {
		LogTester tester = new Log4j2Test();
		tester.putLogs();
	}
	
	public static void slf4j_Test() {
		LogTester tester = new Slf4jTest();
		tester.putLogs();
	}
	
	public static void main(String[] args) 
	{
		// commonsLogTest();
		// logTest();
		 log4jTest();
		// log4j2Test();
		// slf4j_Test();
	}
}
