/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* LoggerTest2.java class
*
* @name    : LoggerTest2.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 25, 2020
****************************************************************************/

package Logging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoggerTest2 {
	private static final Logger logger = LogManager.getLogger(LoggerTest2.class);
	  
	/** **/
	public static void main(String[] args) {
		logger.info("343434");
		logger.error("343434");
	}
}


